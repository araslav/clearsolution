package test.clearsolution.validation.age;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AgeFromLessToValidator implements ConstraintValidator<AgeFromLessTo, Object> {
    private String dateFromKey;
    private String dateToKey;

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Object dateFromObj = new BeanWrapperImpl(value).getPropertyValue(dateFromKey);
        Object dateToObj = new BeanWrapperImpl(value).getPropertyValue(dateToKey);
        LocalDate dateFrom = LocalDate.parse(String.valueOf(dateFromObj), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        LocalDate dateTo = LocalDate.parse(String.valueOf(dateToObj), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        return dateFrom.isBefore(dateTo);
    }

    @Override
    public void initialize(AgeFromLessTo constraintAnnotation) {
        dateFromKey = constraintAnnotation.dateFrom();
        dateToKey = constraintAnnotation.dateTo();
    }
}
