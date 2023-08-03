package mate.academy.car.sharing.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import mate.academy.car.sharing.dto.request.UserRequestDto;
import mate.academy.car.sharing.dto.response.UserResponseDto;
import mate.academy.car.sharing.entity.User;
import mate.academy.car.sharing.mapper.UserMapper;
import mate.academy.car.sharing.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final UserMapper mapper;

    @Operation(summary = "Get current user info", description = "Get current user info")
    @GetMapping("/me")
    public UserResponseDto get(Authentication auth) {
        String email = auth.getName();
        User user = userService.findByEmail(email).orElseThrow(
                () -> new RuntimeException("User with email " + email + " not found"));
        return mapper.mapToDto(user);
    }

    @Operation(summary = "Update user role", description = "Update user role")
    @PutMapping("{id}/role")
    public UserResponseDto updateRole(@PathVariable Long id, @RequestBody String role) {
        return mapper.mapToDto(userService.updateUserRole(id, role));
    }

    @Operation(summary = "Update current user", description = "Update current user")
    @PutMapping("/me")
    public UserResponseDto updateUser(Authentication auth, @RequestBody UserRequestDto dto) {
        String email = auth.getName();
        return mapper.mapToDto(userService.updateProfileInfo(email, dto));
    }
}
