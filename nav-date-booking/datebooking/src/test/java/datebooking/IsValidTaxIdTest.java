package datebooking;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IsValidTaxIdTest {

    @Test
    void isValid() {
        IsValidTaxId isValidTaxId = new IsValidTaxId();

        assertTrue(isValidTaxId.isValid("0123456789"));

        assertFalse(isValidTaxId.isValid("1234567890"));

    }
}