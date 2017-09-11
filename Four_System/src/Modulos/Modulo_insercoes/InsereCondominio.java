import Classes.Morador;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class InsereCondominio{

  Statement sentenca;
  public InsereCondominio(){
      Conexao conexao = new Conexao();
      sentenca = conexao.getStatement();
  }

  public int inserirCondominio(){

    int insercao = 0;
    System.out.println(">Realizando insercao..");
    try{
      String query;
      query = "INSERT INTO condominio "
            + "VALUES (DEFAULT, 'Condominio1', 1, 'Rua 1', 'Santa Monica', 'Uberlândia');";
      sentenca.execute(query);
    }catch(SQLException se){
      System.out.println(">Nao foi possivel realizar a insercao!");
    }
    return insercao;
  }
}