package activitytracker;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.List;

public class ActivityTrackerMain {

    public static void main(String[] args) {

        insertSomeActivities();

    }

    private static void insertSomeActivities() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pu");
        EntityManager em = entityManagerFactory.createEntityManager();

        em.getTransaction().begin();

        Activity activity = new Activity(LocalDateTime.of(2021, 7, 1, 12, 0), "First", ActivityType.BIKING);
        em.persist(activity);

        activity = new Activity(LocalDateTime.of(2021, 7, 2, 13, 0), "Second", ActivityType.BASKETBALL);
        em.persist(activity);

        activity = new Activity(LocalDateTime.of(2021, 7, 3, 14, 0), "Third", ActivityType.HIKING);
        em.persist(activity);

        em.getTransaction().commit();

        List<Activity> activities = em.createQuery("select e from Activity e", Activity.class)
                .getResultList();

        System.out.println(activities);

        em.close();
        entityManagerFactory.close();
    }
}
