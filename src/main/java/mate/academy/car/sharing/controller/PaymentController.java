package mate.academy.car.sharing.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import mate.academy.car.sharing.dto.request.PaymentRequestDto;
import mate.academy.car.sharing.dto.response.PaymentResponseDto;
import mate.academy.car.sharing.entity.Payment;
import mate.academy.car.sharing.entity.User;
import mate.academy.car.sharing.mapper.PaymentMapper;
import mate.academy.car.sharing.service.PaymentService;
import mate.academy.car.sharing.service.TelegramNotificationService;
import mate.academy.car.sharing.service.UserService;
import mate.academy.car.sharing.service.stripe.StripePaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/payments")
public class PaymentController {
    private final PaymentService paymentService;
    private final UserService userService;
    private final StripePaymentService stripePaymentService;
    private final PaymentMapper paymentMapper;
    private final TelegramNotificationService telegramNotificationService;

    @Operation(summary = "Get payment by user id ", description = "Get payment by user id ")
    @GetMapping
    public PaymentResponseDto getByUserId(@RequestParam Long userId) {
        User user = userService.getById(userId);
        Payment payment = paymentService.getByUser(user);
        return paymentMapper.toDto(payment);
    }

    @Operation(summary = "Create payment", description = "Create payment")
    @PostMapping
    public PaymentResponseDto createPaymentSession(@Valid @RequestBody
                                                       PaymentRequestDto requestDto) {
        Payment payment = paymentMapper.toEntity(requestDto);
        return paymentMapper.toDto(stripePaymentService.createPaymentSession(payment));
    }

    @Operation(summary = "Payment success page", description = "Payment success page")
    @GetMapping("/success")
    public ResponseEntity<String> handleSuccessPayment(
            @RequestParam("session_id") String sessionId) {
        stripePaymentService.handleSuccessPayment(sessionId);
        telegramNotificationService.sendMessageToAdminChat(
                successfulPaymentMessage(sessionId));
        return ResponseEntity.ok("Payment successful. Thank you!");
    }

    @Operation(summary = "Payment cancel page", description = "Payment cancel page")
    @GetMapping("/cancel")
    public ResponseEntity<String> handleCancelPayment() {
        return ResponseEntity.ok("Payment canceled. Please try again later.");
    }

    private static String successfulPaymentMessage(String sessionId) {
        return "Success payment wits session_id: " + sessionId + " was completed";
    }
}
