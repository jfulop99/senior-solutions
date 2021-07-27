package mathematicians;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class createMathematicianCommand {

    @NotNull
    @NotBlank
    private String name;

    @Past
    private LocalDate birthDay;

    @NotNull
    @NotEmpty
    private List<String> favoriteTopics;

    @PrimeToHundred
    private int favoritePrime;

}
