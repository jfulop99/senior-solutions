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
    void saveTest() {
        MeetingRoom meetingRoom = meetingRoomRepository.save("Első", 1, 1);

        assertEquals("Első", meetingRoom.getName());
    }

    @Test
    void getMeetingroomsOrderedByNameTest() {
        meetingRoomRepository.save("Első", 1, 1);
        meetingRoomRepository.save("Második", 2, 2);
        meetingRoomRepository.save("Harmadik", 3, 3);

        List<String> meetingRooms = meetingRoomRepository.getMeetingroomsOrderedByName();

        assertThat(meetingRooms)
                .hasSize(3)
                .containsExactly("Első", "Harmadik", "Második");
    }

    @Test
    void getEverySecondMeetingRoomTest(){
        meetingRoomRepository.save("Első", 1, 1);
        meetingRoomRepository.save("XMásodik", 2, 2);
        meetingRoomRepository.save("Harmadik", 3, 3);
        meetingRoomRepository.save("Negyedik", 4, 4);

        List<String> meetingRooms = meetingRoomRepository.getEverySecondMeetingRoom();

        assertThat(meetingRooms)
                .hasSize(2)
                .containsExactly("Negyedik", "XMásodik");

    }

    @Test
    void getMeetingRoomsTest(){
        meetingRoomRepository.save("Első", 1, 1);
        meetingRoomRepository.save("Második", 2, 2);
        meetingRoomRepository.save("Harmadik", 3, 3);

        List<MeetingRoom> meetingRooms = meetingRoomRepository.getMeetingRooms();

        assertThat(meetingRooms)
                .hasSize(3)
                .extracting(MeetingRoom::getName)
                .containsExactly("Első", "Második", "Harmadik");

    }

    @Test
    void getExactMeetingRoomByNameTest(){
        meetingRoomRepository.save("Első", 1, 1);
        meetingRoomRepository.save("Második", 2, 2);
        meetingRoomRepository.save("Harmadik", 3, 3);
        meetingRoomRepository.save("első", 6, 6);

        List<MeetingRoom> meetingRooms = meetingRoomRepository.getExactMeetingRoomByName("Első");

        assertThat(meetingRooms)
                .hasSize(2)
                .extracting(MeetingRoom::getName)
                .containsExactly("Első", "első");


    }

    @Test
    void getMeetingRoomsByPrefixTest(){
        meetingRoomRepository.save("Első", 1, 1);
        meetingRoomRepository.save("Második", 2, 2);
        meetingRoomRepository.save("Harmadik", 3, 3);
        meetingRoomRepository.save("első", 6, 6);

        List<MeetingRoom> meetingRooms = meetingRoomRepository.getMeetingRoomsByPrefix("els");

        assertThat(meetingRooms)
                .hasSize(2)
                .extracting(MeetingRoom::getName)
                .containsExactly("Első", "első");

    }

    @Test
    void deleteAllTest(){
        meetingRoomRepository.save("Első", 1, 1);
        meetingRoomRepository.save("Második", 2, 2);
        meetingRoomRepository.save("Harmadik", 3, 3);

        List<MeetingRoom> meetingRooms = meetingRoomRepository.getMeetingRooms();

        assertEquals(3, meetingRooms.size());

        meetingRoomRepository.deleteAll();

        meetingRooms = meetingRoomRepository.getMeetingRooms();

        assertEquals(0, meetingRooms.size());

    }

}