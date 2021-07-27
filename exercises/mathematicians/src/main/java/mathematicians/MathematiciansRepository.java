package mathematicians;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MathematiciansRepository extends JpaRepository<Mathematician, Long> {
}
