package mate.academy.car.sharing.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import mate.academy.car.sharing.entity.Car;
import mate.academy.car.sharing.repository.CarRepository;
import mate.academy.car.sharing.service.CarService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;

    @Override
    public Car add(Car car) {
        return carRepository.save(car);
    }

    @Override
    public Car getById(Long id) {
        return carRepository
                .findById(id)
                .orElseThrow(() ->
                        new NoSuchElementException("Can't find car by id: " + id));
    }

    @Override
    public List<Car> getAll() {
        return carRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        carRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Car update(Car car) {
        Car carFromDb = carRepository.findById(car.getId()).orElseThrow(
                () -> new NoSuchElementException("Can't find car by id: " + car.getId()));
        carFromDb.setId(car.getId());
        carFromDb.setModel(car.getModel());
        carFromDb.setBrand(car.getBrand());
        carFromDb.setType(car.getType());
        carFromDb.setInventory(car.getInventory());
        carFromDb.setDailyFee(car.getDailyFee());
        return carFromDb;
    }

    @Override
    public Car increaseInventory(Car car) {
        int newInventory = car.getInventory() + 1;
        car.setInventory(newInventory);
        return update(car);
    }

    @Override
    public Car decreaseInventory(Car car) {
        int newInventory = car.getInventory() - 1;
        car.setInventory(newInventory);
        return update(car);
    }
}
