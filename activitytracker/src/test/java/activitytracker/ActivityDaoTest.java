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

        MariaDbDataSource mariaDbDataSource = new MariaDbDataSource();
        mariaDbDataSource.setUrl("jdbc:mariadb://localhost:3306/activitytracker_jpa");
        mariaDbDataSource.setUser("activitytracker");
        mariaDbDataSource.setPassword("activitytracker");

        Flyway flyway = Flyway.configure().dataSource(mariaDbDataSource).load();
        flyway.clean();
        flyway.migrate();

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pu");
        activityDao = new ActivityDao(entityManagerFactory);

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

}