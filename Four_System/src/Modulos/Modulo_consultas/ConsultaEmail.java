
import java.sql.Statement;
import java.util.ArrayList;

public class ConsultaEmail{

  Statement sentenca;
  public ConsultaEmail(){
      Conexao conexao = new Conexao();
      sentenca = conexao.getStatement();
  }

  public ArrayList<String> emailMorador(String id_condominio, String numero_apartamento){

    ArrayList<String> emails = new ArrayList<String>();
    System.out.println(">Realizando consulta..");
    try{
      ResultSet resposta = null;
      String query;
      query = "SELECT e.endereco_email FROM condominio c, apartamento a, "
            + "pessoa p, emails_pessoa e WHERE c.id = a.condominio_id AND "
            + "a.pessoa_id = p.id AND p.id = e.pessoa_id"
            + "AND c.id = " + id_condominio + "AND a.numero = " + numero_apartamento+ ";";
      System.out.println(query);
      //consulta
      resposta = sentenca.executeQuery(query);
    }catch(SQLException se){
      System.out.println(">Nao foi possivel realizar a consulta!!!");
      se.printStackTrace();
    }
  }

  public void emailsCondominio(String id_condominio){
  }
}
