package activitytracker;

import lombok.AllArgsConstructor;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

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
    }

}
