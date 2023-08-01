package mate.academy.car.sharing.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
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
                .findById(id).
                orElseThrow(() ->
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
}
