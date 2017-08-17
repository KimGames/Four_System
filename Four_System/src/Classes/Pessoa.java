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
public class Pessoa {

  private String id;
  private String cpf;
  private String nome;
  private ArrayList<String> telefone;
  private ArrayList<String> email;
  private char tipo;

	/**
	* Default empty Pessoa constructor
	*/
	public Pessoa() {
		super();
	}

	/**
	* Default Pessoa constructor
	*/
	public Pessoa(String id, String cpf, String nome, ArrayList<String> telefone,
                ArrayList<String> email, char tipo) {
		super();
		this.id = id;
		this.cpf = cpf;
		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
		this.tipo = tipo;
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
	* Returns value of tipo
	* @return
	*/
	public char getTipo() {
		return tipo;
	}

	/**
	* Sets new value of tipo
	* @param
	*/
	public void setTipo(char tipo) {
		this.tipo = tipo;
	}

  public ResultSet consultarMorador(Statement sentenca, String nome_condominio,
                                    String nome_morador){

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

  public ResultSet consultarProprietario(Statement sentenca,
                                         String nome_condominio,
                                         String nome_proprietario){

    ResultSet resposta = null;
    System.out.println(">Realizando consulta..");
    try{
        String query;
        query = "SELECT * "
              + "FROM pessoa p, tipo_pessoa t, condominio c, apartamento a "
              + "WHERE t.tipo = 'Proprietario' "
              + "AND p.id = t.id_pessoa "
              + "AND a.id_condominio = c.id "
              + "AND a.id_proprietario = p.id "
              + "AND c.nome = '" + nome_condominio + "' "
              + "AND p.nome = '" + nome_proprietario + "';";
        System.out.println(query);
        //consulta
        resposta = sentenca.executeQuery(query);
    }catch(SQLException se){
        System.out.println(">Nao foi possivel realizar a consulta!!!");
        se.printStackTrace();
    }
    return resposta;
  }

  public ResultSet consultarTodosProprietarios(Statement sentenca,
                                               String nome_condominio)){

    ResultSet resposta = null;
    System.out.println(">Realizando consulta..");
    try{
        String query;
        query = "SELECT * "
              + "FROM pessoa p, tipo_pessoa t, condominio c, apartamento a "
              + "WHERE t.tipo = 'Proprietario' "
              + "AND p.id = t.id_pessoa "
              + "AND a.id_condominio = c.id "
              + "AND a.id_proprietario = p.id "
              + "AND c.nome = '" + nome_condominio + "';";
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
