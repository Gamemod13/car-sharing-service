package mate.academy.car.sharing.mapper;

import mate.academy.car.sharing.config.mappers.MapperConfig;
import mate.academy.car.sharing.dto.request.CarRequestDto;
import mate.academy.car.sharing.dto.response.CarResponseDto;
import mate.academy.car.sharing.entity.Car;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface CarMapper {
    @Mapping(target = "type", expression = "java(car.getType().name())")
    CarResponseDto toDto(Car car);

    @Mapping(target = "type", expression = "java(Car.CarType.valueOf(carRequestDto.getType()))")
    Car toEntity(CarRequestDto carRequestDto);
}
