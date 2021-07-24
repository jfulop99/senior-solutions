package therapy;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Therapy therapy = (Therapy) o;

        return Objects.equals(id, therapy.id);
    }

    @Override
    public int hashCode() {
        return 793722864;
    }
}
