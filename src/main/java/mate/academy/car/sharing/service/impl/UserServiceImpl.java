package mate.academy.car.sharing.service.impl;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import mate.academy.car.sharing.entity.User;
import mate.academy.car.sharing.repository.UserRepository;
import mate.academy.car.sharing.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User update(User user) {
        User userFromDb = userRepository.findById(user.getId()).orElseThrow(
                () -> new RuntimeException("There is no user with this id"));
        userFromDb.setEmail(user.getEmail());
        userFromDb.setRole(user.getRole());
        userFromDb.setFirstName(user.getFirstName());
        userFromDb.setLastName(user.getLastName());
        return userRepository.save(userFromDb);
    }

    @Override
    public User add(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getById(Long id) {
        return userRepository.getReferenceById(id);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
