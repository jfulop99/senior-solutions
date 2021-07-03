package meetingrooms;

import java.util.List;

public interface MeetingRoomsRepository {

    void add(String name, int width, int length);

    List<MeetingRoom> findAll();

    List<MeetingRoom> findAllOrderedByName();

    List<MeetingRoom> findAllReverseOrderedByName();

    List<MeetingRoom> everySecondMeetingRooms();

    List<MeetingRoom> findMeetingRoomsByAreas();

    MeetingRoom findMeetingRoomByName(String name);

    List<MeetingRoom> findMeetingRoomByPartOfName(String partOfName);

    List<MeetingRoom> findMeetingRoomByArea(int area);

    void deleteAll();

}
