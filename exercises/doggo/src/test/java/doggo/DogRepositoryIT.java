package doggo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DogRepositoryIT {

    @Autowired
    DogRepository dogRepository;

    @BeforeEach
    void setUp() {
        dogRepository.deleteAll();
        dogRepository.save(new Dog("Rex", "Németjuhász", 3, "labda"));
        dogRepository.save(new Dog("Frakk", "Magyar vizsla", 5, "bot"));
    }

    @Test
    void saveTest(){

        List<Dog> dogs = dogRepository.findAllByNameLikeOrderByName("%");

        assertThat(dogs)
                .hasSize(2)
                .extracting(Dog::getName)
                .containsExactly("Frakk", "Rex");
    }

    @Test
    void saveTestWithNotUniqueName(){

        Exception e = assertThrows(DataIntegrityViolationException.class, () -> dogRepository.save(new Dog("Frakk", "Magyar vizsla", 5, "bot")));

    }

    @Test
    void findAllTest(){

        List<Dog> dogs = dogRepository.findAll();

        assertThat(dogs)
                .hasSize(2)
                .extracting(Dog::getName)
                .contains("Frakk", "Rex");

    }

    @Test
    void findAllByBreedTest() {
        List<Dog> dogs = dogRepository.findAllByBreed("Németjuhász");

        assertThat(dogs)
                .hasSize(1)
                .extracting(Dog::getName)
                .containsExactly("Rex");

        dogs = dogRepository.findAllByBreed("ET");
        assertTrue(dogs.isEmpty());

    }

    @Test
    void findByIdTest() {

        List<Dog> dogs = dogRepository.findAll();

        Long id = dogs.get(1).getId();

        Optional<Dog> dog = dogRepository.findById(id);

        assertEquals("Frakk", dog.get().getName());

        dog = dogRepository.findById(5L);

        assertTrue(dog.isEmpty());

    }
}