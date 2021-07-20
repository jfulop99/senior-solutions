package meetingrooms;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.text.Collator;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class JpaMeetingRoomRepository implements MeetingRoomRepository{

    private EntityManagerFactory entityManagerFactory;

    private Collator collator = Collator.getInstance(new Locale("hu", "HU"));

    public JpaMeetingRoomRepository(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public MeetingRoom save(String name, int width, int length) {

        MeetingRoom meetingRoom = new MeetingRoom(name, width, length);
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(meetingRoom);
        em.getTransaction().commit();
        em.close();
        return meetingRoom;
    }

    @Override
    public List<String> getMeetingroomsOrderedByName() {
        EntityManager em = entityManagerFactory.createEntityManager();
        List<String> meetingRooms = em.createNamedQuery("findbyname", String.class)
                .getResultList();
        em.close();

        return meetingRooms;
    }

    @Override
    public List<String> getEverySecondMeetingRoom() {
        EntityManager em = entityManagerFactory.createEntityManager();
        List<String> meetingRooms = em.createQuery("select mr.name from MeetingRoom mr order by mr.id", String.class)
                .getResultList();
        em.close();

        return IntStream.range(0, meetingRooms.size())
                .filter(n -> (n + 1) % 2 == 0)
                .mapToObj(meetingRooms::get)
                .sorted(Comparator.comparing(String::valueOf, collator))
                .collect(Collectors.toList());

    }

    @Override
    public List<MeetingRoom> getMeetingRooms() {
        EntityManager em = entityManagerFactory.createEntityManager();
        List<MeetingRoom> meetingRooms = em.createQuery("select mr from MeetingRoom mr order by mr.id", MeetingRoom.class)
                .getResultList();
        em.close();

        return meetingRooms;
    }

    @Override
    public List<MeetingRoom> getExactMeetingRoomByName(String name) {
        EntityManager em = entityManagerFactory.createEntityManager();
        List<MeetingRoom> meetingRooms = em.createQuery("select mr from MeetingRoom mr where lower(mr.name) = lower(:name)", MeetingRoom.class)
                .setParameter("name", name)
                .getResultList();
        em.close();

        return meetingRooms;
    }

    @Override
    public List<MeetingRoom> getMeetingRoomsByPrefix(String nameOrPrefix) {
        EntityManager em = entityManagerFactory.createEntityManager();
        List<MeetingRoom> meetingRooms = em.createQuery("select mr from MeetingRoom mr where lower(mr.name) like lower(:name) ", MeetingRoom.class)
                .setParameter("name", nameOrPrefix.toLowerCase() + "%")
                .getResultList();
        em.close();

        return meetingRooms;
    }

    @Override
    public void deleteAll() {

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.createQuery("delete from MeetingRoom mr").executeUpdate();
        em.getTransaction().commit();
        em.close();

    }
}
