package mate.academy.car.sharing.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Map;
import mate.academy.car.sharing.dto.request.UserLoginDto;
import mate.academy.car.sharing.dto.request.UserRegistrationDto;
import mate.academy.car.sharing.dto.response.UserResponseDto;
import mate.academy.car.sharing.entity.User;
import mate.academy.car.sharing.mapper.UserMapper;
import mate.academy.car.sharing.security.AuthenticationService;
import mate.academy.car.sharing.security.jwt.JwtTokenProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import mate.academy.car.sharing.exception.AuthenticationException;

@ExtendWith(MockitoExtension.class)
class AuthenticationControllerTest {
    @Mock
    private AuthenticationService authService;
    @Mock
    private UserMapper userMapper;
    @Mock
    private JwtTokenProvider jwtTokenProvider;
    @InjectMocks
    private AuthenticationController authController;

    @BeforeEach
    public void setUp() {
        authService = mock(AuthenticationService.class);
        jwtTokenProvider = mock(JwtTokenProvider.class);
        userMapper = mock(UserMapper.class);
        authController = new AuthenticationController(authService, jwtTokenProvider, userMapper);
    }

    @Test
    public void testRegister_ValidUserRegistrationDto_ReturnsUserResponseDto() {
        UserRegistrationDto userRegistrationDto = new UserRegistrationDto();
        User user = new User();
        UserResponseDto userResponseDto = new UserResponseDto();

        when(authService.register(any(User.class))).thenReturn(user);
        when(userMapper.mapToEntity(userRegistrationDto)).thenReturn(user);
        when(userMapper.mapToDto(user)).thenReturn(userResponseDto);

        UserResponseDto result = authController.register(userRegistrationDto);

        assertNotNull(result);
        assertEquals(userResponseDto, result);
    }

    @Test
    public void testLogin_ValidUserLoginDto_ReturnsResponseEntityWithToken()
            throws AuthenticationException {
        UserLoginDto userLoginDto = new UserLoginDto();
        userLoginDto.setEmail("test@example.com");
        userLoginDto.setPassword("password");

        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("password");
        user.setRole(User.Role.CUSTOMER);

        when(authService.login("test@example.com", "password")).thenReturn(user);
        when(jwtTokenProvider.createToken(user.getEmail(),
                List.of(user.getRole().name()))).thenReturn("jwt.token");

        ResponseEntity<Object> result = authController.login(userLoginDto);

        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(Map.of("token", "jwt.token"), result.getBody());
    }
}
