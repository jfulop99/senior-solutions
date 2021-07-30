package activity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
public class ActivityWithTrack extends Activity{

    private int distance;

    private int duration;

    public ActivityWithTrack(LocalDateTime startTime, String description, int distance, int duration) {
        super(startTime, description);
        this.distance = distance;
        this.duration = duration;
    }
}
