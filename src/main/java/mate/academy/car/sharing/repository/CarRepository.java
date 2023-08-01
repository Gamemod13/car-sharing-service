package mate.academy.car.sharing.repository;

import mate.academy.car.sharing.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
}
