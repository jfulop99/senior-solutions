package datebooking;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookedInterval {

    private LocalDateTime startTime;

    private LocalDateTime endTime;


}
