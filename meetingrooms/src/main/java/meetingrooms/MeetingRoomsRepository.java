package meetingrooms;

import java.util.List;

public interface MeetingRoomsRepository {

    void add(String name, int width, int length);

    List<MeetingRoom> findAll();

    void deleteAll();

}
