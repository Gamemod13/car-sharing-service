package mate.academy.car.sharing.dto.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Data
public class UserResponseDto {
    private String email;
    private String firstName;
    private String lastName;
    private String role;
}
