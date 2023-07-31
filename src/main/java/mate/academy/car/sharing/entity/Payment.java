package mate.academy.car.sharing.entity;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;
    @Enumerated(EnumType.STRING)
    private PaymentType type;
    @OneToOne
    @Column(name = "rental_id")
    private Rental rental;
    private String sessionUrl;
    private String sessionId;
    private BigDecimal amountToPay;

    public enum PaymentStatus {
        PENDING, PAID
    }

    public enum PaymentType {
        PAYMENT, FINE
    }
}
