package datebooking;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TypeOfAffair {

    private Long id;

    private String code;

    private String name;

    public TypeOfAffair(String code, String name) {
        this.code = code;
        this.name = name;
    }
}
