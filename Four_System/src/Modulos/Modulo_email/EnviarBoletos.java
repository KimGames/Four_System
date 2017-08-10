package Modulos.Modulo_email;

import java.io.IOException;
import java.util.ArrayList;
import javax.mail.Session;

public class EnviarBoletos{

  private final ArrayList<String> to = new ArrayList<>();
  private final ArrayList<String> filename_to = new ArrayList<>();

  public EnviarBoletos(){}

  public void enviar(Session session, String subject){

    System.out.println("Enviando email..");
    //ENVIAR EMAILS
    try {
        for(int i = 0; i < to.size(); i++){
            SendAttachment.sendEmail(session, to.get(i), filename_to.get(i), subject);
        }
        System.out.println("Email Enviado com sucesso!");
    } catch (IOException ex) {
        System.out.println("Não foi possivel enviar o email!");
	  }
  }
}
