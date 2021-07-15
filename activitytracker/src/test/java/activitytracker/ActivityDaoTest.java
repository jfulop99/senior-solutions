package activitytracker;

import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mariadb.jdbc.MariaDbDataSource;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ActivityDaoTest {

    private ActivityDao activityDao;

    @BeforeEach
    void setUp() throws SQLException {

//        MariaDbDataSource mariaDbDataSource = new MariaDbDataSource();
//        mariaDbDataSource.setUrl("jdbc:mariadb://localhost:3306/activitytracker_jpa");
//        mariaDbDataSource.setUser("activitytracker");
//        mariaDbDataSource.setPassword("activitytracker");
//
//        Flyway flyway = Flyway.configure().dataSource(mariaDbDataSource).load();
//        flyway.clean();
//        flyway.migrate();
//
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pu");
        activityDao = new ActivityDao(entityManagerFactory);

        //activityDao.deleteAllActivities();
    }

    @Test
    void testSaveThenFindById() {

        Activity activity = new Activity(LocalDateTime.of(2021, 7, 1, 21, 30), "First", ActivityType.BIKING);
        activityDao.saveActivity(activity);

        Long id = activity.getId();

        activity = activityDao.findActivityById(id);

        assertEquals("First", activity.getDesc());
        assertEquals(LocalDateTime.of(2021, 7, 1, 21, 30), activity.getStartTime());
        assertEquals(ActivityType.BIKING, activity.getType());

    }

    @Test
    void testSaveThenListAll(){
        Activity activity = new Activity(LocalDateTime.of(2021, 7, 1, 21, 30), "First", ActivityType.BIKING);
        activityDao.saveActivity(activity);

        activity = new Activity(LocalDateTime.of(2021, 7, 2, 22, 30), "Second", ActivityType.HIKING);
        activityDao.saveActivity(activity);

        List<Activity> activities = activityDao.listActivities();

        assertThat(activities)
                .hasSize(2)
                .extracting(Activity::getDesc)
                .containsExactly("First", "Second");

    }

    @Test
    void testSaveThenUpdate(){
        Activity activity = new Activity(LocalDateTime.of(2021, 7, 1, 21, 30), "First", ActivityType.BIKING);
        activityDao.saveActivity(activity);

        Long id = activity.getId();

        activityDao.updateActivity(id, "Updated First");

        activity = activityDao.findActivityById(id);

        assertEquals("Updated First", activity.getDesc());
        assertEquals(LocalDateTime.of(2021, 7, 1, 21, 30), activity.getStartTime());
        assertEquals(ActivityType.BIKING, activity.getType());

    }

    @Test
    void testLabels(){
        Activity activity = new Activity(LocalDateTime.of(2021, 7, 1, 21, 30), "First", ActivityType.BIKING);
        activity.setLabels(List.of("Balaton", "Nyár", "Család"));
        activityDao.saveActivity(activity);
        Long id = activity.getId();

        Activity otherActivity = activityDao.findActivityByIdWithLabels(id);

        assertThat(otherActivity.getLabels())
                .hasSize(3)
                .containsExactly("Balaton", "Nyár", "Család");
    }

    @Test
    void testTrackPoint(){

        TrackPoint trackPoint1 = new TrackPoint(LocalDateTime.of(2021, 7, 1, 12, 1), 47.191817, 19.1817);
        TrackPoint trackPoint2 = new TrackPoint(LocalDateTime.of(2021, 7, 2, 12, 2), 47.211817, 19.2017);

        Activity activity = new Activity(LocalDateTime.of(2021, 7, 1, 12,0), "First", ActivityType.BIKING);
        activity.addTrackPoint(trackPoint2);
        activity.addTrackPoint(trackPoint1);
        activityDao.saveActivity(activity);

        Activity anotherActivity = activityDao.findActivityByIdWithTrackPoints(activity.getId());

        assertThat(anotherActivity.getTrackPoints())
                .hasSize(2)
                .extracting(TrackPoint::getTime)
                .containsExactly(LocalDateTime.of(2021, 7, 1, 12, 1),
                        LocalDateTime.of(2021, 7, 2, 12, 2));

        assertEquals(LocalDateTime.of(2021, 7, 1, 12, 1),
                anotherActivity.getTrackPoints().get(0).getTime());
    }


    @Test
    void testAddTrackPoint(){
        Activity activity = new Activity(LocalDateTime.of(2021, 7, 1, 12,00), "First", ActivityType.BIKING);
        activityDao.saveActivity(activity);

        Long id = activity.getId();
        activityDao.addTrackPoint(id, new TrackPoint(LocalDateTime.of(2021, 7, 1, 12, 1), 47.191817, 19.1817));

        Activity anotherActivity = activityDao.findActivityByIdWithTrackPoints(id);

        assertThat(anotherActivity.getTrackPoints())
                .hasSize(1)
                .extracting(TrackPoint::getTime)
                .contains(LocalDateTime.of(2021, 7, 1, 12, 1));


    }

}