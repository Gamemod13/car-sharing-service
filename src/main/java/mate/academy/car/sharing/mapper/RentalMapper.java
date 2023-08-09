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
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(config = MapperConfig.class)
public abstract class RentalMapper {
    @Autowired
    private CarService carService;
    @Autowired
    private UserService userService;

    @Mapping(target = "car", source = "requestDto.carId", qualifiedByName = "getCarById")
    @Mapping(target = "user", source = "requestDto.userId", qualifiedByName = "getUserById")
    public abstract Rental mapToEntity(RentalRequestDto requestDto);

    @Mapping(source = "car", target = "carId")
    @Mapping(source = "user", target = "userId")
    public abstract RentalResponseDto mapToDto(Rental rental);

    @Named("getCarById")
    protected Car getCarById(Long carId) {
        return carService.getById(carId);
    }

    @Named("getUserById")
    protected User getUserById(Long userId) {
        return userService.getById(userId);
    }

    protected Long mapCarToId(Car car) {
        return car.getId();
    }

    protected Long mapUserToId(User user) {
        return user.getId();
    }
}
