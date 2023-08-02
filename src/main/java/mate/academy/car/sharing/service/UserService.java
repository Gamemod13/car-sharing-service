package mate.academy.car.sharing.service;

import java.util.Optional;
import mate.academy.car.sharing.entity.User;

public interface UserService extends AbstractService<User> {
    Optional<User> findByEmail(String email);
}
