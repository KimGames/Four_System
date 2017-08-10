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
public class Condominio {

  private String id_condominio;
  private String nome;
  private String sindico;

  public Condominio(String id, String name, String sin){
    id_condominio = id;
    nome = name;
    sindico = sin;
  }

  public Condominio(){}

  public void setId_Condominio(String id){
    id_condominio = id;
  }

  public void setNome(String name){
    nome = name;
  }

  public void setSindico(String sin){
    sindico = sin;
  }

  public String getId_Condominio(){
    return id_condominio;
  }

  public String getNome(){
    return nome;
  }

  public String getSindico(){
    return sindico;
  }

  public ResultSet consultarCondominio(Statement sentenca, String nome){

    ResultSet resposta = null;
    System.out.println(">Realizando consulta..");
    try{
        String query;
        query = "SELECT * FROM condominio WHERE nome LIKE '"+nome+"'";
        System.out.println(query);
        //consulta
        resposta = sentenca.executeQuery(query);
    }catch(SQLException se){
        System.out.println(">Nao foi possivel realizar a consulta!!!");
        se.printStackTrace();
    }
    return resposta;
  }

  public ResultSet consultarTodosCondominios(Statement sentenca){

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
