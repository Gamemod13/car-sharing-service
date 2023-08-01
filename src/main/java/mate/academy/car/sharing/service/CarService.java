package mate.academy.car.sharing.service;

import java.util.List;
import mate.academy.car.sharing.entity.Car;

public interface CarService {
    Car add(Car car);

    Car getById(Long id);

    List<Car> getAll();

    Car update (Car car);

    void delete(Long id);
}
