package Modulos.Modulo_email;

import java.io.IOException;
import java.util.ArrayList;
import javax.mail.Session;

public class EnviarComunicado{

  private static String cid = "1";
  private static final String BODY = "<html>"
                                   + " <body>"
                                   + "<p>Segue anexo,</p>"
                                   + "<p>Atenciosamente,</p>"
                                   + "<p>Four Administradora</p>"
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

  private final ArrayList<String> to = new ArrayList<>();
  private final String filename_to;

  public EnviarComunicado(){
    this.filename_to = new String();
  }

  public void enviar(Session session, String assunto){

    String subject = "Comunicado sobre " + assunto;
    System.out.println("Enviando email..");
    //ENVIAR EMAILS
    try {
        for(int i = 0; i < to.size(); i++){
            SendAttachment.sendEmail(session, to.get(i), filename_to,
                                     subject, BODY, IMAGE_BODY);
        }
        System.out.println("Email Enviado com sucesso!");
    } catch (IOException ex) {
        System.out.println("Não foi possivel enviar o email!");
	  }
  }
}
