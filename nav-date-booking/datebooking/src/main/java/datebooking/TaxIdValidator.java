package datebooking;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class TaxIdValidator implements ConstraintValidator<TaxId, String> {



    @Override
    public boolean isValid(String taxId, ConstraintValidatorContext constraintValidatorContext) {
        IsValidTaxId isValidTaxId = new IsValidTaxId();
        return isValidTaxId.isValid(taxId);
    }
}
