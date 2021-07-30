package jpaspringboottal;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.Transient;
import javax.transaction.Transactional;

@AllArgsConstructor
@Repository
public class ActivityDao {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void saveActivity(Activity activity){

        em.persist(activity);
    }

    public Activity findActivityByDescription(String description){
        return em.createQuery("select a from Activity a where a.description = :description", Activity.class)
                .setParameter("description", description)
                .getSingleResult();
    }
}
