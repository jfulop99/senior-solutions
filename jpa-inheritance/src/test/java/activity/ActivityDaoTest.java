package activity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ActivityDaoTest {

    private ActivityDao activityDao;

    @BeforeEach
    void setUp() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pu");
        activityDao = new ActivityDao(entityManagerFactory);
    }

    @Test
    void testSaveAndFind(){
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