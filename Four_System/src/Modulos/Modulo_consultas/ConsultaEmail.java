
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ConsultaEmail{

  Statement sentenca;
  public ConsultaEmail(){
      Conexao conexao = new Conexao();
      sentenca = conexao.getStatement();
  }

  public ArrayList<String> emailMorador(String id_condominio,
                                        String numero_apartamento){

    ArrayList<String> emails = new ArrayList<String>();
    System.out.println(">Realizando consulta..");
    try{
      ResultSet resposta = null;
      String query;
      query = "SELECT e.email "
            + "FROM condominio c, apartamento a, pessoa p, emails_pessoa e "
            + "WHERE c.id = a.id_condominio "
            + "AND a.id_morador = p.id "
            + "AND p.id = e.id_pessoa "
            + "AND c.id = " + id_condominio + " "
            + "AND a.numero = " + numero_apartamento;
      System.out.println(query);
      //consulta
      resposta = sentenca.executeQuery(query);
      ResultSetMetaData metaDados = resposta.getMetaData();
      int numOfCols = metaDados.getColumnCount();
			while( resposta.next() ){
				for( int i = 1; i <= numOfCols; i++ ){
							emails.add( resposta.getString(i) );
        }
      }
    }catch(SQLException se){
      System.out.println(">Nao foi possivel realizar a consulta!!!");
      se.printStackTrace();
    }
    return emails;
  }

  public ArrayList<String> emailsMoradoresCondominio(String id_condominio){

    ArrayList<String> emails = new ArrayList<String>();
    System.out.println(">Realizando consulta..");
    try{
      ResultSet resposta = null;
      String query;
      query = "SELECT e.email "
            + "FROM condominio c, apartamento a, pessoa p, emails_pessoa e "
            + "WHERE c.id = a.id_condominio "
            + "AND a.id_morador = p.id "
            + "AND e.id_pessoa = p.id "
            + "AND c.id = " + id_condominio;
      System.out.println(query);
      //consulta
      resposta = sentenca.executeQuery(query);
      ResultSetMetaData metaDados = resposta.getMetaData();
      int numOfCols = metaDados.getColumnCount();
			while( resposta.next() ){
				for( int i = 1; i <= numOfCols; i++ ){
							emails.add( resposta.getString(i) );
        }
      }
    }catch(SQLException se){
      System.out.println(">Nao foi possivel realizar a consulta!!!");
      se.printStackTrace();
    }
    return emails;
  }

  public ArrayList<String> emailProprietario(String id_condominio,
                                        String numero_apartamento){

    ArrayList<String> emails = new ArrayList<String>();
    System.out.println(">Realizando consulta..");
    try{
      ResultSet resposta = null;
      String query;
      query = "SELECT e.email "
            + "FROM condominio c, apartamento a, pessoa p, emails_pessoa e "
            + "WHERE c.id = a.id_condominio "
            + "AND a.id_proprietario = p.id "
            + "AND p.id = e.id_pessoa "
            + "AND c.id = " + id_condominio + " "
            + "AND a.numero = " + numero_apartamento;
      System.out.println(query);
      //consulta
      resposta = sentenca.executeQuery(query);
      ResultSetMetaData metaDados = resposta.getMetaData();
      int numOfCols = metaDados.getColumnCount();
			while( resposta.next() ){
				for( int i = 1; i <= numOfCols; i++ ){
							emails.add( resposta.getString(i) );
        }
      }
    }catch(SQLException se){
      System.out.println(">Nao foi possivel realizar a consulta!!!");
      se.printStackTrace();
    }
    return emails;
  }

  public ArrayList<String> emailsProprietariosCondominio(String id_condominio){

    ArrayList<String> emails = new ArrayList<String>();
    System.out.println(">Realizando consulta..");
    try{
      ResultSet resposta = null;
      String query;
      query = "SELECT e.email "
            + "FROM condominio c, apartamento a, pessoa p, emails_pessoa e "
            + "WHERE c.id = a.id_condominio "
            + "AND a.id_proprietario = p.id "
            + "AND e.id_pessoa = p.id "
            + "AND c.id = " + id_condominio + ";";
      System.out.println(query);
      //consulta
      resposta = sentenca.executeQuery(query);
      ResultSetMetaData metaDados = resposta.getMetaData();
      int numOfCols = metaDados.getColumnCount();
			while( resposta.next() ){
				for( int i = 1; i <= numOfCols; i++ ){
							emails.add( resposta.getString(i) );
        }
      }
    }catch(SQLException se){
      System.out.println(">Nao foi possivel realizar a consulta!!!");
      se.printStackTrace();
    }
    return emails;
  }
}
