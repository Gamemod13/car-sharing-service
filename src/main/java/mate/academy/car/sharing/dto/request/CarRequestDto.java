package mate.academy.car.sharing.dto.request;

import lombok.Data;
import java.math.BigDecimal;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class CarRequestDto {
    @NotBlank(message = "Model must not be empty")
    private String model;
    @NotBlank(message = "Brand must not be empty")
    private String brand;
    @NotBlank(message = "Type must not be empty")
    private String type;
    @Positive(message = "Inventory must be a positive value")
    private int inventory;
    @NotNull(message = "DailyFee must not be null")
    @Positive(message = "DailyFee must be a positive value")
    private BigDecimal dailyFee;
}
