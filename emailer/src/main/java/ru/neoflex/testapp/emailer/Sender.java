package ru.neoflex.testapp.emailer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

public class Sender implements Runnable {
    private JavaMailSenderImpl javaMailSender;
    private String sendTo;
    private String subject;
    private String text;

//    TODO
    private static final Logger log = LoggerFactory.getLogger(Sender.class);

    public Sender(JavaMailSenderImpl javaMailSender, String sendTo, String subject, String text) {
        this.javaMailSender = javaMailSender;
        this.sendTo = sendTo;
        this.subject = subject;
        this.text = text;
    }

    @Override
    public void run() {
//        TODO
        log.info("Sending to " + sendTo);
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setTo(sendTo);
//        message.setSubject(subject);
//        message.setText(text);
//        javaMailSender.send(message);
    }
}