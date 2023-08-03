package mate.academy.car.sharing.security;

import mate.academy.car.sharing.entity.User;
import mate.academy.car.sharing.exception.AuthenticationException;

public interface AuthenticationService {
    User register(User user);

    User login(String login, String password) throws AuthenticationException;
}
