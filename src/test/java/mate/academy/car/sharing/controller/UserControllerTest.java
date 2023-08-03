package mate.academy.car.sharing.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import mate.academy.car.sharing.dto.request.UserRequestDto;
import mate.academy.car.sharing.dto.response.UserResponseDto;
import mate.academy.car.sharing.entity.User;
import mate.academy.car.sharing.mapper.UserMapper;
import mate.academy.car.sharing.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private UserMapper userMapper;

    @Mock
    private Authentication authentication;

    @InjectMocks
    private UserController userController;

    @Test
    public void testGet_ReturnsUserResponseDto() {
        String email = "user@example.com";
        User user = new User();
        user.setEmail(email);
        when(authentication.getName()).thenReturn(email);
        when(userService.findByEmail(email)).thenReturn(Optional.of(user));
        when(userMapper.mapToDto(user)).thenReturn(new UserResponseDto());

        UserResponseDto result = userController.get(authentication);

        assertNotNull(result);
    }

    @Test
    public void testUpdateRole_ReturnsUserResponseDto() {
        Long userId = 1L;
        String role = "MANAGER";
        User user = new User();
        when(userService.updateUserRole(userId, role)).thenReturn(user);
        when(userMapper.mapToDto(user)).thenReturn(new UserResponseDto());

        UserResponseDto result = userController.updateRole(userId, role);

        assertNotNull(result);
    }

    @Test
    public void testUpdateUser_ReturnsUserResponseDto() {
        String email = "user@example.com";
        UserRequestDto userRequestDto = new UserRequestDto();
        User user = new User();
        when(authentication.getName()).thenReturn(email);
        when(userService.updateProfileInfo(email, userRequestDto)).thenReturn(user);
        when(userMapper.mapToDto(user)).thenReturn(new UserResponseDto());

        UserResponseDto result = userController.updateUser(authentication, userRequestDto);

        assertNotNull(result);
    }
}
