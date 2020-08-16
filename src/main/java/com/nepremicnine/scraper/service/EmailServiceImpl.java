package com.nepremicnine.scraper.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class EmailServiceImpl implements EmailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailServiceImpl.class);

    private final JavaMailSender mailSender;

    @Autowired
    public EmailServiceImpl(final JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void send(final String from, final String to, final String subject, final String body) {
        LOGGER.info("Sending email to: " + to);
        try {
            this.mailSender.send(getMimeMessagePrep(from, to, subject, body));
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    private MimeMessagePreparator getMimeMessagePrep(final String from, final String to, final String subject, final String body) {
        return mimeMessage -> {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
            message.setTo(to);
            message.setFrom(from);
            message.setSubject(subject);
            message.setSentDate(new Date());
            message.setText(body, true);
        };
    }
}
