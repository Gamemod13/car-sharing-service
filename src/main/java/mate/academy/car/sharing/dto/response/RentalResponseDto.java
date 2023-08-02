package mate.academy.car.sharing.dto.response;

import java.time.LocalDate;
import lombok.Data;

@Data
public class RentalResponseDto {
    private LocalDate rentalDate;
    private LocalDate returnDate;
    private LocalDate actualReturnDate;
    private Long carId;
    private Long userId;
}
