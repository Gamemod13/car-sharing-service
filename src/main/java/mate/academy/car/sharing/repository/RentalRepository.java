package mate.academy.car.sharing.repository;

import mate.academy.car.sharing.entity.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRepository extends JpaRepository<Rental, Long> {
}
