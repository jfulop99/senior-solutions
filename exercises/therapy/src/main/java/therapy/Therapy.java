package therapy;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Therapy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String therapist;

    private String location;

    private LocalDateTime time;

    @ElementCollection
    @CollectionTable(name = "participants", joinColumns = @JoinColumn(name = "therapy_id"))
    @Column(name = "participant")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id")
    private List<String> participants;

    public Therapy(String therapist, String location, LocalDateTime time) {
        this.therapist = therapist;
        this.location = location;
        this.time = time;
    }

}
