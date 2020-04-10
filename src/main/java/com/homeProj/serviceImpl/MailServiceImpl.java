package com.homeProj.serviceImpl;

import java.util.Locale;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import com.homeProj.domain.User;
import com.homeProj.service.EmailService;

@Service
@PropertySource("application-${spring.profiles.active}.properties")
public class MailServiceImpl implements EmailService {

    private final Logger log = LoggerFactory.getLogger(MailServiceImpl.class);
    private final SpringTemplateEngine templateEngine;
    private final JavaMailSender javaMailSender;
    
    @Value("${mailservice.base.url}")
    private String base_url;
    @Value("${mailservice.mail.from}")
    private String mail_from;

    public MailServiceImpl(JavaMailSender javaMailSender, SpringTemplateEngine templateEngine) {
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
    }

    @Override
	@Async
    public void sendEmail(String to, String subject, String content, boolean isMultiPart, boolean isHtml) {
        log.debug("Sending Email");

        MimeMessage mimeMessage = this.javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "UTF-8");
            message.setTo(to);
            message.setFrom(mail_from);
            message.setSubject(subject);
            message.setText(content,isHtml);
            javaMailSender.send(mimeMessage);
        } catch (Exception e) {
            log.warn("Email could not be sent to user '{}': {}", to, e.getMessage());
        }
    }

    @Override
	@Async
    public void sendEmailFromTemplate(User user, String templateName, String subject) {
        Locale locale = Locale.ENGLISH;
        Context context = new Context(locale);
        context.setVariable("user", user);
        context.setVariable("baseURL",base_url);
        String content = templateEngine.process(templateName,context);
        sendEmail(user.getEmail(),subject,content,false,true);
    }

    @Override
	@Async
    public void sendActivationEmail(User user) {
        log.debug("Sending activation email to '{}'", user.getEmail());
        sendEmailFromTemplate(user, "email/activation", "Springit User Activation");
    }

    @Override
	@Async
    public void sendWelcomeEmail(User user) {
        log.debug("Sending activation email to '{}'", user.getEmail());
        sendEmailFromTemplate(user, "email/welcome", "Welcome new Springit User");
    }
}
