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
public class Condominio {
    
    private Morador[] morador;
    
    public Condominio(Morador[] pessoa){
        System.arraycopy(pessoa, 0, morador, 0, pessoa.length);
    }
    
    public Condominio(){}
    
    public void setMorador(String[] pessoa){
        System.arraycopy(pessoa, 0, morador, 0, pessoa.length);
    }
    
    public Morador[] getMorador(){
        return morador;
    }
}
