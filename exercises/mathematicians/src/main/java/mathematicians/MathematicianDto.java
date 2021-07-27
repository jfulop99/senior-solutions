package mathematicians;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MathematicianDto {

    private Long id;

    private String name;

    private LocalDate birthDay;

    private List<String> favoriteTopics;

    private int favoritePrime;

}
