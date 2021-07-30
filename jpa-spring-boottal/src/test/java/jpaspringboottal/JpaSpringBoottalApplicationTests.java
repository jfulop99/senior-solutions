package jpaspringboottal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class JpaSpringBoottalApplicationTests {

    @Autowired
    ActivityDao activityDao;

    @Test
    void activityDaoTest() {

        activityDao.saveActivity(new Activity(LocalDateTime.of(2021,7,1,12,0), "Séta"));
        activityDao.saveActivity(new SimpleActivity(LocalDateTime.of(2020,8,1,12,0), "Futás", "Budapest"));
        activityDao.saveActivity(new ActivityWithTrack(LocalDateTime.of(2020,9,1,12,0), "Biciklizés", 25, 3600));


        Activity activity = activityDao.findActivityByDescription("Séta");
        assertEquals("Séta", activity.getDescription());

        Activity simpleActivity = activityDao.findActivityByDescription("Futás");
        assertEquals("Futás", simpleActivity.getDescription());
        assertEquals("Budapest", ((SimpleActivity) simpleActivity).getPlace());

        Activity activityWithTrack = activityDao.findActivityByDescription("Biciklizés");
        assertEquals("Biciklizés", activityWithTrack.getDescription());
        assertEquals(25, ((ActivityWithTrack) activityWithTrack).getDistance());



    }

}
