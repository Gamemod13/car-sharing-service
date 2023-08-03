package mate.academy.car.sharing.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import mate.academy.car.sharing.dto.request.UserRequestDto;
import mate.academy.car.sharing.entity.User;
import mate.academy.car.sharing.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder encoder;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addNewUserTest() {
        User user = new User();
        user.setId(1L);
        user.setEmail("test@example.com");
        user.setPassword("testPassword");
        when(userRepository.save(user)).thenReturn(user);
        User addedUser = userService.add(user);
        assertNotNull(addedUser);
        assertEquals(user, addedUser);
    }

    @Test
    void getByIdExistingUserTest() {
        Long userId = 1L;
        User user = new User();
        user.setId(userId);
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        User retrievedUser = userService.getById(userId);
        assertNotNull(retrievedUser);
        assertEquals(user, retrievedUser);
    }

    @Test
    void getByIdNonExistingUserTest() {
        Long userId = 1L;
        when(userRepository.findById(userId)).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> userService.getById(userId));
    }

    @Test
    void getAllUsersTest() {
        User user1 = new User();
        User user2 = new User();
        List<User> userList = List.of(user1, user2);
        when(userRepository.findAll()).thenReturn(userList);
        List<User> retrievedUsers = userService.getAll();
        assertNotNull(retrievedUsers);
        assertEquals(2, retrievedUsers.size());
        assertEquals(user1, retrievedUsers.get(0));
        assertEquals(user2, retrievedUsers.get(1));
    }

    @Test
    void deleteUserTest() {
        Long userId = 1L;
        userService.delete(userId);
        verify(userRepository).deleteById(userId);
    }

    @Test
    void findByEmailExistingUserTest() {
        String email = "test@example.com";
        User user = new User();
        user.setEmail(email);
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));
        Optional<User> retrievedUser = userService.findByEmail(email);
        assertNotNull(retrievedUser);
        assertEquals(user, retrievedUser.get());
    }

    @Test
    void findByEmailNonExistingUserTest() {
        String email = "test@example.com";
        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());
        Optional<User> retrievedUser = userService.findByEmail(email);
        assertNotNull(retrievedUser);
        assertEquals(Optional.empty(), retrievedUser);
    }

    @Test
    void updateUserRoleTest() {
        Long userId = 1L;
        String role = "MANAGER";
        User user = new User();
        user.setId(userId);
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        User updatedUser = userService.updateUserRole(userId, role);
        assertNotNull(updatedUser);
        assertEquals(user, updatedUser);
        assertEquals(User.Role.MANAGER, updatedUser.getRole());
    }

    @Test
    void updateProfileInfoTest() {
        String email = "test@example.com";
        UserRequestDto dto = new UserRequestDto();
        dto.setEmail(email);
        dto.setFirstName("John");
        dto.setLastName("Doe");
        dto.setPassword("newPassword");
        User user = new User();
        user.setEmail(email);
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));
        when(encoder.encode(dto.getPassword())).thenReturn("encodedPassword");
        User updatedUser = userService.updateProfileInfo(email, dto);
        assertNotNull(updatedUser);
        assertEquals(user, updatedUser);
        assertEquals(email, updatedUser.getEmail());
        assertEquals("John", updatedUser.getFirstName());
        assertEquals("Doe", updatedUser.getLastName());
        assertEquals("encodedPassword", updatedUser.getPassword());
    }

    @Test
    void updateUserTest() {
        User user = new User();
        user.setId(1L);
        when(userRepository.save(user)).thenReturn(user);
        User updatedUser = userService.update(user);
        assertNotNull(updatedUser);
        assertEquals(user, updatedUser);
    }
}
