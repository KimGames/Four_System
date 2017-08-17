
import Classes.Pessoa;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class InsereCondominio{

  private Statement sentenca;

  public InsereCondominio(){
      Conexao conexao = new Conexao();
      sentenca = conexao.getStatement();
  }

  public int inserirCondominio(String nome_condominio, String id_sindico,
                               String rua, String bairro, String cidade){

    int insercao = 0;
    System.out.println(">Realizando insercao..");
    try{
      String query;
      query = "INSERT INTO condominio "
            + "VALUES (DEFAULT, '" + nome_condominio
            + "', " + id_sindico + ", '" + rua + "', '" + bairro
            + "', '" + cidade + "');";
      sentenca.execute(query);
      insercao = 1;
    }catch(SQLException se){
      System.out.println(">Nao foi possivel realizar a insercao!");
    }
    return insercao;
  }

  //public int inserirApartamentos(int numero_aps, int blocos, )
}
