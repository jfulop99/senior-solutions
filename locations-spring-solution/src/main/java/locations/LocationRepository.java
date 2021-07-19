package locations;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LocationRepository extends JpaRepository<Location, Long> {
    @Query(value = "select l from Location l where lower(l.name) like ?1 order by l.id")
    List<Location> findAllByName(String name);
}
