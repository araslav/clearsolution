package test.clearsolution.validation.age;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import java.time.LocalDate;

@RequiredArgsConstructor
public class AgeValidator implements ConstraintValidator<AgeGraterThan, LocalDate> {
    private int minAge;
    private String errorMessage;
    private final Environment environment;

    @Override
    public boolean isValid(LocalDate birthDay, ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(errorMessage)
                .addConstraintViolation();
        return birthDay.plusYears(minAge).isBefore(LocalDate.now());
    }

    @Override
    public void initialize(AgeGraterThan constraintAnnotation) {
        String minAgeEnv = environment.getProperty("validation.minAge");
        minAge = minAgeEnv != null ? Integer.parseInt(minAgeEnv) : constraintAnnotation.minAge();
        errorMessage = constraintAnnotation.message() + minAgeEnv;
    }
}
