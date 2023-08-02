package mate.academy.car.sharing.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDto {
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String role;
}
