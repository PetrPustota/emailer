package ru.neoflex.testapp.emailer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class EmployeeDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final class EmployeeMapper implements RowMapper<Employee> {
        @Override
        public Employee mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Employee(resultSet.getInt("id"),
                    resultSet.getString("first_name"),
                    resultSet.getString("last_name"),
                    resultSet.getString("email"));
        }
    }

    public List<Employee> getAll() {
        return jdbcTemplate.query("SELECT id, first_name, last_name, email FROM employees", new EmployeeMapper());
    }

    public Employee getEmployee(String email) {
        return jdbcTemplate.queryForObject("SELECT id, first_name, last_name, email FROM employees WHERE email = ?", new EmployeeMapper(), email);
    }

    public Employee addEmployee(Employee employee) {
        Object[] empl = {employee.getFirstName(), employee.getLastName(), employee.getEmail()};
        jdbcTemplate.update("INSERT INTO employees (first_name, last_name, email) VALUE(?,?,?)", empl);
        return jdbcTemplate.queryForObject("SELECT id, first_name, last_name, email " +
                "FROM employees WHERE first_name = ? AND last_name = ? AND email = ?", empl, new EmployeeMapper());
    }

    public void deleteEmployee(String email) {
        jdbcTemplate.update("DELETE FROM employees WHERE email = ?", email);
    }
}
