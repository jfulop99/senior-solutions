package meetingrooms;

import java.util.ArrayList;
import java.util.List;

public class InMemoryMeetingRoomsRepository implements MeetingRoomsRepository{

    private List<MeetingRoom> meetingRooms = new ArrayList<>();

    @Override
    public void add(String name, int width, int length) {
        meetingRooms.add(new MeetingRoom(name, width, length));
    }

    @Override
    public List<MeetingRoom> findAll() {
        return null;
    }

    @Override
    public List<MeetingRoom> findByName(String name) {
        return null;
    }

    @Override
    public List<MeetingRoom> findByPartOfName(String partOfName) {
        return null;
    }

    @Override
    public List<MeetingRoom> findByArea(int area) {
        return null;
    }
}
