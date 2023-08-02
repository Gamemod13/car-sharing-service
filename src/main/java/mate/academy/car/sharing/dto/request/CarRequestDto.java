package mate.academy.car.sharing.dto.request;

import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
public class CarRequestDto {
    private String model;
    private String brand;
    private String type;
    private int inventory;
    private BigDecimal dailyFee;
}
