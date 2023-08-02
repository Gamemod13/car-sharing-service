package mate.academy.car.sharing.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginDto {
    private String login;
    private String password;
}
