package mate.academy.car.sharing.dto.request;

import java.time.LocalDateTime;
import lombok.Data;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

@Data
public class RentalRequestDto {
    @NotNull(message = "Rental date must not be null")
    @PastOrPresent(message = "Rental date must be in the past or present")
    private LocalDateTime rentalDate;
    @NotNull(message = "Return date must not be null")
    @Future(message = "Return date must be in the future")
    private LocalDateTime returnDate;
    private LocalDateTime actualReturnDate;
    @NotNull(message = "Car ID must not be null")
    private Long carId;
    @NotNull(message = "User ID must not be null")
    private Long userId;
}
