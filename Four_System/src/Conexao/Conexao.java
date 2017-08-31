<<<<<<< HEAD
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class Conexao {
  private Connection conexao; //gerencia conexao
  private Statement sentenca;

  public Conexao(){

      System.out.println(">Checando conexao com o banco");
      try{
          //Estabelece conexao com o banco de dados
          System.out.println(">Conectando com o servidor: ");
          String url = "jdbc:postgresql://localhost/postgres?user=postgres&password=conectax";
          conexao = DriverManager.getConnection(url);
          System.out.println(">Conectado!");
          //cria uma sentenca para consultar o banco de dados
          sentenca = conexao.createStatement();
          sentenca.execute("SET search_path TO banco_four;");
      }catch(SQLException se){
          System.out.println(">Nao foi possivel conectar ao banco de dados.");
          se.printStackTrace();
      }
  }

  public Statement getStatement(){
    return sentenca;
  }

}
=======
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class Conexao {
  private Connection conexao; //gerencia conexao
  private Statement sentenca;

  public Conexao(){

      System.out.println(">Checando conexao com o banco");
      try{
          //Estabelece conexao com o banco de dados
          System.out.println(">Conectando com o servidor: ");
          String url = "jdbc:postgresql://localhost/postgres?user=postgres&password=conectax";
          conexao = DriverManager.getConnection(url);
          System.out.println(">Conectado!");
          //cria uma sentenca para consultar o banco de dados
          sentenca = conexao.createStatement();
          sentenca.execute("SET search_path TO banco_four;");
      }catch(SQLException se){
          System.out.println(">Nao foi possivel conectar ao banco de dados.");
          se.printStackTrace();
      }
  }

  public Statement getStatement(){
    return sentenca;
  }

}
>>>>>>> 55f453466159db58662fb0e581997e8099950887
