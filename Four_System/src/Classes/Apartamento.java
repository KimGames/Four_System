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
  private String id_condominio;
  private String id_proprietario;
  private String id_morador;
  private String diretorio_boleto;

	/**
	* Default empty Apartamento constructor
	*/
	public Apartamento() {
		super();
	}

	/**
	* Default Apartamento constructor
	*/
	public Apartamento(String numero, String bloco, String id_condominio,
                     String id_proprietario, String id_morador,
                     String diretorio_boleto) {
		super();
		this.numero = numero;
		this.bloco = bloco;
		this.id_condominio = id_condominio;
		this.id_proprietario = id_proprietario;
		this.id_morador = id_morador;
		this.diretorio_boleto = diretorio_boleto;
	}

	/**
	* Returns value of numero
	* @return
	*/
	public String getNumero() {
		return numero;
	}

	/**
	* Sets new value of numero
	* @param
	*/
	public void setNumero(String numero) {
		this.numero = numero;
	}

	/**
	* Returns value of bloco
	* @return
	*/
	public String getBloco() {
		return bloco;
	}

	/**
	* Sets new value of bloco
	* @param
	*/
	public void setBloco(String bloco) {
		this.bloco = bloco;
	}

	/**
	* Returns value of id_condominio
	* @return
	*/
	public String getId_condominio() {
		return id_condominio;
	}

	/**
	* Sets new value of id_condominio
	* @param
	*/
	public void setId_condominio(String id_condominio) {
		this.id_condominio = id_condominio;
	}

	/**
	* Returns value of id_proprietario
	* @return
	*/
	public String getId_proprietario() {
		return id_proprietario;
	}

	/**
	* Sets new value of id_proprietario
	* @param
	*/
	public void setId_proprietario(String id_proprietario) {
		this.id_proprietario = id_proprietario;
	}

	/**
	* Returns value of id_morador
	* @return
	*/
	public String getId_morador() {
		return id_morador;
	}

	/**
	* Sets new value of id_morador
	* @param
	*/
	public void setId_morador(String id_morador) {
		this.id_morador = id_morador;
	}

	/**
	* Returns value of diretorio_boleto
	* @return
	*/
	public String getDiretorio_boleto() {
		return diretorio_boleto;
	}

	/**
	* Sets new value of diretorio_boleto
	* @param
	*/
	public void setDiretorio_boleto(String diretorio_boleto) {
		this.diretorio_boleto = diretorio_boleto;
	}

  public ResultSet consultarApartamento(Statement sentenca, String condominio,
                                        String numero){

    ResultSet resposta = null;
    System.out.println(">Realizando consulta..");
    try{
        String query;
        query = "SELECT * "
              + "FROM condominio c, apartamento a "
              + "WHERE c.id = a.id_condominio "
              + "AND c.id = " + condominio + " "
              + "AND a.numero = " + numero + ";";
        System.out.println(query);
        //consulta
        resposta = sentenca.executeQuery(query);
    }catch(SQLException se){
        System.out.println(">Nao foi possivel realizar a consulta!!!");
        se.printStackTrace();
    }
    return resposta;
  }

  public ResultSet consultarTodosApartamentos(Statement sentenca,
                                              String condominio){

    ResultSet resposta = null;
    System.out.println(">Realizando consulta..");
    try{
        String query;
        query = "SELECT * "
              + "FROM condominio c, apartamento a "
              + "WHERE c.id = a.id_condominio "
              + "AND c.id = " + condominio + ";";
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