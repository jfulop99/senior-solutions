package locations;

import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpServerErrorException;

import java.sql.*;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class LocationDao {

    private JdbcTemplate jdbcTemplate;

    public List<Location> findAll(Optional<String> prefix) {

        String parameter = "%" + prefix.orElse("").toLowerCase() + "%";

        return jdbcTemplate.query("SELECT id, location_name, lat, lon FROM locations WHERE LOWER(location_name) LIKE ? ORDER BY id",
                LocationDao::mapRow, parameter);
    }

    public Location findById(long id){
        Location location;
        try {
                location = jdbcTemplate.queryForObject("SELECT id, location_name, lat, lon FROM locations WHERE id = ?",
                    LocationDao::mapRow, id);
        }catch (EmptyResultDataAccessException e) {
            throw new LocationNotFoundException(e.getMessage());
        }
        return location;
    }

    public Location createLocation(Location location){
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement("INSERT INTO locations (location_name, lat, lon) VALUES (?, ?, ?)",
                        Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, location.getName());
                ps.setDouble(2, location.getLat());
                ps.setDouble(3, location.getLon());
                return ps;
            }
        }, keyHolder);
        location.setId(keyHolder.getKey().longValue());
        return location;
    }

    public void updateLocation(Location location){
        jdbcTemplate.update("UPDATE locations SET location_name = ? WHERE id = ?",
                location.getName(), location.getId());
    }

    public void deleteById(long id){
        jdbcTemplate.update("DELETE FROM locations WHERE id = ?", id);
    }

    public void deleteAll(){
        jdbcTemplate.execute("TRUNCATE TABLE locations RESTART IDENTITY");
    }

    private static Location mapRow(ResultSet resultSet, int i) throws SQLException{
        long id = resultSet.getLong("id");
        String name = resultSet.getString("location_name");
        double lat = resultSet.getDouble("lat");
        double lon = resultSet.getDouble("lon");
        Location location = new Location(id, name, lat, lon);
        return location;
    }
}
