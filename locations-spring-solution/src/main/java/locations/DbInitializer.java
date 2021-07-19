package locations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

//@Component
public class DbInitializer implements CommandLineRunner {

//    @Autowired
    private JdbcTemplate jdbcTemplate;

//    @Override
    public void run(String... args) throws Exception {
        jdbcTemplate.execute(
                "CREATE TABLE locations (id bigint auto_increment, location_name varchar(255), lat double, lon double, primary key (id))"
        );

        jdbcTemplate.execute(
                "INSERT INTO locations (location_name, lat, lon) VALUES ('Bécs', 45.497912, 19.040235)"
        );

        jdbcTemplate.execute(
                "INSERT INTO locations (location_name, lat, lon) VALUES ('Budapest', 47.497912,19.040235)"
        );

        jdbcTemplate.execute(
                "INSERT INTO locations (location_name, lat, lon) VALUES ('Prága', 48.497912,19.040235)"
        );

    }
}
