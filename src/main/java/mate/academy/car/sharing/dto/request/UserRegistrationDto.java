package mate.academy.car.sharing.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Data;
import mate.academy.car.sharing.validation.Password;

@Password
@Data
public class UserRegistrationDto {
    @Email
    private String email;
    @NotBlank(message = "First name must not be blank")
    @Size(min = 2, max = 30, message = "First name must be at most 30 characters"
            + "and at min 2 characters")
    private String firstName;
    @NotBlank(message = "Last name must not be blank")
    @Size(min = 2, max = 30, message = "Last name must be at most 30 characters"
            + "and at min 2 characters")
    private String lastName;
    private String password;
    private String repeatPassword;
}
