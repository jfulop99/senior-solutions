package fleamarket;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateAdvertisementCommand {

    @NotNull
    private LumberCategory lumberCategory;

    @NotBlank
    private String text;

}
