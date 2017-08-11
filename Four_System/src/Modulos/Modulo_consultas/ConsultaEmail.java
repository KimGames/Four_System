public class ConsultaEmail{

  Statement sentenca;
  public ConsultaEmail(){
    Conexao conexao = new Conexao();
    sentenca = conexao.getStatement();
  }

  public void emailMorador(String id_condominio, String numero_apartamento){
  }

  public void emailsCondominio(String id_condominio){
  }
}
