package datebooking;

import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Service
public class DateBookingService {

    private List<TypeOfAffair> typeOfAffairs = new ArrayList<>(List.of(new TypeOfAffair("001", "Adóbevallás"),
            new TypeOfAffair("002", "Befizetés")));

    private List<BookedDate> bookedDates = new ArrayList<>();

    private ModelMapper modelMapper;

    public DateBookingService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public BookedDateDto createBooking(CreateBookingCommand command) {
        BookedDate bookedDate = new BookedDate(command.getTaxId(), command.getCodeOfAffair(), command.getFutureInterval());
        bookedDates.add(bookedDate);
        return modelMapper.map(bookedDate, BookedDateDto.class);
    }

    public List<BookedDateDto> getAffairs() {
        return bookedDates.stream()
                .map(b -> modelMapper.map(b, BookedDateDto.class))
                .collect(Collectors.toList());
    }
}
