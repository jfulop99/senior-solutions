package meetingrooms;

import java.util.ArrayList;
import java.util.List;

public class InMemoryMeetingRoomsRepository implements MeetingRoomsRepository{

    private List<MeetingRoom> meetingRooms = new ArrayList<>();

    public InMemoryMeetingRoomsRepository() {
    }

    @Override
    public void add(String name, int width, int length) {
        meetingRooms.add(new MeetingRoom(name, width, length));
    }

    @Override
    public List<MeetingRoom> findAll() {
        return new ArrayList<>(meetingRooms);
    }

    @Override
    public void deleteAll() {
        meetingRooms.clear();
    }
}
