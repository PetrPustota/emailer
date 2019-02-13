package ru.neoflex.testapp.emailer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class ScheduledTask {

    private static final String SUBJECT = "Важное";
    private static final String TEXT = "Пора пить чай";

    @Autowired
    private JavaMailSenderImpl javaMailSender;

    @Autowired
    private EmployeeDAO dao;

//    TODO
    private static final Logger log = LoggerFactory.getLogger(ScheduledTask.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Autowired
    private ThreadPoolTaskExecutor tpte;

    @Scheduled(fixedRate = 30000)
//    TODO
//    @Scheduled(cron = "0 0 17 * * MON-FRI")
    public void task() {
//        TODO
        log.info("The time is now {}", dateFormat.format(new Date()));
        List<Employee> employees = dao.getAll();
        for (Employee employee : employees) {
            tpte.execute(new Sender(javaMailSender, employee.getEmail(), SUBJECT, TEXT));
        }
    }
}
