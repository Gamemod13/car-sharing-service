package mate.academy.car.sharing.service;

import mate.academy.car.sharing.entity.Car;

public interface CarService extends AbstractService<Car> {
    Car increaseInventory(Car car);

    Car decreaseInventory(Car car);
}
