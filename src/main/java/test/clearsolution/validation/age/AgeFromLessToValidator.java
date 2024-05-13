package test.clearsolution.validation.age;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AgeFromLessToValidator implements ConstraintValidator<AgeFromLessTo, Object> {
    private LocalDate dateFrom;
    private LocalDate dateTo;

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        return dateTo.isBefore(dateFrom);
    }

    @Override
    public void initialize(AgeFromLessTo constraintAnnotation) {
        dateFrom = LocalDate.parse(constraintAnnotation.dateFrom(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        dateTo = LocalDate.parse(constraintAnnotation.dateTo(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
}
