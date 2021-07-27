package mathematicians;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class PrimeToHundredValidator implements ConstraintValidator<PrimeToHundred, Integer> {

    private final static List<Integer> primes =
            List.of(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97);

    @Override
    public boolean isValid(Integer prime, ConstraintValidatorContext constraintValidatorContext) {
        return primes.contains(prime);
    }
}
