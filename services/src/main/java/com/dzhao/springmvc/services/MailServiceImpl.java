package com.dzhao.springmvc.services;

import com.dzhao.springmvc.config.MailConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by dzhao on 7/10/2015.
 */
@Service("mailService")
public class MailServiceImpl {

    private JavaMailSenderImpl mailSender;
    private SimpleMailMessage templateMessage;
    private MailConfig config;

/*
    @Autowired
    public void setMailSender(JavaMailSenderImpl mailSender){this.mailSender = mailSender;}
*/
    @Autowired
    public void setTemplateMessage(SimpleMailMessage templateMessage) {this.templateMessage = templateMessage;}

    @Autowired
    public MailServiceImpl(MailConfig config){
        this.config = config;
        mailSender = new JavaMailSenderImpl();
        Properties mailProperties = new Properties();
        mailProperties.put("mail.smtp.auth", config.isAuth());
        mailProperties.put("mail.smtp.starttls.enable", config.isStarttls());
        mailSender.setJavaMailProperties(mailProperties);

        mailSender.setHost(config.getHost());
        mailSender.setPort(config.getPort());
        mailSender.setProtocol(config.getProtocol());
        mailSender.setUsername(config.getUsername());
        mailSender.setPassword(config.getPassword());
    }

    public void sendEmail(String to, String subject, String body){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }

    public void sendEmail(String msg){
        SimpleMailMessage mailMessage = new SimpleMailMessage(templateMessage);
        mailMessage.setText(msg);
        mailSender.send(mailMessage);
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
