package test.clearsolution.validation.age;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = AgeValidator.class)
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AgeGraterThan {

    String message() default "Age should be grater than or equal to ";

    int minAge() default 0;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
