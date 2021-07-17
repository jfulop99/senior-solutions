package datebooking;

import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class IsValidTaxId {

    private static final Pattern TEN_DIGIT = Pattern.compile("^\\d{10}$");

    public boolean isValid(String taxId) {
        if (taxId == null || !TEN_DIGIT.matcher(taxId).find()) {
            return false;
        }
        int sum = 0;
        int multiplier = 1;
        for (int i = 0; i < 9; i++) {
            sum += (taxId.charAt(i) - '0') * multiplier;
            multiplier++;
        }
        return sum % 11 == (taxId.charAt(9) - '0');
    }

}
