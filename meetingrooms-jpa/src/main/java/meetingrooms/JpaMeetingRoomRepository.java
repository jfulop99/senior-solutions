package meetingrooms;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class JpaMeetingRoomRepository implements MeetingRoomRepository{

    private EntityManagerFactory entityManagerFactory;

    public JpaMeetingRoomRepository(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public void add(String name, int width, int length) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(new MeetingRoom(name, width, length));
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public List<String> findAll() {
        EntityManager em = entityManagerFactory.createEntityManager();
        List<String> meetingRooms = em.createQuery("select a.name from MeetingRoom a order by a.id", String.class)
                .getResultList();
        em.close();

        return meetingRooms;
    }

    @Override
    public List<MeetingRoom> findAllOrderedByName() {
        return null;
    }

    @Override
    public List<MeetingRoom> findAllReverseOrderedByName() {
        return null;
    }

    @Override
    public List<MeetingRoom> everySecondMeetingRooms() {
        return null;
    }

    @Override
    public List<MeetingRoom> findMeetingRoomsByAreas() {
        return null;
    }

    @Override
    public MeetingRoom findMeetingRoomByName(String name) {
        return null;
    }

    @Override
    public List<MeetingRoom> findMeetingRoomByPartOfName(String partOfName) {
        return null;
    }

    @Override
    public List<MeetingRoom> findMeetingRoomByArea(int area) {
        return null;
    }

    @Override
    public void deleteAll() {

    }
}
