package mate.academy.car.sharing.entity;

import java.time.LocalDate;
import javax.persistence.*;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate rentalDate;
    private LocalDate returnDate;
    private LocalDate actualReturnDate;
    @OneToMany
    @JoinColumn(name = "car_id")
    private Car carId;
    @OneToMany
    @JoinColumn(name = "user_id")
    private User userId;
}
