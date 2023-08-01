package mate.academy.car.sharing.repository;

import java.util.Optional;
import mate.academy.car.sharing.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
