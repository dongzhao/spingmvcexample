package com.dzhao.springmvc.services;

import com.dzhao.springmvc.services.api.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Created by dzhao on 7/10/2015.
 */
@Service("mailService")
public class MailServiceImpl implements MailService{

    private JavaMailSender  mailSender;
    private ThreadPoolTaskExecutor taskExecutor;

    @Autowired
    public MailServiceImpl(JavaMailSender  mailSender, ThreadPoolTaskExecutor taskExecutor) {
        this.mailSender = mailSender;
        this.taskExecutor = taskExecutor;
    }

    public void sendEmail(String to, String subject, String body){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }

    public void sendConfirmationEmail(final String to, final String subject, final String body){
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
                message.setTo(to);
                message.setFrom("test@gmail.com");
                message.setSubject(subject);
                message.setText(body, true);
            }
        };
        this.mailSender.send(preparator);
    }

    public void sendConfirmationEmail(final String to, final String subject, final String body, String file){
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
                mimeMessage.setRecipient(Message.RecipientType.TO,
                        new InternetAddress(to));
                message.setFrom(new InternetAddress("test@gmail.com"));
                message.setSubject(subject);
                message.setText(body, true);
            }
        };
        this.mailSender.send(preparator);
    }
}
