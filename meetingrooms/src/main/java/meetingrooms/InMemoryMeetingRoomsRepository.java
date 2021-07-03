package meetingrooms;

import java.text.Collator;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class InMemoryMeetingRoomsRepository implements MeetingRoomsRepository{

    private List<MeetingRoom> meetingRooms = new ArrayList<>();

    private Collator collator = Collator.getInstance(new Locale("hu", "HU"));


    @Override
    public void add(String name, int width, int length) {
        meetingRooms.add(new MeetingRoom(name, width, length));
    }

    @Override
    public List<MeetingRoom> findAll() {
        return new ArrayList<>(meetingRooms);
    }

    @Override
    public List<MeetingRoom> findAllOrderedByName(){
        return meetingRooms
                .stream()
                .sorted(Comparator.comparing(MeetingRoom::getName, collator))
                .collect(Collectors.toList());
    }

    @Override
    public List<MeetingRoom> findAllReverseOrderedByName(){
        return meetingRooms
                .stream()
                .sorted(Comparator.comparing(MeetingRoom::getName, collator).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public List<MeetingRoom> everySecondMeetingRooms(){
        return IntStream.range(0, meetingRooms.size())
                .filter(n -> (n + 1) % 2 == 0)
                .mapToObj(meetingRooms::get)
                .sorted(Comparator.comparing(MeetingRoom::getName, collator))
                .collect(Collectors.toList());
    }

    @Override
    public List<MeetingRoom> findMeetingRoomsByAreas(){
        return meetingRooms
                .stream()
                .sorted(Comparator.comparing(MeetingRoom::getArea).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public MeetingRoom findMeetingRoomByName(String name) {
        return meetingRooms
                .stream()
                .filter(meetingRoom -> meetingRoom.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Nincs ilyen nevű terem!"));
    }

    @Override
    public List<MeetingRoom> findMeetingRoomByPartOfName(String partOfName) {
        return meetingRooms
                .stream()
                .filter(meetingRoom -> meetingRoom.getName().contains(partOfName))
                .collect(Collectors.toList());
    }

    @Override
    public List<MeetingRoom> findMeetingRoomByArea(int area) {
        return meetingRooms
                .stream()
                .filter(meetingRoom -> meetingRoom.getArea() > area)
                .collect(Collectors.toList());
    }



    @Override
    public void deleteAll() {
        meetingRooms.clear();
    }
}
