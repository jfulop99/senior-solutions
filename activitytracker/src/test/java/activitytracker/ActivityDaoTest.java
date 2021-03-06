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
        activity.setLabels(List.of("Balaton", "Ny??r", "Csal??d"));
        activityDao.saveActivity(activity);
        Long id = activity.getId();

        Activity otherActivity = activityDao.findActivityByIdWithLabels(id);

        assertThat(otherActivity.getLabels())
                .hasSize(3)
                .containsExactly("Balaton", "Ny??r", "Csal??d");
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

    @Test
    void findTrackPointCoordinatesByDate() {

        Activity activity = new Activity(LocalDateTime.of(2021, 7, 1, 12,0), "First", ActivityType.BIKING);
        for (int i = 0; i < 20; i++) {
            TrackPoint trackPoint = new TrackPoint(LocalDateTime.of(2021, 7, 1, 12, i), 47.1 + i * 0.001, 19.1817);
            activity.addTrackPoint(trackPoint);
        }
        activityDao.saveActivity(activity);

        activity = new Activity(LocalDateTime.of(2017, 7, 1, 12,0), "Second", ActivityType.HIKING);
        for (int i = 0; i < 20; i++) {
            TrackPoint trackPoint = new TrackPoint(LocalDateTime.of(2017, 7, 1, 12, i), 47.2 + i * 0.001, 19.1817);
            activity.addTrackPoint(trackPoint);
        }
        activityDao.saveActivity(activity);

        activity = new Activity(LocalDateTime.of(2021, 8, 2, 12,0), "Third", ActivityType.RUNNING);
        for (int i = 0; i < 30; i++) {
            TrackPoint trackPoint = new TrackPoint(LocalDateTime.of(2021, 8, 2, 12, i), 47.3 + i * 0.001, 19.1817);
            activity.addTrackPoint(trackPoint);
        }
        activityDao.saveActivity(activity);

        List<Coordinate> coordinates = activityDao.findTrackPointCoordinatesByDate(LocalDateTime.of(2018, 1, 1, 0, 0), 30, 10);

        assertEquals(10, coordinates.size());

        assertEquals(47.310, coordinates.get(0).getLat(), 0.0005);



    }

    @Test
    void findTrackPointCountByActivity() {
        Activity activity = new Activity(LocalDateTime.of(2021, 7, 1, 12,0), "Third", ActivityType.BIKING);
        for (int i = 0; i < 20; i++) {
            TrackPoint trackPoint = new TrackPoint(LocalDateTime.of(2021, 7, 1, 12, i), 47.1 + i * 0.001, 19.1817);
            activity.addTrackPoint(trackPoint);
        }
        activityDao.saveActivity(activity);

        activity = new Activity(LocalDateTime.of(2017, 7, 1, 12,0), "Second", ActivityType.HIKING);
        for (int i = 0; i < 20; i++) {
            TrackPoint trackPoint = new TrackPoint(LocalDateTime.of(2017, 7, 1, 12, i), 47.2 + i * 0.001, 19.1817);
            activity.addTrackPoint(trackPoint);
        }
        activityDao.saveActivity(activity);

        activity = new Activity(LocalDateTime.of(2021, 8, 2, 12,0), "First", ActivityType.RUNNING);
        for (int i = 0; i < 30; i++) {
            TrackPoint trackPoint = new TrackPoint(LocalDateTime.of(2021, 8, 2, 12, i), 47.3 + i * 0.001, 19.1817);
            activity.addTrackPoint(trackPoint);
        }
        activityDao.saveActivity(activity);

        List<Object[]> result = activityDao.findTrackPointCountByActivity();

        assertEquals("First", result.get(0)[0]);
        assertEquals("Third", result.get(2)[0]);

        assertEquals(30L, result.get(0)[1]);
        assertEquals(20L, result.get(2)[1]);
    }

    @Test
    void removeActivitiesByDateAndType() {

        Activity activity = new Activity(LocalDateTime.of(2021, 7, 1, 21, 30), "First", ActivityType.BIKING);
        activityDao.saveActivity(activity);

        activity = new Activity(LocalDateTime.of(2021, 7, 2, 22, 30), "Second", ActivityType.HIKING);
        TrackPoint trackPoint1 = new TrackPoint(LocalDateTime.of(2021, 7, 1, 12, 1), 47.191817, 19.1817);
        activity.addTrackPoint(trackPoint1);
        activityDao.saveActivity(activity);

        activity = new Activity(LocalDateTime.of(2017, 7, 2, 22, 30), "Third", ActivityType.HIKING);
        trackPoint1 = new TrackPoint(LocalDateTime.of(2021, 8, 1, 12, 1), 47.191817, 19.1817);
        activity.addTrackPoint(trackPoint1);
        activityDao.saveActivity(activity);

        activity = new Activity(LocalDateTime.of(2017, 7, 2, 22, 30), "Fourth", ActivityType.HIKING);
        activityDao.saveActivity(activity);

        activity = new Activity(LocalDateTime.of(2017, 7, 2, 22, 30), "Fifth", ActivityType.BIKING);
        activityDao.saveActivity(activity);


        activityDao.removeActivitiesByDateAndType(LocalDateTime.of(2018, 1, 1, 0, 0), ActivityType.HIKING);

        List<Activity> activities = activityDao.listActivities();

        assertThat(activities)
                .hasSize(4)
                .extracting(Activity::getDesc)
                .contains("First", "Third", "Fourth", "Fifth");

    }

    @Test
    void testActivityWithDetails(){

        Activity activity = new Activity(LocalDateTime.of(2021, 7, 1, 21, 30), "First", ActivityType.BIKING);

        activity.setDistance(25);
        activity.setDuration(3600);

        activityDao.saveActivity(activity);

        Activity anotherActivity = activityDao.findActivityById(activity.getId());

        assertEquals(25, activity.getDistance());
        assertEquals(3600, activity.getDuration());


    }
}