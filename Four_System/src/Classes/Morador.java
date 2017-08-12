package Classes;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
public class Morador {

  private String id;
  private String cpf;
  private String nome;
  private ArrayList<String> telefone;
  private ArrayList<String> email;
  private String numero;
  private String bloco;
  private String id_condominio;

	/**
	* Default empty Morador constructor
	*/
	public Morador() {
		super();
	}

	/**
	* Default Morador constructor
	*/
	public Morador(String id, String cpf, String nome, ArrayList<String> telefone,
                 ArrayList<String> email, String numero, String bloco,
                 String id_condominio) {
		super();
		this.id = id;
		this.cpf = cpf;
		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
		this.numero = numero;
		this.bloco = bloco;
		this.id_condominio = id_condominio;
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
	* Returns value of cpf
	* @return
	*/
	public String getCpf() {
		return cpf;
	}

	/**
	* Sets new value of cpf
	* @param
	*/
	public void setCpf(String cpf) {
		this.cpf = cpf;
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
	* Returns value of telefone
	* @return
	*/
	public ArrayList<String> getTelefone() {
		return telefone;
	}

	/**
	* Sets new value of telefone
	* @param
	*/
	public void setTelefone(ArrayList<String> telefone) {
		this.telefone = telefone;
	}

	/**
	* Returns value of email
	* @return
	*/
	public ArrayList<String> getEmail() {
		return email;
	}

	/**
	* Sets new value of email
	* @param
	*/
	public void setEmail(ArrayList<String> email) {
		this.email = email;
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
     * @param numero	*/
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
     * @param bloco	*/
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
     * @param id_condominio	*/
	public void setId_condominio(String id_condominio) {
		this.id_condominio = id_condominio;
	}

  public ResultSet consultarMorador(Statement sentenca, String nome_condominio,
                                    String nome_morador){

    ResultSet resposta = null;
    System.out.println(">Realizando consulta..");
    try{
        String query;
        query = "SELECT * "
              + "FROM pessoa p, tipo_pessoa t "
              + "WHERE t.tipo = 'Morador' "
              + "AND p.id = t.id_pessoa "
              + "AND a.id_condominio = c.id "
              + "AND a.id_morador = p.id "
              + "AND c.nome = '" + nome_condominio + "' "
              + "AND p.nome = '" + nome_morador + "';";
        System.out.println(query);
        //consulta
        resposta = sentenca.executeQuery(query);
    }catch(SQLException se){
        System.out.println(">Nao foi possivel realizar a consulta!!!");
    }
    return resposta;
  }

  public ResultSet consultarTodosMoradores(Statement sentenca,
                                           String nome_condominio){

    ResultSet resposta = null;
    System.out.println(">Realizando consulta..");
    try{
        String query;
        query = "SELECT * "
              + "FROM pessoa p, tipo_pessoa t, condominio c, apartamento a "
              + "WHERE t.tipo = 'Morador' "
              + "AND p.id = t.id_pessoa "
              + "AND a.id_condominio = c.id "
              + "AND a.id_morador = p.id "
              + "AND c.nome = '" + nome_condominio + "';";
        System.out.println(query);
        //consulta
        resposta = sentenca.executeQuery(query);
    }catch(SQLException se){
        System.out.println(">Nao foi possivel realizar a consulta!!!");
    }
    return resposta;
  }
}
