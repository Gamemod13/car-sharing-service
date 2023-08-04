package mate.academy.car.sharing.dto.request;

import lombok.Data;
import mate.academy.car.sharing.entity.Payment;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class PaymentRequestDto {
    @NotNull(message = "Payment type must not be null")
    private Payment.PaymentType type;
    @NotNull(message = "Rental ID must not be null")
    @Positive(message = "Rental ID must be a positive value")
    private Long rentalId;
}
