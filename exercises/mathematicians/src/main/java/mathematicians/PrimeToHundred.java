package mathematicians;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Constraint(validatedBy = PrimeToHundredValidator.class)
public @interface PrimeToHundred {

    String message() default "0 és 100 közötti prímszámnak kell lennie";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
