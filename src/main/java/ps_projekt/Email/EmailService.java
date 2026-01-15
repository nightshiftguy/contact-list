package ps_projekt.Email;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailService {
    public static void SendEmailSMTP(String recipientEmail, String subject, String body) {
        String senderEmail = "ps.projekt.automatic.email@gmail.com";     // sender address
        String host = "smtp.gmail.com";          // server SMTP

        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");    // TLS port
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true"); // TLS

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, "vyel jiem meva jucw");
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject(subject);
            message.setContent(body, "text/html; charset=utf-8");
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
