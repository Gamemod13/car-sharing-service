package mate.academy.car.sharing.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import mate.academy.car.sharing.dto.request.UserRequestDto;
import mate.academy.car.sharing.entity.User;
import mate.academy.car.sharing.repository.UserRepository;
import mate.academy.car.sharing.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    @Override
    public User add(User user) {
        String encodedPass = encoder.encode(user.getPassword());
        user.setPassword(encodedPass);
        return userRepository.save(user);
    }

    @Override
    public User getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() ->
                        new NoSuchElementException("Can't find user by id: " + id));
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    @Transactional
    public User updateUserRole(Long id, String role) {
        User userFromDb = userRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Not found user with id: " + id)
        );
        userFromDb.setRole(User.Role.valueOf(role));
        return userFromDb;
    }

    @Override
    @Transactional
    public User updateProfileInfo(String email, UserRequestDto dto) {
        User userFromDb = userRepository.findByEmail(email).orElseThrow(
                () -> new NoSuchElementException(
                        "Not found profile info for user with email: " + email));
        userFromDb.setEmail(dto.getEmail());
        userFromDb.setFirstName(dto.getFirstName());
        userFromDb.setLastName(dto.getLastName());
        userFromDb.setPassword(encoder.encode(dto.getPassword()));
        return userFromDb;
    }

    @Override
    public User update(User user) {
        return userRepository.save(user);
    }
}
