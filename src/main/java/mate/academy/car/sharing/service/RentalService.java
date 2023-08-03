package mate.academy.car.sharing.service;

import java.util.List;
import mate.academy.car.sharing.entity.Rental;

public interface RentalService extends AbstractService<Rental> {
    List<Rental> getOverdueRentals();

    Rental findActualRental(Long userId);
}
