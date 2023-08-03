package mate.academy.car.sharing.dto.response;

import java.time.LocalDateTime;
import lombok.Data;
import mate.academy.car.sharing.entity.User;

@Data
public class RentalResponseDto {
    private Long id;
    private LocalDateTime rentalDate;
    private LocalDateTime returnDate;
    private LocalDateTime actualReturnDate;
    private Long carId;
    private User userId;
}
