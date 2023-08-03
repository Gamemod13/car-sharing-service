package mate.academy.car.sharing.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import mate.academy.car.sharing.dto.request.UserRegistrationDto;

public class PasswordValidator implements ConstraintValidator<Password, UserRegistrationDto> {
    private static final String PASSWORD_PATTERN = "^.{8,40}$";

    @Override
    public boolean isValid(UserRegistrationDto dto,
            ConstraintValidatorContext constraintValidatorContext) {
        String password = dto.getPassword();
        String repeatedPassword = dto.getRepeatPassword();
        if (dto != null && isPattern(password) && isPattern(repeatedPassword)) {
            return password.equals(repeatedPassword);
        }
        return false;
    }

    private boolean isPattern(String password) {
        if (password != null) {
            Pattern patterns = Pattern.compile(PASSWORD_PATTERN);
            Matcher matcher = patterns.matcher(password);
            return matcher.matches();
        }
        return false;
    }
}
