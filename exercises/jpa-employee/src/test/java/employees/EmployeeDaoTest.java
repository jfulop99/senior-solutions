package employees;

import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mariadb.jdbc.MariaDbDataSource;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class EmployeeDaoTest {

    private  EmployeeDao employeeDao;

    @BeforeEach
    public void init() throws SQLException {
//        MariaDbDataSource dataSource = new MariaDbDataSource();
//        dataSource.setUrl("jdbc:mariadb://localhost:3306/employees");
//        dataSource.setUser("employees");
//        dataSource.setPassword("employees");
//
//        Flyway flyway = Flyway.configure().dataSource(dataSource).load();
//
//        flyway.clean();
//        flyway.migrate();
//
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pu");
        employeeDao = new EmployeeDao(entityManagerFactory);
    }

    @Test
    void testSaveThenFindById() {

        Employee employee = new Employee("John Doe");
        employeeDao.save(employee);

        Long id = employee.getId();

        Employee another = employeeDao.findById(id);

        assertEquals("John Doe", another.getName());

    }

    @Test
    void testSaveThenListAll() {

        employeeDao.save(new Employee("John Doe"));
        employeeDao.save(new Employee("Jane Doe"));

        List<Employee> employees = employeeDao.listAll();
        assertThat(employees)
                .hasSize(2)
                .extracting(Employee::getName)
                .containsExactly("Jane Doe", "John Doe");
    }

    @Test
    void testChangeName() {

        Employee employee = new Employee("John Doe");
        employeeDao.save(employee);

        long id = employee.getId();

        employeeDao.changeName(id,"Jack Doe");

        Employee another = employeeDao.findById(id);
        assertEquals("Jack Doe", another.getName());
    }

    @Test
    void testDelete() {
        Employee employee = new Employee("John Doe");
        employeeDao.save(employee);

        long id = employee.getId();

        employeeDao.delete(id);

        List<Employee> employees = employeeDao.listAll();
        assertThat(employees)
                .hasSize(0);
    }

    @Test
    public void testEmployeeWithAttributes(){
        employeeDao.save(new Employee("John Doe", EmployeeType.PART_TIME,
                LocalDate.of(2000, 1, 1)));

        Employee employee = employeeDao.listAll().get(0);
        assertEquals(LocalDate.of(2000, 1, 1), employee.getDateOfBirth());
    }
}