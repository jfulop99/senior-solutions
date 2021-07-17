package datebooking;

import lombok.Data;

import javax.validation.constraints.Future;
import java.time.LocalDateTime;

@Data
public class FutureInterval {

    @Future(message = "Must be in the future")
    private final LocalDateTime start;

    @Future(message = "Must be in the future")
    private final LocalDateTime end;
}
