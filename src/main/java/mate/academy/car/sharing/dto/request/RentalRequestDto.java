package mate.academy.car.sharing.dto.request;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class RentalRequestDto {
    private LocalDateTime rentalDate;
    private LocalDateTime returnDate;
    private LocalDateTime actualReturnDate;
    private Long carId;
    private Long userId;
}
