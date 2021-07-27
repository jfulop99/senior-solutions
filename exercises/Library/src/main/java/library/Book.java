package library;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String IsbnNumber;

    private String title;

    @ManyToOne(cascade = CascadeType.ALL)
    private Author author;

    public Book(String isbnNumber, String title) {
        IsbnNumber = isbnNumber;
        this.title = title;
    }
}
