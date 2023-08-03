package mate.academy.car.sharing.service.stripe;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import lombok.RequiredArgsConstructor;
import mate.academy.car.sharing.entity.Payment;
import mate.academy.car.sharing.entity.Rental;
import mate.academy.car.sharing.exception.FailedSessionCreatingException;
import mate.academy.car.sharing.service.PaymentService;
import mate.academy.car.sharing.service.RentalService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@RequiredArgsConstructor
@Service
public class StripePaymentService {
    private static final BigDecimal FINE_MULTIPLIER = new BigDecimal(3);
    @Value("${stripe.secret.key}")
    private String secretKey;
    @Value("${base.url}")
    private String baseUrl;
    private final RentalService rentalService;
    private final PaymentService paymentService;

    public Payment createPaymentSession(Payment payment) {
        Rental rental = rentalService.getById(payment.getRental().getId());
        payment.setAmountToPay(calculateAmountToPay(rental));
        payment.setStatus(Payment.PaymentStatus.PENDING);
        payment.setRental(rental);
        SessionCreateParams.LineItem lineItem =
                SessionCreateParams.LineItem.builder()
                        .setPrice(String.valueOf(payment
                                .getAmountToPay()
                                .multiply(BigDecimal.valueOf(100))
                                .intValue()))
                        .setQuantity(1L)
                        .build();
        String successUrl = buildSuccessUrl();
        String cancelUrl = buildFailureUrl();
        SessionCreateParams params =
                SessionCreateParams.builder()
                        .setCurrency("USD")
                        .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                        .setMode(SessionCreateParams.Mode.PAYMENT)
                        .setSuccessUrl(successUrl)
                        .setCancelUrl(cancelUrl)
                        .addLineItem(lineItem)
                        .build();
        try {
            Stripe.apiKey = secretKey;
            Session session = Session.create(params);
            if (payment.getType().equals(Payment.PaymentType.FINE)) {
                Payment finePayment = new Payment();
                finePayment.setStatus(Payment.PaymentStatus.PENDING);
                finePayment.setType(Payment.PaymentType.FINE);
                finePayment.setRental(rental);
                finePayment.setSessionId(session.getId());
                finePayment.setSessionUrl(session.getUrl());
                finePayment.setAmountToPay(calculateFineAmountToPay(rental));
                return paymentService.add(finePayment);
            }
            payment.setSessionId(session.getId());
            payment.setSessionUrl(session.getUrl());
            return paymentService.add(payment);
        } catch (StripeException e) {
            throw new FailedSessionCreatingException("Can't create session");
        }
    }

    private BigDecimal calculateFineAmountToPay(Rental rental) {
        BigDecimal overdueDays = BigDecimal.valueOf(Duration
                        .between(LocalDateTime.now(), rental.getReturnDate())
                        .toDays());
        BigDecimal amountToPayForOverdueDays = rental
                .getCar()
                .getDailyFee()
                .multiply(overdueDays)
                .multiply(FINE_MULTIPLIER);
        BigDecimal commonAmountToPay = amountToPayForOverdueDays.add(calculateAmountToPay(rental));
        return commonAmountToPay;
    }

    private BigDecimal calculateAmountToPay(Rental rental) {
        BigDecimal rentalDays =
                BigDecimal.valueOf(Duration
                        .between(rental.getReturnDate(), rental.getRentalDate())
                        .toDays());
        BigDecimal amountToPay = rental.getCar().getDailyFee().multiply(rentalDays);
        return amountToPay;
    }

    private String buildSuccessUrl() {
        return UriComponentsBuilder.fromHttpUrl(baseUrl)
                .path("/success")
                .queryParam("session_id", "{CHECKOUT_SESSION_ID}")
                .build()
                .toUriString();
    }

    private String buildFailureUrl() {
        return UriComponentsBuilder.fromHttpUrl(baseUrl)
                .path("/cancel")
                .queryParam("session_id", "{CHECKOUT_SESSION_ID}")
                .build()
                .toUriString();
    }

    public void handleSuccessPayment(String sessionId) {
        Payment payment = paymentService.findBySessionId(sessionId);
        payment.setStatus(Payment.PaymentStatus.PAID);
        paymentService.add(payment);
    }
}
