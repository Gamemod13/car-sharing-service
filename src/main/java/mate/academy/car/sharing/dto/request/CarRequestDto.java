package mate.academy.car.sharing.dto.request;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class CarRequestDto {
    private String model;
    private String brand;
    private String type;
    private int inventory;
    private BigDecimal dailyFee;
}
