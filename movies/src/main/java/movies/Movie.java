package movies;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@RequiredArgsConstructor
public class Movie {

    private final long id;
    private final String title;
    private final int playingTime;
    private List<Integer> ratings = new ArrayList<>();
    private double averageRating;

    public void addRating(int rating){
        if (rating < 0 || rating > 5) {
            throw new IllegalArgumentException("Rating must be between 0 and 5");
        }
        ratings.add(rating);
        averageRating = ratings.stream()
                .mapToInt(Integer::intValue)
                .summaryStatistics()
                .getAverage();
    }
}
