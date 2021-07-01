package movies;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MovieControllerRestTest {

    @Autowired
    TestRestTemplate template;

    @Test
    void createMovieTest(){
        MovieDto result = template.postForObject("/api/movies",
                new CreateMovieCommand("Mortal Kombat", 150), MovieDto.class);

        assertEquals("Mortal Kombat", result.getTitle());

        result = template.getForObject("/api/movies/1", MovieDto.class);

        assertEquals(150, result.getPlayingTime());
    }


}
