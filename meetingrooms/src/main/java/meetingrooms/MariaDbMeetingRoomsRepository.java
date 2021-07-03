package meetingrooms;

import org.flywaydb.core.Flyway;
import org.mariadb.jdbc.MariaDbDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;
import java.text.Collator;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MariaDbMeetingRoomsRepository implements MeetingRoomsRepository{

//    private Collator collator = Collator.getInstance(new Locale("hu", "HU"));

    private JdbcTemplate jdbcTemplate;

    public MariaDbMeetingRoomsRepository() {
        try {
            MariaDbDataSource dataSource = new MariaDbDataSource();
            dataSource.setUrl("jdbc:mariadb://localhost:3306/employees?useUnicode=true");
            dataSource.setUser("employees");
            dataSource.setPassword("employees");

            Flyway flyway = Flyway.configure().dataSource(dataSource).load();
//            flyway.clean();
            flyway.migrate();

            jdbcTemplate = new JdbcTemplate(dataSource);

        } catch (SQLException e) {
            throw new IllegalStateException("Cannot create datasource", e);
        }
    }

    @Override
    public void add(String name, int width, int length) {

        jdbcTemplate.update("insert into meetingrooms(name, width, length) values(?, ?, ?)", name, width, length);

    }

    @Override
    public List<MeetingRoom> findAll() {
        return jdbcTemplate.query("select id, name, width, length from meetingrooms order by id",
                (rs, i) -> new MeetingRoom(rs.getLong("id"), rs.getString("name"),
                        rs.getInt("width"), rs.getInt("length")));
    }

    @Override
    public List<MeetingRoom> findAllOrderedByName() {
        return jdbcTemplate.query("select id, name, width, length from meetingrooms order by name",
                (rs, i) -> new MeetingRoom(rs.getLong("id"), rs.getString("name"),
                        rs.getInt("width"), rs.getInt("length")));
    }

    @Override
    public List<MeetingRoom> findAllReverseOrderedByName() {
        return jdbcTemplate.query("select id, name, width, length from meetingrooms order by name desc",
                (rs, i) -> new MeetingRoom(rs.getLong("id"), rs.getString("name"),
                        rs.getInt("width"), rs.getInt("length")));
    }

    @Override
    public void deleteAll() {
        jdbcTemplate.update("delete from meetingrooms");
    }

    @Override
    public List<MeetingRoom> everySecondMeetingRooms() {

        return jdbcTemplate.query("SELECT result.id, result.name, result.width, result.length FROM (SELECT id, name, width, length, ROW_NUMBER() OVER (ORDER BY id) AS rownum FROM meetingrooms) AS result WHERE result.rownum % 2 = 0 ORDER BY name",
                (rs, i) -> new MeetingRoom(rs.getLong("id"), rs.getString("name"),
                        rs.getInt("width"), rs.getInt("length")));


//        List<MeetingRoom> result = findAll();
//        return IntStream.range(0, result.size())
//                .filter(n -> (n + 1) % 2 == 0)
//                .mapToObj(result::get)
//                .sorted(Comparator.comparing(MeetingRoom::getName, collator))
//                .collect(Collectors.toList());
    }

    @Override
    public List<MeetingRoom> findMeetingRoomsByAreas() {
        return jdbcTemplate.query("select id, name, width, length, (length * width) as area from meetingrooms order by area desc",
                (rs, i) -> new MeetingRoom(rs.getLong("id"), rs.getString("name"),
                        rs.getInt("width"), rs.getInt("length")));
    }

    @Override
    public MeetingRoom findMeetingRoomByName(String name) {
        return jdbcTemplate.query("select id, name, width, length from meetingrooms where name = ?",
                (rs, i) -> new MeetingRoom(rs.getLong("id"), rs.getString("name"),
                        rs.getInt("width"), rs.getInt("length")), name)
                .stream()
                .findAny()
                .orElseThrow(() -> new NoSuchElementException("Nincs ilyen nevű terem!"));
    }

    @Override
    public List<MeetingRoom> findMeetingRoomByPartOfName(String partOfName) {
        return jdbcTemplate.query("select id, name, width, length from meetingrooms WHERE LOWER(name) LIKE ? order by name",
                (rs, i) -> new MeetingRoom(rs.getLong("id"), rs.getString("name"),
                        rs.getInt("width"), rs.getInt("length")), '%' + partOfName + '%');
    }

    @Override
    public List<MeetingRoom> findMeetingRoomByArea(int area) {
        return jdbcTemplate.query("select id, name, width, length, (length * width) as area from meetingrooms where width * length > ? order by area desc",
                (rs, i) -> new MeetingRoom(rs.getLong("id"), rs.getString("name"),
                        rs.getInt("width"), rs.getInt("length")), area);
    }
}
