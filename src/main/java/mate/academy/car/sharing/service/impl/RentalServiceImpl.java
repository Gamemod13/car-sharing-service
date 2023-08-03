package mate.academy.car.sharing.service.impl;

import java.util.List;
import java.util.NoSuchElementException;

import lombok.RequiredArgsConstructor;
import mate.academy.car.sharing.entity.Rental;
import mate.academy.car.sharing.repository.RentalRepository;
import mate.academy.car.sharing.service.RentalService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RentalServiceImpl implements RentalService {
    private final RentalRepository rentalRepository;

    @Override
    public Rental add(Rental rental) {
        return rentalRepository.save(rental);
    }

    @Override
    public Rental getById(Long id) {
        return rentalRepository.findById(id)
                .orElseThrow(() ->
                        new NoSuchElementException("Can't find rental by id: " + id));
    }

    @Override
    public List<Rental> getAll() {
        return rentalRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        rentalRepository.deleteById(id);
    }

    @Override
    public Rental update(Rental rental) {
        //TODO:Check/Update
        return rentalRepository.save(rental);
    }

    @Override
    public List<Rental> getOverdueRentals() {
        return rentalRepository.getOverdueRentals();
    }

    public Rental findActualRental(Long userId) {
        return rentalRepository.findActualRental(userId).orElseThrow();
    }
}
