import Classes.Morador;
import java.sql.SQLException;
import java.sql.Statement;

public class InserePessoa{

  Statement sentenca;
  public InserePessoa(){
      Conexao conexao = new Conexao();
      sentenca = conexao.getStatement();
  }

  public int inserirPessoa(String cpf, String nome){

    int insercao = 0;
    System.out.println(">Realizando insercao..");
    try{
      String query;
      query = "INSERT INTO pessoa "
            + "VALUES (DEFAULT, '" + cpf + "', '" + nome + "');";
      sentenca.execute(query);
    }catch(SQLException se){
      System.out.println(">Nao foi possivel realizar a insercao!");
    }
    return insercao;
  }

  public int insrirMorador(Morador morador){

    String id_morador = morador.getId();
    int insercao = 0;
    System.out.println(">Realizando insercao..");
    try{
      String query;
      query = "INSERT INTO tipo_pessoa "
            + "VALUES ('Morador', '" + id_morador + "');";
      sentenca.execute(query);
    }catch(SQLException se){
      System.out.println(">Nao foi possivel realizar a insercao!");
    }
    return insercao;
  }
}