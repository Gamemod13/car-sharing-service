package mate.academy.car.sharing.dto.request;

import lombok.Data;
import mate.academy.car.sharing.entity.Payment;

@Data
public class PaymentRequestDto {
    private Payment.PaymentType type;
    private Long rentalId;

}
