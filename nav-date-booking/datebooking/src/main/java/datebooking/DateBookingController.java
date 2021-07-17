package datebooking;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@Data
@AllArgsConstructor
public class DateBookingController {

    private final DateBookingService dateBookingService;

    @GetMapping("/api/types")
    public List<TypeOfAffair> getTypes(){
        return dateBookingService.getTypeOfAffairs();
    }

    @PostMapping("/api/appointments")
    public BookedDateDto createBooking(@Valid @RequestBody CreateBookingCommand command){

        return dateBookingService.createBooking(command);

    }

    @GetMapping("api/affairs")
    public List<BookedDateDto> getAffairs(){
        return dateBookingService.getAffairs();
    }
}
