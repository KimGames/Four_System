
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ConsultaTelefone{

  Statement sentenca;
  public ConsultaTelefone(){
      Conexao conexao = new Conexao();
      sentenca = conexao.getStatement();
  }

  public ArrayList<String> telefoneMorador(String id_condominio,
                                           String numero_apartamento){

    ArrayList<String> telefones = new ArrayList<String>();
    System.out.println(">Realizando consulta..");
    try{
      ResultSet resposta = null;
      String query;
      query = "SELECT t.telefone "
            + "FROM condominio c, apartamento a, pessoa p, telefones_pessoa t "
            + "WHERE c.id = a.id_condominio "
            + "AND a.id_morador = p.id "
            + "AND p.id = t.id_pessoa "
            + "AND c.id = " + id_condominio + " "
            + "AND a.numero = " + numero_apartamento;
      System.out.println(query);
      //consulta
      resposta = sentenca.executeQuery(query);
      ResultSetMetaData metaDados = resposta.getMetaData();
      int numOfCols = metaDados.getColumnCount();
			while( resposta.next() ){
				for( int i = 1; i <= numOfCols; i++ ){
							telefones.add( resposta.getString(i) );
        }
      }
    }catch(SQLException se){
      System.out.println(">Nao foi possivel realizar a consulta!!!");
    }
    return telefones;
  }

  public ArrayList<String> telefoneProprietario(String id_condominio,
                                                String numero_apartamento){

    ArrayList<String> telefones = new ArrayList<String>();
    System.out.println(">Realizando consulta..");
    try{
      ResultSet resposta = null;
      String query;
      query = "SELECT t.telefone "
            + "FROM condominio c, apartamento a, pessoa p, telefones_pessoa t "
            + "WHERE c.id = a.id_condominio "
            + "AND a.id_proprietario = p.id "
            + "AND p.id = t.id_pessoa "
            + "AND c.id = " + id_condominio + " "
            + "AND a.numero = " + numero_apartamento;
      System.out.println(query);
      //consulta
      resposta = sentenca.executeQuery(query);
      ResultSetMetaData metaDados = resposta.getMetaData();
      int numOfCols = metaDados.getColumnCount();
			while( resposta.next() ){
				for( int i = 1; i <= numOfCols; i++ ){
							telefones.add( resposta.getString(i) );
        }
      }
    }catch(SQLException se){
      System.out.println(">Nao foi possivel realizar a consulta!!!");
    }
    return telefones;
  }
}
