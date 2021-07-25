package therapy;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class TherapyDTO {

    private Long id;

    private String therapist;

    private String location;

    private LocalDateTime time;

    private List<String> participants;

}
