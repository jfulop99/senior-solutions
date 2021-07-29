package activitytracker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class AreaDaoTest {

    private AreaDao areaDao;
    private ActivityDao activityDao;

    @BeforeEach
    void setUp(){

        EntityManagerFactory entityManagerFactory = Persistence
                .createEntityManagerFactory("pu");
        areaDao = new AreaDao(entityManagerFactory);
        activityDao = new ActivityDao(entityManagerFactory);
    }

    @Test
    void testSaveArea(){
        Activity firstActivity = new Activity(LocalDateTime.of(2021, 7, 1, 21, 30), "First", ActivityType.BIKING);
        Activity secondActivity = new Activity(LocalDateTime.of(2021, 7, 2, 21, 30), "Second", ActivityType.HIKING);
        Activity thirdActivity = new Activity(LocalDateTime.of(2021, 7, 3, 21, 30), "Third", ActivityType.BASKETBALL);

        activityDao.saveActivity(firstActivity);
        activityDao.saveActivity(secondActivity);
        activityDao.saveActivity(thirdActivity);

        Area firstArea = new Area("Mátra");
        Area secondArea = new Area("Alföld");
        Area thirdArea = new Area("Balaton");

        firstArea.addActivity(firstActivity);
        firstArea.addActivity(secondActivity);

        secondArea.addActivity(firstActivity);
        secondArea.addActivity(thirdActivity);

        thirdArea.addActivity(firstActivity);
        thirdArea.addActivity(secondActivity);
        thirdArea.addActivity(thirdActivity);

        areaDao.saveArea(firstArea);
        areaDao.saveArea(secondArea);
        areaDao.saveArea(thirdArea);

        Area area = areaDao.findAreaByName("Mátra");

        assertThat(area.getActivities())
                .hasSize(2)
                .extracting(Activity::getDesc)
                .contains("First", "Second");

    }

    @Test
    void testSaveWithCityAndFind(){
        Area area = new Area("Balaton");
        area.getCities().put("Balatonfenyves", new City("Balatonfenyves", 3000));
        area.getCities().put("Balatonszemes", new City("Balatonszemes", 4000));

        areaDao.saveArea(area);
        Long id = area.getId();

        Area anotherArea = areaDao.findById(id);
        assertEquals(3000, anotherArea.getCities().get("Balatonfenyves").getPopulation());

    }

}