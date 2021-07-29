package activitytracker;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Area {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    @MapKey(name = "name")
    private Map<String, City> cities = new HashMap<>();

    @ManyToMany
    private List<Activity> activities = new ArrayList<>();

    public Area(String name) {
        this.name = name;
    }

    public void addActivity(Activity activity){
        activities.add(activity);
        activity.getAreas().add(this);
    }
}
