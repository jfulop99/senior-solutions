package datebooking;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class DateBookingService {

    private AtomicLong id = new AtomicLong();

    private List<TypeOfAffair> typeOfAffairs = new ArrayList<>();

    private List<BookedDates> bookedDates = new ArrayList<>();

}
