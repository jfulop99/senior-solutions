package meetingrooms;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class MeetingRoomsService {

    private MeetingRoomsRepository meetingRoomsRepository;

    public void add(String name, int width, int length){
        meetingRoomsRepository.add(name, width, length);
    }

    public List<MeetingRoom> findAllOrderedByName(){
        return meetingRoomsRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(MeetingRoom::getName))
                .collect(Collectors.toList());
    }

}
