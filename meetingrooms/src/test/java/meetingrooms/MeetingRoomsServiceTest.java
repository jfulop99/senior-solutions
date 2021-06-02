package meetingrooms;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class MeetingRoomsServiceTest {

    private MeetingRoomsService meetingRoomsService = new MeetingRoomsService(new MariaDbMeetingRoomsRepository());
//    private MeetingRoomsService meetingRoomsService = new MeetingRoomsService(new InMemoryMeetingRoomsRepository());

    @BeforeEach
    void setUp() {
        meetingRoomsService.deleteAll();
    }

    @Test
    void add() {
        meetingRoomsService.add("Alfa", 3, 3);

        List<MeetingRoom> result = meetingRoomsService.findAllOrderedByName();
        assertEquals(1, result.size());
        assertEquals("Alfa", result.get(0).getName());
    }

    @Test
    void addTwo() {
        meetingRoomsService.add("Beta", 6, 6);
        meetingRoomsService.add("Alfa", 3, 3);

        List<MeetingRoom> result = meetingRoomsService.findAllOrderedByName();
        assertEquals(2, result.size());
        assertEquals("Beta", result.get(1).getName());
        assertEquals("Alfa", result.get(0).getName());
    }

    @Test
    void findAllOrderedByName() {
        meetingRoomsService.add("Beta", 6, 6);
        meetingRoomsService.add("Alfa", 3, 3);

        List<MeetingRoom> result = meetingRoomsService.findAllOrderedByName();
        assertEquals(2, result.size());
        assertEquals("Beta", result.get(1).getName());
        assertEquals("Alfa", result.get(0).getName());
    }

    @Test
    void findAllReverseOrderedByName() {
        meetingRoomsService.add("Beta", 6, 6);
        meetingRoomsService.add("Alfa", 3, 3);

        List<MeetingRoom> result = meetingRoomsService.findAllReverseOrderedByName();
        assertEquals(2, result.size());
        assertEquals("Beta", result.get(0).getName());
        assertEquals("Alfa", result.get(1).getName());
    }

    @Test
    void everySecondMeetingRooms() {
        meetingRoomsService.add("Alfa", 1, 3);
        meetingRoomsService.add("Beta", 2, 6);
        meetingRoomsService.add("Delta", 3, 6);
        meetingRoomsService.add("Gamma", 4, 6);
        meetingRoomsService.add("Epsilon", 5, 6);

        List<MeetingRoom> result = meetingRoomsService.everySecondMeetingRooms();
        assertEquals(2, result.size());
        assertEquals(2, result.get(0).getWidth());
        assertEquals(4, result.get(1).getWidth());
    }

    @Test
    void getAreas() {
        meetingRoomsService.add("Alfa", 1, 6);
        meetingRoomsService.add("Beta", 2, 6);
        meetingRoomsService.add("Delta", 3, 6);
        meetingRoomsService.add("Gamma", 4, 6);
        meetingRoomsService.add("Epsilon", 5, 6);

        List<MeetingRoom> result = meetingRoomsService.findMeetingRoomsByAreas();
        assertEquals(5, result.size());
        assertEquals(30, result.get(0).getArea());
        assertEquals(24, result.get(1).getArea());
        assertEquals(18, result.get(2).getArea());
        assertEquals(12, result.get(3).getArea());
        assertEquals(6, result.get(4).getArea());
    }

    @Test
    void findMeetingRoomByName() {
        meetingRoomsService.add("Alfa", 1, 6);
        meetingRoomsService.add("Beta", 2, 6);
        meetingRoomsService.add("Delta", 3, 6);
        meetingRoomsService.add("Gamma", 4, 6);
        meetingRoomsService.add("Epsilon", 5, 6);
        meetingRoomsService.add("Alfa Romeo", 6, 6);

        MeetingRoom result = meetingRoomsService.findMeetingRoomByName("Delta");

        assertEquals("Delta", result.getName());
    }

    @Test
    void findMeetingRoomByNameNoResult() {
        meetingRoomsService.add("Alfa", 1, 6);
        meetingRoomsService.add("Beta", 2, 6);
        meetingRoomsService.add("Delta", 3, 6);
        meetingRoomsService.add("Gamma", 4, 6);
        meetingRoomsService.add("Epsilon", 5, 6);
        meetingRoomsService.add("Alfa Romeo", 6, 6);

        Exception ex = assertThrows(NoSuchElementException.class, () -> meetingRoomsService.findMeetingRoomByName("Omega"));
        assertEquals("Nincs ilyen nevű terem!", ex.getMessage());
    }

    @Test
    void findMeetingRoomByPartOfName() {
        meetingRoomsService.add("Alfa", 1, 6);
        meetingRoomsService.add("Beta", 2, 6);
        meetingRoomsService.add("Delta", 3, 6);
        meetingRoomsService.add("Gamma", 4, 6);
        meetingRoomsService.add("Epsilon", 5, 6);
        meetingRoomsService.add("Alfa Romeo", 6, 6);

        List<MeetingRoom> result = meetingRoomsService.findMeetingRoomByPartOfName("lfa");

        assertEquals(2, result.size());

    }

    @Test
    void findMeetingRoomByPartOfNameNoResult() {
        meetingRoomsService.add("Alfa", 1, 6);
        meetingRoomsService.add("Beta", 2, 6);
        meetingRoomsService.add("Delta", 3, 6);
        meetingRoomsService.add("Gamma", 4, 6);
        meetingRoomsService.add("Epsilon", 5, 6);
        meetingRoomsService.add("Alfa Romeo", 6, 6);

        List<MeetingRoom> result = meetingRoomsService.findMeetingRoomByPartOfName("xyz");

        assertEquals(0, result.size());

    }

    @Test
    void findMeetingRoomByArea() {
        meetingRoomsService.add("Alfa", 1, 6);
        meetingRoomsService.add("Beta", 2, 6);
        meetingRoomsService.add("Delta", 3, 6);
        meetingRoomsService.add("Gamma", 4, 6);
        meetingRoomsService.add("Epsilon", 5, 6);
        meetingRoomsService.add("Alfa Romeo", 6, 6);

        List<MeetingRoom> result = meetingRoomsService.findMeetingRoomByArea(23);

        assertEquals(3, result.size());
        assertEquals("Gamma", result.get(0).getName());
        assertEquals("Epsilon", result.get(1).getName());
        assertEquals("Alfa Romeo", result.get(2).getName());

    }

    @Test
    void findMeetingRoomByAreaNoResult() {
        meetingRoomsService.add("Alfa", 1, 6);
        meetingRoomsService.add("Beta", 2, 6);
        meetingRoomsService.add("Delta", 3, 6);
        meetingRoomsService.add("Gamma", 4, 6);
        meetingRoomsService.add("Epsilon", 5, 6);
        meetingRoomsService.add("Alfa Romeo", 6, 6);

        List<MeetingRoom> result = meetingRoomsService.findMeetingRoomByArea(40);

        assertEquals(0, result.size());
    }
}