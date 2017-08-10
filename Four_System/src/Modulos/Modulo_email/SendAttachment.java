package Modulos.Modulo_email;

import java.io.File;
import java.io.IOException;
import java.sql.Statement;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendAttachment{

  private Properties properties;
  private Session session;

  private static String cid = "1";
  private static final String USER = "****@gmail.com"; //change accordingly
  private static final String PASSWORD = "****"; //change accordingly
  private static final String MAIL_SERVER = "smtp";
  private static final String SMTP_HOST_NAME = "smtp.gmail.com";
  private static final int SMTP_HOST_PORT = 587;
  
  public SendAttachment(){

    //1) get the session object
    properties = System.getProperties();
    properties.put("mail.smtp.starttls.enable", "true");
    properties.put("mail.smtp.host", SMTP_HOST_NAME);
    properties.put("mail.smtp.user", USER);
    properties.put("mail.smtp.password", PASSWORD);
    properties.put("mail.smtp.port", SMTP_HOST_PORT);
    properties.put("mail.smtp.auth", "true");

    session = Session.getDefaultInstance(properties,
        new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USER,PASSWORD);
            }
        });
  }

  public Session getSession(){
    return session;
  }

  public static void sendEmail(Session session, String to, String filename,
                               String subject, String body,
                               String image_body) throws IOException{

    MimeMessage message = new MimeMessage(session);
    try{
        message.setFrom(new InternetAddress(USER));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
        message.setSubject(subject);

        //3) create MimeBodyPart object and set your message text
        MimeBodyPart messageBodyPart_HTML = new MimeBodyPart();

        messageBodyPart_HTML.setText(body, "US-ASCII", "html");

        //4) create new MimeBodyPart object and set DataHandler object to this object
        MimeBodyPart messageBody_FILE = new MimeBodyPart();

        DataSource source = new FileDataSource(filename);
        messageBody_FILE.setDataHandler(new DataHandler(source));
        messageBody_FILE.setFileName(new File(filename).getName());

        MimeBodyPart messageBodyPart_IMAGE = new MimeBodyPart();

        messageBodyPart_IMAGE.attachFile(image_body);
        messageBodyPart_IMAGE.setContentID("<" + cid + ">");
        messageBodyPart_IMAGE.setDisposition(MimeBodyPart.INLINE);

        //5) create Multipart object and add MimeBodyPart objects to this object
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart_HTML);
        multipart.addBodyPart(messageBody_FILE);
        multipart.addBodyPart(messageBodyPart_IMAGE);

        //6) set the multiplart object to the message object
        message.setContent(multipart);

        //7) send message
        Transport transport = session.getTransport(MAIL_SERVER);
        transport.connect(SMTP_HOST_NAME, SMTP_HOST_PORT, USER, PASSWORD);
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
        //Transport.send(message);

       System.out.println("Mensagem enviada com sucesso!");
    }catch (MessagingException ex){
    }
  }
}
