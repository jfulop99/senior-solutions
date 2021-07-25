package therapy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TherapyRepositoryIT {

    @Autowired
    TherapyRepository therapyRepository;


    @BeforeEach
    void setUp(){
        therapyRepository.deleteAll();
    }


    @Test
    void saveTest(){
        Therapy therapy = new Therapy("Kis Pista", "1. kezelő", LocalDateTime.of(2021, 8, 1, 13, 0));
        therapyRepository.save(therapy);
        Long id = therapy.getId();

        Therapy otherTherapy = therapyRepository.findById(id).orElseThrow();

        assertEquals("Kis Pista", otherTherapy.getTherapist());

    }
}
