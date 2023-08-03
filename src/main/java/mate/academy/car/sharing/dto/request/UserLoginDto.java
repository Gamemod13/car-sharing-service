package mate.academy.car.sharing.dto.request;

import lombok.Data;

@Data
public class UserLoginDto {
    private String email;
    private String password;
}
