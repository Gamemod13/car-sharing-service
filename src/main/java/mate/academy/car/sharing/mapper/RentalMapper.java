package mate.academy.car.sharing.mapper;

import mate.academy.car.sharing.config.mappers.MapperConfig;
import mate.academy.car.sharing.dto.request.RentalRequestDto;
import mate.academy.car.sharing.dto.response.RentalResponseDto;
import mate.academy.car.sharing.entity.Rental;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface RentalMapper {
    Rental mapToEntity(RentalRequestDto dto);

    RentalResponseDto mapToDto(Rental rental);
}

