package test.clearsolution.validation.age;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = AgeFromLessToValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface AgeFromLessTo {

    String message() default "Age should be grater than or equal to ";

    String dateFrom();

    String dateTo();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
