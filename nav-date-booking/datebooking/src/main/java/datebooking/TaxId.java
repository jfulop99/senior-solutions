package datebooking;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Constraint(validatedBy = TaxIdValidator.class)
public @interface TaxId {

    String message() default "Invalid TAX id";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
