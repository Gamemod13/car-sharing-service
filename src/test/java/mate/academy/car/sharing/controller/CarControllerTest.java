package mate.academy.car.sharing.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

import mate.academy.car.sharing.dto.request.CarRequestDto;
import mate.academy.car.sharing.dto.response.CarResponseDto;
import mate.academy.car.sharing.entity.Car;
import mate.academy.car.sharing.mapper.CarMapper;
import mate.academy.car.sharing.service.CarService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class CarControllerTest {
    private static final Long CAR_ID = 1L;

    @Mock
    private CarService carService;

    @Mock
    private CarMapper carMapper;

    @InjectMocks
    private CarController carController;

    private CarRequestDto carRequestDto;
    private CarResponseDto carResponseDto;
    private Car car;

    @BeforeEach
    void setUp() {
        carRequestDto = new CarRequestDto();
        carRequestDto.setBrand("Peugeot");
        carRequestDto.setModel("107");
        carRequestDto.setType(String.valueOf(Car.CarType.HATCHBACK));
        carRequestDto.setInventory(4);
        carRequestDto.setDailyFee(BigDecimal.TEN);

        carResponseDto = new CarResponseDto();
        carResponseDto.setId(CAR_ID);
        carResponseDto.setBrand(carRequestDto.getBrand());
        carResponseDto.setModel(carRequestDto.getModel());
        carResponseDto.setType(carRequestDto.getType());
        carResponseDto.setInventory(carRequestDto.getInventory());
        carResponseDto.setDailyFee(carRequestDto.getDailyFee());

        car = new Car();
        car.setId(CAR_ID);
        car.setBrand(carRequestDto.getBrand());
        car.setModel(carRequestDto.getModel());
        car.setType(Car.CarType.valueOf(carRequestDto.getType()));
        car.setInventory(carRequestDto.getInventory());
        car.setDailyFee(carRequestDto.getDailyFee());
    }

    @Test
    void add_validCarRequestDto_ok() {
        given(carMapper.toEntity(any(CarRequestDto.class))).willReturn(car);
        given(carService.add(any(Car.class))).willReturn(car);
        given(carMapper.toDto(any(Car.class))).willReturn(carResponseDto);

        Assertions.assertEquals(carResponseDto, carController.add(carRequestDto));

        then(carMapper).should().toEntity(carRequestDto);
        then(carService).should().add(car);
        then(carMapper).should().toDto(car);
    }

    @Test
    void getAll_notEmptyDB_ok() {
        given(carService.getAll()).willReturn(Collections.singletonList(car));
        given(carMapper.toDto(any(Car.class))).willReturn(carResponseDto);
        List<CarResponseDto> actual = carController.getAll();

        then(carService).should().getAll();

        List<CarResponseDto> expected = Collections.singletonList(carResponseDto);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getAll_emptyDb_ok() {
        given(carService.getAll()).willReturn(Collections.emptyList());
        List<CarResponseDto> actual = carController.getAll();

        then(carService).should().getAll();

        List<CarResponseDto> expected = Collections.emptyList();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void updateCar_validId_ok() {
        given(carMapper.toEntity(any(CarRequestDto.class))).willReturn(car);
        given(carService.update(car)).willReturn(car);
        given(carMapper.toDto(any(Car.class))).willReturn(carResponseDto);

        CarResponseDto actual = carController.updateCar(CAR_ID, carRequestDto);
        Assertions.assertEquals(carResponseDto, actual);

        then(carMapper).should().toEntity(carRequestDto);
        then(carService).should().update(car);
        then(carMapper).should().toDto(car);
    }

    @Test
    void delete_validId_ok() {
        Mockito.doNothing().when(carService).delete(CAR_ID);

        carController.delete(CAR_ID);

        Mockito.verify(carService).delete(CAR_ID);
    }
}
