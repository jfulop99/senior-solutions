package datebooking;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookedDate {

    private String taxId;

    private String codeOfAffair;

    private FutureInterval futureInterval;
}
