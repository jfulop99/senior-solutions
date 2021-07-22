package doggo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
@Data
@Table(name = "dogs")
@NoArgsConstructor
public class Dog {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @NotNull
    @Column(unique = true)
    private String name;

    @NotBlank
    private String breed;

    @Positive
    private int age;

    @Column(name = "favourite_toy")
    private String favouriteToy;

    public Dog(String name, String breed, int age, String favouriteToy) {
        this.name = name;
        this.breed = breed;
        this.age = age;
        this.favouriteToy = favouriteToy;
    }
}
