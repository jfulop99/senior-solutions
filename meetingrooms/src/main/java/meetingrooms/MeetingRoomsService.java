package meetingrooms;

import java.util.List;

public class MeetingRoomsService {

    private MeetingRoomsRepository meetingRoomsRepository;

//    private Collator collator = Collator.getInstance(new Locale("hu", "HU"));

    public MeetingRoomsService(MeetingRoomsRepository meetingRoomsRepository) {
        this.meetingRoomsRepository = meetingRoomsRepository;
    }

    public void add(String name, int width, int length){
        meetingRoomsRepository.add(name, width, length);
    }

    public List<MeetingRoom> findAllOrderedByName(){
        return meetingRoomsRepository.findAllOrderedByName();
    }

    public List<MeetingRoom> findAllReverseOrderedByName(){
        return meetingRoomsRepository.findAllReverseOrderedByName();
    }

    public List<MeetingRoom> everySecondMeetingRooms(){
        return meetingRoomsRepository.everySecondMeetingRooms();
    }

    public List<MeetingRoom> findMeetingRoomsByAreas(){
        return meetingRoomsRepository.findMeetingRoomsByAreas();
    }

    public MeetingRoom findMeetingRoomByName(String name) {
        return meetingRoomsRepository.findMeetingRoomByName(name);
    }

    public List<MeetingRoom> findMeetingRoomByPartOfName(String partOfName) {
        return meetingRoomsRepository.findMeetingRoomByPartOfName(partOfName);
    }

    public List<MeetingRoom> findMeetingRoomByArea(int area) {
        return meetingRoomsRepository.findMeetingRoomByArea(area);
    }

    public void deleteAll() {
        meetingRoomsRepository.deleteAll();
    }
}
