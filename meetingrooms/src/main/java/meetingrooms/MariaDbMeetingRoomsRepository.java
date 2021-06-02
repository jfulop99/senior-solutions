package meetingrooms;

import org.flywaydb.core.Flyway;
import org.mariadb.jdbc.MariaDbDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;
import java.util.List;

public class MariaDbMeetingRoomsRepository implements MeetingRoomsRepository{

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

        } catch (SQLException sqle) {
            throw new IllegalStateException("Cannot create datasource", sqle);
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
    public void deleteAll() {
        jdbcTemplate.update("delete from meetingrooms");
    }

}
