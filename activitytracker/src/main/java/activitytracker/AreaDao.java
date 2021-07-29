package activitytracker;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@Data
@AllArgsConstructor
public class AreaDao {

    private EntityManagerFactory entityManagerFactory;

    public void saveArea(Area area){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(area);
        em.getTransaction().commit();
        em.close();

    }

    public Area findAreaByName(String name){
        EntityManager em = entityManagerFactory.createEntityManager();
        Area area = em.createQuery("select a from Area a join fetch a.activities where a.name = :name", Area.class)
                .setParameter("name", name)
                .getSingleResult();
        em.close();
        return area;
    }


}
