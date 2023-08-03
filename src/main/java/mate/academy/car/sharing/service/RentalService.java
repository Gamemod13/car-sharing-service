package mate.academy.car.sharing.service;

import java.util.List;
import java.util.Optional;
import mate.academy.car.sharing.entity.Rental;

public interface RentalService extends AbstractService<Rental> {
    Optional<Rental> getByUserIdActual(Long userId);
}
