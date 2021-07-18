package activitytracker;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "activities")
@NamedQuery(name = "findTrackPointCoordinatesByDate",
        query = "select new activitytracker.Coordinate(t.lat, t.lon) from Activity a join a.trackPoints t where a.startTime > :afterThis order by a.id , t.id")
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

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ElementCollection
    @CollectionTable(name = "labels", joinColumns = @JoinColumn(name = "activity_id"))
    @Column(name = "activity_label")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id")
    private List<String> labels;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "activity")
    @OrderBy("time")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<TrackPoint> trackPoints;

    public Activity(LocalDateTime startTime, String desc, ActivityType type) {
        this.startTime = startTime;
        this.desc = desc;
        this.type = type;
    }

    public void addTrackPoint(TrackPoint trackPoint){
        if (trackPoints == null) {
            trackPoints = new ArrayList<>();
        }
        trackPoints.add(trackPoint);
        trackPoint.setActivity(this);
    }

    @PrePersist
    public void debugPersist(){
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void debugUpdate(){
        this.updatedAt = LocalDateTime.now();
    }

}
