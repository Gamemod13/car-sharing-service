package mate.academy.car.sharing.security;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;
import mate.academy.car.sharing.entity.User;
import mate.academy.car.sharing.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

class CustomUserDetailsServiceTest {
    private static final String EMAIL = "aboba@example.com";
    private static final String PASSWORD = "123456";
    private UserDetailsService userDetailsService;
    private UserService userService;
    private User user;

    @BeforeEach
    void setUp() {
        userService = mock(UserService.class);
        userDetailsService = new CustomUserDetailsService(userService);
        user = new User();
        user.setEmail(EMAIL);
        user.setRole(User.Role.CUSTOMER);
        user.setPassword(PASSWORD);
    }

    @Test
    void loadUserByUsername_Ok() {
        when(userService.findByEmail(EMAIL)).thenReturn(Optional.of(user));
        UserDetails actual = userDetailsService.loadUserByUsername(EMAIL);
        assertNotNull(actual);
        assertEquals(EMAIL, actual.getUsername());
        assertEquals(PASSWORD, actual.getPassword());
    }

    @Test
    void loadUserByUsername_notOk() {
        User user = new User();
        when(userService.findByEmail(EMAIL)).thenReturn(Optional.of(user));
        assertThrows(UsernameNotFoundException.class, () ->
                userDetailsService.loadUserByUsername("ldkandawlnd"),
                "User not found.");
    }
}
