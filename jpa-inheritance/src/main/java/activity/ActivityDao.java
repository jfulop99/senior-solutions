package activity;

import lombok.AllArgsConstructor;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@AllArgsConstructor
public class ActivityDao {

    private EntityManagerFactory entityManagerFactory;

    public void saveActivity(Activity activity){

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(activity);
        em.getTransaction().commit();
        em.close();
    }

    public Activity findActivityByDescription(String description){
        EntityManager em = entityManagerFactory.createEntityManager();
        Activity activity = em
                .createQuery("select a from Activity a where a.description = :description", Activity.class)
                .setParameter("description", description)
                .getSingleResult();
        em.close();
        return activity;
    }



}
