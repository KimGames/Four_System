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
public class Morador {

  private String id_morador;
  private String pessoa_cpf;
  private String pessoa_nome;

  public Morador(String id, String cpf, String nome){
      id_morador = id;
      pessoa_cpf = cpf;
      pessoa_nome = nome;
  }

  public Morador(){}

  public void setId_Morador(String id){
      id_morador = id;
  }

  public void setCpf(String cpf){
      pessoa_cpf = cpf;
  }

  public void setNome(String nome){
      pessoa_nome = nome;
  }

  public String getId_Morador(){
      return id_morador;
  }

  public String getCpf(){
      return pessoa_cpf;
  }

  public String getNome(){
      return pessoa_nome;
  }

  public ResultSet consultarMorador(Statement sentenca, String nome){

    ResultSet resposta = null;
    System.out.println(">Realizando consulta..");
    try{
        String query;
        query = "SELECT * FROM morador WHERE pessoa_nome LIKE '"+nome+"'";
        System.out.println(query);
        //consulta
        resposta = sentenca.executeQuery(query);
    }catch(SQLException se){
        System.out.println(">Nao foi possivel realizar a consulta!!!");
        se.printStackTrace();
    }
    return resposta;
  }

  public ResultSet consultarTodosMoradores(Statement sentenca){

    ResultSet resposta = null;
    System.out.println(">Realizando consulta..");
    try{
        String query;
        query = "SELECT * FROM morador";
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
