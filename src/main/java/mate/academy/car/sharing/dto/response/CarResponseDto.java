package mate.academy.car.sharing.dto.response;

import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
public class CarResponseDto {
    private Long id;
    private String model;
    private String brand;
    private String type;
    private int inventory;
    private BigDecimal dailyFee;
}
