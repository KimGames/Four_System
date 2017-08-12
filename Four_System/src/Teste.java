
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Kim
 */
public class Teste {
    
    public static void main(String[] args) {
        ConsultaEmail consulta = new ConsultaEmail();
        
        ArrayList<String> emails = new ArrayList<String>();
        
        emails = consulta.emailMorador("1000", "102");
        
        for(int i = 0; i < emails.size(); i++){
            System.out.println(emails.get(i));
        }
    }
    
}
