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
@Table(name = "trackpoints")
public class TrackPoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "point_time", nullable = false)
    private LocalDateTime time;

    @Column(nullable = false)
    private double lat;

    @Column(nullable = false)
    private double lon;

    @ManyToOne
    @JoinColumn(name = "act_id")
    private Activity activity;

    public TrackPoint(LocalDateTime time, double lat, double lon) {
        this.time = time;
        this.lat = lat;
        this.lon = lon;
    }
}
