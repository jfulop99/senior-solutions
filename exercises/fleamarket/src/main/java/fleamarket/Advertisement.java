package fleamarket;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Advertisement {

    private long id;

    private final LumberCategory lumberCategory;

    private String text;

    private LocalDateTime timeStamp;

    public Advertisement(long id, CreateAdvertisementCommand createAdvertisementCommand) {

        lumberCategory = createAdvertisementCommand.getLumberCategory();

        text = createAdvertisementCommand.getText();

        timeStamp = LocalDateTime.now();
    }

    public void setText(String text){
        this.text = text;
        timeStamp = LocalDateTime.now();
    }
}
