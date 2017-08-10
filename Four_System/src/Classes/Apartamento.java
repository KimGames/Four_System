package Classes;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Kim
 */
public class Apartamento {

  private String numero;
  private String bloco;
  private String condominio;
  private String id_proprietario;
  private String proprietario;
  private String id_morador;
  private String morador;

  public Apartamento(String number, String bl, String cond, String id_p,
                     String prop, String id_m, String mor){
    numero = number;
    bloco = bl;
    condominio = cond;
    id_proprietario = id_p;
    proprietario = prop;
    id_morador = id_m;
    morador = mor;
  }

  public Apartamento(){}

  public void setNumero(String number){
    numero = number;
  }

  public void setBloco(String bl){
    bloco = bl;
  }

  public void setCondominio(String cond){
    condominio = cond;
  }

  public void setId_Proprietario(String id_p){
    id_proprietario = id_p;
  }

  public void setProprietario(String prop){
    proprietario = prop;
  }

  public void setId_Morador(String id_m){
    id_morador = id_m;
  }

  public void setMorador(String mor){
    morador = mor;
  }

  public String getNumero(){
    return numero;
  }

  public String getBloco(){
    return bloco;
  }

  public String getCondominio(){
    return condominio;
  }

  public String getId_Proprietario(){
    return id_proprietario;
  }

  public String getProprietario(){
    return proprietario;
  }

  public String getId_Morador(){
    return id_morador;
  }

  public String getMorador(){
    return morador;
  }

  public ResultSet consultarApartamento(Statement sentenca, String nome){

    ResultSet resposta = null;
    System.out.println(">Realizando consulta..");
    try{
        String query;
        query = "SELECT * FROM sindico WHERE nome LIKE '"+nome+"'";
        System.out.println(query);
        //consulta
        resposta = sentenca.executeQuery(query);
    }catch(SQLException se){
        System.out.println(">Nao foi possivel realizar a consulta!!!");
        se.printStackTrace();
    }
    return resposta;
  }

  public ResultSet consultarTodosApartamentos(Statement sentenca){

    ResultSet resposta = null;
    System.out.println(">Realizando consulta..");
    try{
        String query;
        query = "SELECT * FROM condominio";
        System.out.println(query);
        //consulta
        resposta = sentenca.executeQuery(query);
    }catch(SQLException se){
        System.out.println(">Nao foi possivel realizar a consulta!!!");
        se.printStackTrace();
    }
    return resposta;
  }
}
