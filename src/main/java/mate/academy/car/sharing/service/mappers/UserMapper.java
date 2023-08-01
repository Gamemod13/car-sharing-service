package mate.academy.car.sharing.service.mappers;

import mate.academy.car.sharing.dto.request.UserRegistrationDto;
import mate.academy.car.sharing.dto.response.UserResponseDto;
import mate.academy.car.sharing.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User mapToModel(UserRegistrationDto requestDto) {
        User user = new User();
        return user;
    }

    public UserResponseDto mapToDto(User user) {
        UserResponseDto responseDto = new UserResponseDto();
        return responseDto;
    }
}
