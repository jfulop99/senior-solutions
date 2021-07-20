package meetingrooms;

import org.junit.jupiter.api.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class JpaMeetingRoomRepositoryIT {

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pu");

    MeetingRoomRepository meetingRoomRepository = new JpaMeetingRoomRepository(entityManagerFactory);

    @Test
    void add() {
        meetingRoomRepository.add("Első", 1, 1);
        meetingRoomRepository.add("Második", 2, 2);
        meetingRoomRepository.add("Harmadik", 3, 3);

        List<String> meetingRooms = meetingRoomRepository.findAll();

        assertThat(meetingRooms)
                .hasSize(3)
                .containsExactly("Első", "Második", "Harmadik");
    }

    @Test
    void findAll() {
    }
}