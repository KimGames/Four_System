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
public class Proprietario {

  private String id_proprietario;
  private String pessoa_cpf;
  private String pessoa_nome;

  public Proprietario(String id, String cpf, String nome){
      id_proprietario = id;
      pessoa_cpf = cpf;
      pessoa_nome = nome;
  }

  public Proprietario(){}

  public void setId_Proprietario(String id){
      id_proprietario = id;
  }

  public void setCpf(String cpf){
      pessoa_cpf = cpf;
  }

  public void setNome(String nome){
      pessoa_nome = nome;
  }

  public String getId_Proprietario(){
      return id_proprietario;
  }

  public String getCpf(){
      return pessoa_cpf;
  }

  public String getNome(){
      return pessoa_nome;
  }

  public ResultSet consultarProprietario(Statement sentenca, String nome){

    ResultSet resposta = null;
    System.out.println(">Realizando consulta..");
    try{
        String query;
        query = "SELECT * FROM proprietario WHERE pessoa_nome LIKE '"+nome+"'";
        System.out.println(query);
        //consulta
        resposta = sentenca.executeQuery(query);
    }catch(SQLException se){
        System.out.println(">Nao foi possivel realizar a consulta!!!");
        se.printStackTrace();
    }
    return resposta;
  }

  public ResultSet consultarTodosProprietarios(Statement sentenca){

    ResultSet resposta = null;
    System.out.println(">Realizando consulta..");
    try{
        String query;
        query = "SELECT * FROM proprietario";
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
