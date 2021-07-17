package datebooking;

import lombok.AllArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@AllArgsConstructor
public class AffairValidator implements ConstraintValidator<ValidAffair, String> {

    private DateBookingService dateBookingService;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return dateBookingService.getTypeOfAffairs()
                .stream()
                .map(TypeOfAffair::getCode)
                .anyMatch(code -> code.equals(s));
    }
}
