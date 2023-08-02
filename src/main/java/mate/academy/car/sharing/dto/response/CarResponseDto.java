package mate.academy.car.sharing.dto.response;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class CarResponseDto {
    private Long id;
    private String model;
    private String brand;
    private String type;
    private int inventory;
    private BigDecimal dailyFee;
}
