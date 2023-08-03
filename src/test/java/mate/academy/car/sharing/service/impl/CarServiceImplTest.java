package mate.academy.car.sharing.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import mate.academy.car.sharing.entity.Car;
import mate.academy.car.sharing.repository.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.transaction.annotation.Transactional;

class CarServiceImplTest {

    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarServiceImpl carService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addCarTest() {
        Car car = new Car();
        car.setId(1L);
        when(carRepository.save(car)).thenReturn(car);
        Car addedCar = carService.add(car);
        assertNotNull(addedCar);
        assertEquals(car, addedCar);
    }

    @Test
    void getByIdExistingCarTest() {
        Long carId = 1L;
        Car car = new Car();
        car.setId(carId);
        when(carRepository.findById(carId)).thenReturn(Optional.of(car));
        Car retrievedCar = carService.getById(carId);
        assertNotNull(retrievedCar);
        assertEquals(car, retrievedCar);
    }

    @Test
    void getByIdNonExistingCarTest() {
        Long carId = 1L;
        when(carRepository.findById(carId)).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> carService.getById(carId));
    }

    @Test
    void getAllTest() {
        Car car1 = new Car();
        Car car2 = new Car();
        List<Car> carList = List.of(car1, car2);
        when(carRepository.findAll()).thenReturn(carList);
        List<Car> retrievedCars = carService.getAll();
        assertNotNull(retrievedCars);
        assertEquals(2, retrievedCars.size());
        assertEquals(car1, retrievedCars.get(0));
        assertEquals(car2, retrievedCars.get(1));
    }

    @Test
    void deleteTest() {
        Long carId = 1L;
        carService.delete(carId);
        verify(carRepository).deleteById(carId);
    }

    @Test
    @Transactional
    void updateCarTest() {
        Car car = new Car();
        car.setId(1L);
        when(carRepository.findById(car.getId())).thenReturn(Optional.of(car));
        Car updatedCar = carService.update(car);
        assertNotNull(updatedCar);
        assertEquals(car, updatedCar);
    }

    @Test
    void increaseInventoryTest() {
        int initialInventory = 5;
        Car car = new Car();
        car.setId(1L);
        car.setInventory(initialInventory);
        when(carRepository.findById(car.getId())).thenReturn(Optional.of(car));
        Car updatedCar = carService.increaseInventory(car);
        assertNotNull(updatedCar);
        assertEquals(initialInventory + 1, updatedCar.getInventory());
    }

    @Test
    void decreaseInventoryTest() {
        int initialInventory = 5;
        Car car = new Car();
        car.setId(1L);
        car.setInventory(initialInventory);
        when(carRepository.findById(car.getId())).thenReturn(Optional.of(car));
        Car updatedCar = carService.decreaseInventory(car);
        assertNotNull(updatedCar);
        assertEquals(initialInventory - 1, updatedCar.getInventory());
    }
}
