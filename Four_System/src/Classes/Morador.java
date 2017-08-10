package Classes;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Kim
 */
public class Morador {
    
    public String id;
    private String[] email;
    private String boleto;
    
    public Morador(String[] emails, String bol, String ids){
        System.arraycopy(emails, 0, email, 0, emails.length);
        boleto = bol;
        id = ids;
    }
    
    public Morador(){}
    
    public void setEmail(String[] emails){
        System.arraycopy(emails, 0, email, 0, emails.length);
    }
    
    public void setBoleto(String bol){
        boleto = bol;
    }
    
    public String[] getEmail(){
        return email;
    }
    
    public String getBoleto(){
        return boleto;
    }
}
