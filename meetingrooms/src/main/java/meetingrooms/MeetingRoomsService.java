package meetingrooms;

import java.text.Collator;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MeetingRoomsService {

    private MeetingRoomsRepository meetingRoomsRepository;

    private Collator collator = Collator.getInstance(new Locale("hu", "HU"));

    public MeetingRoomsService(MeetingRoomsRepository meetingRoomsRepository) {
        this.meetingRoomsRepository = meetingRoomsRepository;
    }

    public void add(String name, int width, int length){
        meetingRoomsRepository.add(name, width, length);
    }

    public List<MeetingRoom> findAllOrderedByName(){
        return meetingRoomsRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(MeetingRoom::getName, collator))
                .collect(Collectors.toList());
    }

    public List<MeetingRoom> findAllReverseOrderedByName(){
        return meetingRoomsRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(MeetingRoom::getName, collator).reversed())
                .collect(Collectors.toList());
    }

    public List<MeetingRoom> everySecondMeetingRooms(){
        List<MeetingRoom> result = meetingRoomsRepository.findAll();
        return IntStream.range(0, result.size())
                .filter(n -> (n + 1) % 2 == 0)
                .mapToObj(result::get)
                .sorted(Comparator.comparing(MeetingRoom::getName, collator))
                .collect(Collectors.toList());
    }

    public List<MeetingRoom> findMeetingRoomsByAreas(){
        return meetingRoomsRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(MeetingRoom::getArea).reversed())
                .collect(Collectors.toList());
    }

    public MeetingRoom findMeetingRoomByName(String name) {
        return meetingRoomsRepository.findAll()
                .stream()
                .filter(meetingRoom -> meetingRoom.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Nincs ilyen nevű terem!"));
    }

    public List<MeetingRoom> findMeetingRoomByPartOfName(String partOfName) {
        return meetingRoomsRepository.findAll()
                .stream()
                .filter(meetingRoom -> meetingRoom.getName().contains(partOfName))
                .collect(Collectors.toList());
    }

    public List<MeetingRoom> findMeetingRoomByArea(int area) {
        return meetingRoomsRepository.findAll()
                .stream()
                .filter(meetingRoom -> meetingRoom.getArea() > area)
                .collect(Collectors.toList());
    }

    public void deleteAll() {
        meetingRoomsRepository.deleteAll();
    }
}
