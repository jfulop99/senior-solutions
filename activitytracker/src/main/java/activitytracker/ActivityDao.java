package activitytracker;

import lombok.AllArgsConstructor;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NamedQuery;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class ActivityDao {

    private EntityManagerFactory entityManagerFactory;

    public ActivityDao(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public void saveActivity(Activity activity){

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(activity);
        em.getTransaction().commit();
        em.close();

    }

    public Activity findActivityById(long id){
        EntityManager em = entityManagerFactory.createEntityManager();
        Activity activity = em.find(Activity.class, id);
        em.close();
        return activity;
    }

    public Activity findActivityByIdWithLabels(long id){
        EntityManager em = entityManagerFactory.createEntityManager();

        Activity activity = em
                .createQuery("select a from Activity a join fetch a.labels where a.id = :id", Activity.class)
                .setParameter("id", id)
                .getSingleResult();
        em.close();
        return activity;
    }

    public Activity findActivityByIdWithTrackPoints(Long id){
        EntityManager em = entityManagerFactory.createEntityManager();
        Activity activity = em.createQuery("select a from Activity a join fetch a.trackPoints where a.id = :id",
            Activity.class)
                .setParameter("id", id)
                .getSingleResult();
        em.close();
        return activity;
    }

    public List<Activity> listActivities(){
        EntityManager em = entityManagerFactory.createEntityManager();
        List<Activity> activities = em.createQuery("select a from Activity a order by a.id", Activity.class)
                .getResultList();
        em.close();
        return activities;
    }

    public void updateActivity(long id, String desc){
        EntityManager em = entityManagerFactory.createEntityManager();
        Activity activity = em.find(Activity.class, id);


        em.getTransaction().begin();
        activity.setDesc(desc);
        em.getTransaction().commit();
        em.close();
    }

    public void deleteAllActivities(){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.createNativeQuery("TRUNCATE TABLE activities");
        em.createNativeQuery("TRUNCATE TABLE labels");
        em.createNativeQuery("TRUNCATE TABLE trackpoints");
        em.close();
    }

    public void addTrackPoint(Long id, TrackPoint trackPoint){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
//        Activity activity = em.find(Activity.class, id);
        Activity activity = em.getReference(Activity.class, id);
        trackPoint.setActivity(activity);
        em.persist(trackPoint);
        em.getTransaction().commit();
        em.close();
    }

    public List<Coordinate> findTrackPointCoordinatesByDate(LocalDateTime afterThis, int start, int max){
        EntityManager em = entityManagerFactory.createEntityManager();
        List<Coordinate> coordinates = em
                .createNamedQuery("findTrackPointCoordinatesByDate", Coordinate.class)
                .setParameter("afterThis", afterThis)
                .setFirstResult(start)
                .setMaxResults(max)
                .getResultList();
        return coordinates;
    }


}
