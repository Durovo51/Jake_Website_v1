package jakeBryantSiteV1.example.demo;

import org.springframework.beans.factory.annotation.Autowired; // IMPORTANT IMPORT
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    // @Autowired tells Spring: "Go get the settings from application.properties
    // and inject them into this variable."
    @Autowired
    private JavaMailSender mailSender;

    // Rename 'to' to 'userEmail' so it's clear this is the person contacting you
    public void sendEmail(String userEmail, String subject, String body, String name) {
        SimpleMailMessage message = new SimpleMailMessage();

        // 1. AUTHENTIC Sender: This must match the email in your application.properties
        // This tells the spam filters "Yes, this email is legitimately from this server."
        message.setFrom("noreply@jakebryantsite.com");

        // 2. The Recipient: You (the admin)
        message.setTo("mail.jbryant1@gmail.com");

        // 3. The Magic Part: "Reply-To"
        // When you hit "Reply" in your inbox, it will ignore the "From" address
        // and automatically reply to the user's email address.
        message.setReplyTo(userEmail);

        message.setSubject(subject);
        message.setText("Message from: " + name + "\n\nEmailed from: " + userEmail + "\n\nBody of email: " + body);


        mailSender.send(message);
    }
}