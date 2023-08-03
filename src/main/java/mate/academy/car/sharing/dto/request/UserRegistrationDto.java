package mate.academy.car.sharing.dto.request;

import javax.validation.constraints.Email;
import lombok.Data;
import mate.academy.car.sharing.validation.Password;

@Password
@Data
public class UserRegistrationDto {
    @Email
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String repeatPassword;
}
