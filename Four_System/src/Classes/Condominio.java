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
    
  private String id;
  //private int id;
  private String nome;
  private String id_sindico;
  private String rua;
  private String bairro;
  private String cidade;

	/**
	* Default empty Condominio constructor
	*/
	public Condominio() {
		super();
	}

	/**
	* Default Condominio constructor
	*/
	public Condominio(String id, String nome, String id_sindico, String rua, String bairro, String cidade) {
		super();
		this.id = id;
		this.nome = nome;
		this.id_sindico = id_sindico;
		this.rua = rua;
		this.bairro = bairro;
		this.cidade = cidade;
	}

	/**
	* Returns value of id
	* @return
	*/
	public String getId() {
		return id;
	}

	/**
	* Sets new value of id
	* @param
	*/
	public void setId(String id) {
		this.id = id;
	}

	/**
	* Returns value of nome
	* @return
	*/
	public String getNome() {
		return nome;
	}

	/**
	* Sets new value of nome
	* @param
	*/
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	* Returns value of id_sindico
	* @return
	*/
	public String getId_sindico() {
		return id_sindico;
	}

	/**
	* Sets new value of id_sindico
	* @param
	*/
	public void setId_sindico(String id_sindico) {
		this.id_sindico = id_sindico;
	}

	/**
	* Returns value of rua
	* @return
	*/
	public String getRua() {
		return rua;
	}

	/**
	* Sets new value of rua
	* @param
	*/
	public void setRua(String rua) {
		this.rua = rua;
	}

	/**
	* Returns value of bairro
	* @return
	*/
	public String getBairro() {
		return bairro;
	}

	/**
	* Sets new value of bairro
	* @param
	*/
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	/**
	* Returns value of cidade
	* @return
	*/
	public String getCidade() {
		return cidade;
	}

	/**
	* Sets new value of cidade
	* @param
	*/
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

  public ResultSet consultarCondominio(Statement sentenca, String nome){

    ResultSet resposta = null;
    System.out.println(">Realizando consulta..");
    try{
        String query;
        query = "SELECT * "
              + "FROM condominio "
              + "WHERE nome = " + nome + ";";
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
        query = "SELECT * "
              + "FROM condominio;";
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