package mate.academy.car.sharing.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegistrationDto {
    private String email;
    private String password;
    private String repeatPassword;
}
