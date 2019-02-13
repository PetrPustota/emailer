package ru.neoflex.testapp.emailer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeDAO dao;

//    @Autowired
//    private JdbcTemplate jdbcTemplate;
//  TODO
    @Autowired
    private JavaMailSenderImpl javaMailSender;

//    private static final class EmployeeMapper implements RowMapper<Employee> {
//        @Override
//        public Employee mapRow(ResultSet resultSet, int i) throws SQLException {
//            return new Employee(resultSet.getInt("id"),
//                    resultSet.getString("first_name"),
//                    resultSet.getString("last_name"),
//                    resultSet.getString("email"));
//        }
//    }

//    TODO Exception empty return
    @GetMapping("/employee")
    public Employee getEmployee(@RequestParam String email) {
        return dao.getEmployee(email);
//        return jdbcTemplate.queryForObject("SELECT id, first_name, last_name, email FROM employees",
//                new EmployeeMapper());
    }

    @PostMapping("/add")
    public Employee addEmployee(@RequestBody Employee employee) {
        return dao.addEmployee(employee);
//        Object[] empl = {employee.getFirstName(), employee.getLastName(), employee.getEmail()};
//        jdbcTemplate.update("INSERT INTO employees (first_name, last_name, email) VALUE(?,?,?)", empl);
//        return jdbcTemplate.queryForObject("SELECT id, first_name, last_name, email " +
//                "FROM employees WHERE first_name = ? AND last_name = ? AND email = ?", empl, new EmployeeMapper());
    }

    @DeleteMapping("/delete/{email}")
    public String deleteEmployee(@PathVariable String email) {
        dao.deleteEmployee(email);
//        jdbcTemplate.update("DELETE FROM employees WHERE email = ?", email);
        return "deleted";
    }

    @GetMapping("/all")
    public List<Employee> allEmployees() {
        return dao.getAll();
//        return jdbcTemplate.query("SELECT id, first_name, last_name, email FROM employees", new EmployeeMapper());
    }

//    TODO
    @RequestMapping("/hello")
    public String hello() {
        return "Hello";
    }

//    TODO
    @RequestMapping("/sendEmail")
    public String send() {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo("alekcei94@mail.ru");
        mailMessage.setSubject("Test");
        mailMessage.setText("Test me");
        javaMailSender.send(mailMessage);
        return "Sent";
    }
}
