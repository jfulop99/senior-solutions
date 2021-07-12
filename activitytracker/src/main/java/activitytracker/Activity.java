package activitytracker;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "activities")
public class Activity {

    @TableGenerator(name = "Act_Gen",
        table = "act_id_gen",
        pkColumnName = "id_gen",
        valueColumnName = "id_val",
        initialValue = 100)
    @Id
    @GeneratedValue(generator = "Act_Gen")
    private Long id;

    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "activity_desc", nullable = false, length = 200)
    private String desc;

    @Enumerated(EnumType.STRING)
    @Column(name = "activity_type", nullable = false, length = 20)
    private ActivityType type;

    public Activity(LocalDateTime startTime, String desc, ActivityType type) {
        this.startTime = startTime;
        this.desc = desc;
        this.type = type;
    }
}
