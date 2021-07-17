package datebooking;

import lombok.Data;

import javax.validation.Valid;

@Data
public class CreateBookingCommand {
    @TaxId
    private String taxId;

    @ValidAffair
    private String codeOfAffair;

    @ValidInterval
    @Valid
    private FutureInterval futureInterval;

}
