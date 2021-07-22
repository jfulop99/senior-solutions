package doggo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DogRepository extends JpaRepository<Dog, Long> {

    List<Dog> findAllByNameLikeOrderByName(String name);

    Optional<Dog> findById(Long id);

    List<Dog> findAllByBreed(String breed);
}
