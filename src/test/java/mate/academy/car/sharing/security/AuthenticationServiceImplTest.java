package mate.academy.car.sharing.security;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;
import mate.academy.car.sharing.entity.User;
import mate.academy.car.sharing.exception.AuthenticationException;
import mate.academy.car.sharing.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AuthenticationServiceImplTest {
    private static final String EMAIL = "aboba@example.com";
    private static final String PASSWORD = "123456";
    private static final User.Role ROLE = User.Role.CUSTOMER;
    private User user;
    private UserService userService;
    private AuthenticationService authenticationService;

    @BeforeEach
    void setUp() {
        userService = mock(UserService.class);
        authenticationService = new AuthenticationServiceImpl(userService);
        user = new User();
        user.setEmail(EMAIL);
        user.setPassword(PASSWORD);
        user.setRole(ROLE);
    }

    @Test
    void register_Ok() {
        when(userService.add(any())).thenReturn(user);
        User actual = authenticationService.register(user);
        assertNotNull(actual);
        assertEquals(EMAIL, actual.getEmail());
        assertEquals(PASSWORD, actual.getPassword());
    }

    @Test
    void login_Ok() {
        when(userService.findByEmail(EMAIL)).thenReturn(Optional.of(user));
        Optional<User> actual = Optional.ofNullable(assertDoesNotThrow(() ->
                authenticationService.login(EMAIL, PASSWORD)));
        assertFalse(actual.isEmpty());
        assertEquals(EMAIL, actual.get().getEmail());
        assertEquals(PASSWORD, actual.get().getPassword());
    }

    @Test
    void login_invalidCredentials_notOk() {
        when(userService.findByEmail(EMAIL)).thenReturn(Optional.of(user));
        assertThrows(AuthenticationException.class, () ->
                        authenticationService.login(EMAIL, "1"),
                "Incorrect username or password.");
    }
}
