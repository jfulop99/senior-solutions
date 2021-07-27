package mathematicians;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "mathematicians")
public class Mathematician {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private LocalDate birthDay;

    @ElementCollection
    private List<String> favoriteTopics;

    private int favoritePrime;

    public Mathematician(String name, LocalDate birthDay, List<String> favoriteTopics, int favoritePrime) {
        this.name = name;
        this.birthDay = birthDay;
        this.favoriteTopics = favoriteTopics;
        this.favoritePrime = favoritePrime;
    }
}
