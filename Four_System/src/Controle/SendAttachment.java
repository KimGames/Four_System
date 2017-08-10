package Controle;

import Classes.Condominio;
import Classes.Morador;
import java.io.File;
import java.io.IOException;
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
    
    private static String cid = "1";
    
    private static final String USER = "fourassociados@gmail.com"; //change accordingly  
    private static final String PASSWORD = "Administradora932266"; //change accordingly
    private static final String MAIL_SERVER = "smtp";
    private static final String SMTP_HOST_NAME = "smtp.gmail.com";
    private static final int SMTP_HOST_PORT = 587;
    private static final String SUBJECT = "Taxa de Condomínio Agosto/2017";
    private static final String BODY = "<html>"
                                     + " <body>"
                                     + "<p>Segue anexo,</p>"
                                     + "<p>Atenciosamente,</p>"
                                     + "<p>Kim Ruan</p>"
                                     + "<p>--</p>"
                                     + "<p><h2 style=\"font-family:Comic Sans MS; color:rgb(56, 118, 29);\">Four Associados Administração de Condomínios</h2></p>"
                                     + "<img src=\"cid:" + cid + "\" />"
                                     + "<b style=\"font-family:Sans Serif; color:rgb(11, 83, 148);\">:03-004547/O</b>"
                                     + "<p><b style=\"font-family:Comic Sans MS; color:black;\">Horário de funcionamento: segunda a sexta-feira "
                                     + "de 09:00 as 18:00h.</b></p>"
                                     + "<p style=\"color:black;\">(34) 3213- 6203</p>"
                                     + "<p style=\"color:black;\">(34) 9630-2967</p>"
                                     + "<p style=\"color:black;\">(34) 9226-2967</p>"
                                     + " </body>"
                                     + "</html>";
    private static final String IMAGE_BODY = "..\\Four_System\\Imagens\\crea.png";
    
    public static void main(String [] args) throws IOException{
        
        ArrayList<String> to = new ArrayList<String>(); //change accordingly
        to.add("kimgames1@gmail.com");
        
        ArrayList<String> filename_to = new ArrayList<String>();
        filename_to.add("..\\Four_System\\Arquivos\\Boletos_Do_Condominio_X\\Boleto.pdf");
        
        //1) get the session object     
        Properties properties = System.getProperties();  
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", SMTP_HOST_NAME);
        properties.put("mail.smtp.user", USER);
        properties.put("mail.smtp.password", PASSWORD);
        properties.put("mail.smtp.port", SMTP_HOST_PORT);
        properties.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(properties,  
            new javax.mail.Authenticator() {  
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {  
                    return new PasswordAuthentication(USER,PASSWORD);  
                }
            });
        
        //ENVIAR EMAILS
        try {
            for(int i = 0; i < to.size(); i++){
                sendEmail(session, to.get(i), filename_to.get(i), SUBJECT);
            }
            System.out.println("Email Sent....!");
	} catch (Exception ex) {
            System.out.println("Could not send email....!");
            ex.printStackTrace();
	}
    }
    
    private static void sendEmail(Session session, String to, String filename, String subject) throws IOException{
       
        MimeMessage message = new MimeMessage(session);
        
        try{
            message.setFrom(new InternetAddress(USER));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(subject);

            //3) create MimeBodyPart object and set your message text     
            MimeBodyPart messageBodyPart_HTML = new MimeBodyPart();
            
            messageBodyPart_HTML.setText(BODY, "US-ASCII", "html");

            //4) create new MimeBodyPart object and set DataHandler object to this object      
            MimeBodyPart messageBody_FILE = new MimeBodyPart();
            
            DataSource source = new FileDataSource(filename);  
            messageBody_FILE.setDataHandler(new DataHandler(source));
            messageBody_FILE.setFileName(new File(filename).getName());
            
            MimeBodyPart messageBodyPart_IMAGE = new MimeBodyPart();
            
            messageBodyPart_IMAGE.attachFile(IMAGE_BODY);
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

           System.out.println("message sent....");
        }catch (MessagingException ex){
            ex.printStackTrace();
        }
   }
}