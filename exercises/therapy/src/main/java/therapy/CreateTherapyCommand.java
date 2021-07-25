package therapy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateTherapyCommand {

    @NotNull
    @NotBlank
    private String therapist;

    @NotNull
    @NotBlank
    private String location;

    @NotNull
    @Future
    private LocalDateTime time;

}
