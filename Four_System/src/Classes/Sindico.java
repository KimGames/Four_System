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
public class Sindico {

  private String id_sindico;
  private String cpf;
  private String tipo;

  public Sindico(String id, String cp, String type){
    id_sindico = id;
    cpf = cp;
    tipo = type;
  }

  public Sindico(){}

  public void setId_Sindico(String id){
    id_sindico = id;
  }

  public void setCpf(String cp){
    cpf = cp;
  }

  public void setTipo(String type){
    tipo = type;
  }

  public String getId_Sindico(){
    return id_sindico;
  }

  public String getCpf(){
    return cpf;
  }

  public String getTipo(){
    return tipo;
  }

  public ResultSet consultarSindico(Statement sentenca, String nome){

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

  public ResultSet consultarTodosSindicos(Statement sentenca){

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
