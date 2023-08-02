package mate.academy.car.sharing.security;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import mate.academy.car.sharing.entity.User;
import mate.academy.car.sharing.exception.AuthenticationException;
import mate.academy.car.sharing.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User register(String email, String password) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setRole(User.Role.CUSTOMER);
        user = userService.add(user);
        return user;
    }

    @Override
    public User login(String login, String password) throws AuthenticationException {
        Optional<User> user = userService.findByEmail(login);
        if (user.isEmpty() || !passwordEncoder.matches(password, user.get().getPassword())) {
            throw new AuthenticationException("Incorrect username or password.");
        }
        return user.get();
    }
}
