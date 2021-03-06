package fleamarket;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdvertisementDTO {

    private long id;

    private LumberCategory lumberCategory;

    private String text;

    private LocalDateTime timeStamp;

}
