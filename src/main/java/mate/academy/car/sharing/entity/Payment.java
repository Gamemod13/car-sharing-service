package mate.academy.car.sharing.entity;

import java.math.BigDecimal;
import java.net.URL;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Payment {
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;
    @Enumerated(EnumType.STRING)
    private PaymentType type;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rentalId;
    private URL sessionUrl;
    private String sessionId;
    private BigDecimal amountToPay;

    public enum PaymentStatus {
        PENDING, PAID
    }

    public enum PaymentType {
        PAYMENT, FINE
    }
}
