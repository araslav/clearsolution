package test.clearsolution.validation.email;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class EmailValidator implements ConstraintValidator<Email, String> {
    private static final String PATTERN_OF_EMAIL =
            "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        return isValidEmail(email);
    }

    public static boolean isValidEmail(String email) {
        return email != null && Pattern.compile(PATTERN_OF_EMAIL).matcher(email).matches();
    }
}
