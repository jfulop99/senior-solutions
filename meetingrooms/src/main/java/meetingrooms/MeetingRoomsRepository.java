package meetingrooms;

import java.util.List;

public interface MeetingRoomsRepository {

    void add(String name, int width, int length);

    List<MeetingRoom> findAll();

    List<MeetingRoom> findByName(String name);

    List<MeetingRoom> findByPartOfName(String partOfName);

    List<MeetingRoom> findByArea(int area);


}
