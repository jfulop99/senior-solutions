package jpaspringboottal;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
public class SimpleActivity extends Activity {

    private String place;

    public SimpleActivity(LocalDateTime startTime, String description, String place) {
        super(startTime, description);
        this.place = place;
    }
}
