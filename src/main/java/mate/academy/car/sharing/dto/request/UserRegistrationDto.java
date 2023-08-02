package mate.academy.car.sharing.dto.request;

import lombok.Data;

@Data
public class UserRegistrationDto {
    private String email;
    private String password;
    private String repeatPassword;
}
