package mate.academy.car.sharing.entity;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue
    private Long id;
    private String model;
    private String brand;
    @Enumerated(value = EnumType.STRING)
    private CarType type;
    private int inventory;
    private BigDecimal dailyFee;

    public enum CarType {
        SEDAN,
        SUV,
        HATCHBACK,
        UNIVERSAL
    }
}
