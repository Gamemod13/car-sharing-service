package mate.academy.car.sharing.dto.request;

import lombok.Data;
import javax.validation.constraints.NotBlank;

@Data
public class UserLoginDto {
    @NotBlank(message = "Email must not be empty")
    private String email;
    @NotBlank(message = "Password must not be empty")
    private String password;
}
