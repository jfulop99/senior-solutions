package datebooking;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IntervalValidator implements ConstraintValidator<ValidInterval, FutureInterval> {
    @Override
    public boolean isValid(FutureInterval futureInterval, ConstraintValidatorContext constraintValidatorContext) {
        return futureInterval.getStart().isBefore(futureInterval.getEnd());
    }
}
