package mate.academy.car.sharing.controller;

import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import mate.academy.car.sharing.dto.request.UserLoginDto;
import mate.academy.car.sharing.dto.request.UserRegistrationDto;
import mate.academy.car.sharing.dto.response.UserResponseDto;
import mate.academy.car.sharing.entity.User;
import mate.academy.car.sharing.exception.AuthenticationException;
import mate.academy.car.sharing.security.AuthenticationService;
import mate.academy.car.sharing.security.jwt.JwtTokenProvider;
import mate.academy.car.sharing.service.mappers.UserMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserMapper userMapper;

    @PostMapping("/register")
    public UserResponseDto register(@RequestBody UserRegistrationDto userRequestDto) {
        User user = authenticationService.register(userRequestDto.getEmail(),
                userRequestDto.getPassword());
        return userMapper.mapToDto(user);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody UserLoginDto userLoginDto)
            throws AuthenticationException {
        User user =
                authenticationService.login(userLoginDto.getLogin(), userLoginDto.getPassword());
        String token =
                jwtTokenProvider.createToken(user.getEmail(), List.of(user.getRole().name()));
        return new ResponseEntity<>(Map.of("token", token), HttpStatus.OK);
    }
}

