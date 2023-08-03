package mate.academy.car.sharing.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

import lombok.RequiredArgsConstructor;
import mate.academy.car.sharing.entity.Car;
import mate.academy.car.sharing.entity.Rental;
import mate.academy.car.sharing.entity.User;
import mate.academy.car.sharing.repository.RentalRepository;
import mate.academy.car.sharing.service.CarService;
import mate.academy.car.sharing.service.RentalService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class RentalServiceImpl implements RentalService {
    private final RentalRepository rentalRepository;
    private final CarService carService;

    @Override
    @Transactional
    public Rental add(Rental rental) {
        User user = rental.getUser();
        Car car = carService.decreaseInventory(rental.getCar());
        rental.setCar(car);
        rental.setUser(user);
        rental.setRentalDate(LocalDateTime.now());
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

    @Override
    public List<Rental> getByUserAndStatus(Long userId, Boolean isActive) {
        if (isActive) {
            return rentalRepository.findByUserIdAndActualReturnDateNull(userId);
        } else {
            return rentalRepository.findByUserIdAndActualReturnDateNotNull(userId);
        }
    }

    @Override
    @Transactional
    public Rental returnRental(Long id) {
        Rental rental = getById(id);
        if (rental.getActualReturnDate() == null) {
            rental.setActualReturnDate(LocalDateTime.now());
            Car car = rental.getCar();
            carService.increaseInventory(car);
        }
        return update(rental);
    }
}
