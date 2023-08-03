package mate.academy.car.sharing.mapper;

import mate.academy.car.sharing.config.mappers.MapperConfig;
import mate.academy.car.sharing.dto.request.RentalRequestDto;
import mate.academy.car.sharing.dto.response.RentalResponseDto;
import mate.academy.car.sharing.entity.Car;
import mate.academy.car.sharing.entity.Rental;
import mate.academy.car.sharing.entity.User;
import mate.academy.car.sharing.service.CarService;
import mate.academy.car.sharing.service.UserService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(config = MapperConfig.class)
public abstract class RentalMapper {
    private CarService carService;
    private UserService userService;

    @Mapping(target = "car", source = "requestDto.carId", qualifiedByName = "getCarById")
    @Mapping(target = "user", source = "requestDto.userId", qualifiedByName = "getUserById")
    public abstract Rental mapToEntity(RentalRequestDto requestDto);

    @Mapping(source = "car", target = "carId")
    @Mapping(target = "userId", expression = "java(rental.getUser())")
    public abstract RentalResponseDto mapToDto(Rental rental);

    @Named("getCarById")
    protected Car getCarById(Long carId) {
        return carService.getById(carId);
    }

    @Named("getUserById")
    protected User getUserById(User userId) {
        return userId;
    }

    protected Long mapCarToId(Car car) {
        return car.getId();
    }
}
