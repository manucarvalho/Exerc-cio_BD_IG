package Exercicio_BD_IG;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class BancoConsole {

	static Connection conexao = null;
	static Statement declaracao = null;
	static ResultSet resultado = null;
	static Pessoa pessoa = new Pessoa();
	
	public BancoConsole() {
		
	}

	public void conectar() {
		String url = "jdbc:mysql://localhost:3306/bd_pessoa?serverTimezone=UTC";

		try {
			conexao = DriverManager.getConnection(url, "root", "");			
			declaracao = conexao.createStatement();
		}
		catch(Exception erro) {
			System.out.println("Erro " + erro.getMessage());
		}
	}
	
	public boolean isConected() {
		if (conexao != null) {
			return true;
		} else {
			return false;
		}
	}

	public void desconectar() {
		try {
			if (isConected()) {
				conexao.close();				
			}
		}
		catch(Exception erro) {
			System.out.println("Erro " + erro.getMessage());
		}		
	}
	
	public void inserirUsuario(Pessoa pessoa) {
		conectar();
		try {

    		String query = "INSERT INTO pessoa (nome, email, rendaFamiliar, qtdePessoas) VALUES ('" +
                    pessoa.getNome() + "', '" + pessoa.getEmail() + "', '" + pessoa.getRendaFamiliar() + "', '" + pessoa.getQtdePessoas() +"');";
		    PreparedStatement inserir = conexao.prepareStatement(query);
		    inserir.executeUpdate();
		    System.out.println("\n Inclusão realizada com sucesso!!!\n");
		}
		catch(Exception erro) {
			System.out.println("Erro " + erro.getMessage());
		}		
		finally {
			desconectar();
		}							
	}

	public void alterarUsuario(Pessoa pessoa) {
		conectar();
		try {
    		String query = "UPDATE pessoa SET nome = '" +
 			           pessoa.getNome() + "', email = '" + pessoa.getEmail() + "', rendaFamiliar = '" + pessoa.getRendaFamiliar() + "', qtdePessoas = '" + pessoa.getQtdePessoas() + "' WHERE id = " + pessoa.getId() + ";";
		    PreparedStatement alterar = conexao.prepareStatement(query);
		    alterar.executeUpdate();
		    System.out.println("\n Alteração realizada com sucesso!!!\n");
		}
		catch(Exception erro) {
			System.out.println("Erro " + erro.getMessage());
		}		
		finally {
			desconectar();
		}							
	}


	public void listarPessoas() {

		conectar();
		try {
			String query = "SELECT * FROM pessoa";
			PreparedStatement pesquisa = conexao.prepareStatement(query);
			ResultSet resultado = pesquisa.executeQuery();
			
			System.out.printf("\n         Lista de Usuários");
			System.out.printf("\n----------------------------------------------------------");
			System.out.printf("\nID -   Nome                   Email          Renda(R$)       Qtde Pessoas");
			System.out.printf("\n----------------------------------------------------------");
			while (resultado.next()) {
				int id = resultado.getInt("id");
				String nome = resultado.getString("nome");
				String email = resultado.getString("email");
				float renda = resultado.getFloat("rendaFamiliar");
				int qtde = resultado.getInt("qtdePessoas");
				System.out.printf("\n%4d - %-20s - %-20s - %.2f - %d", id, nome, email, renda, qtde);
			}			
			System.out.printf("\n--------------------------------------------------------\n");
		}
		catch(Exception erro) {
			System.out.println("Erro " + erro.getMessage());
		}
		finally {
			desconectar();
		}							
	}
		
	public void excluirUsuario(Pessoa pessoa) {

		conectar();
		try {

    		String query = "DELETE FROM pessoa " +
 			       "WHERE id = " + pessoa.getId() + ";";
		    PreparedStatement inserir = conexao.prepareStatement(query);
		    inserir.executeUpdate();
		    
		    System.out.println("\n Exclusao realizada com sucesso!!!\n");
		}
		catch(Exception erro) {
			System.out.println("Erro " + erro.getMessage());
		}		
		finally {
			desconectar();
		}							
	}

	public void consultarUsuario(Pessoa pessoa) {

		conectar();
		try {
			String query = "SELECT * FROM pessoa WHERE id = " + pessoa.getId() + ";";
			PreparedStatement pesquisa = conexao.prepareStatement(query);
			ResultSet resultado = pesquisa.executeQuery();
			
			while (resultado.next()) {
				int id= resultado.getInt("id");
				String nome = resultado.getString("nome");
				String email = resultado.getString("email");
				float renda = resultado.getFloat("rendaFamiliar");
				int qtde = resultado.getInt("qtdePessoas"); 
				System.out.printf("\nID....: %d", id);
				System.out.printf("\nNome..: %s", nome);
				System.out.printf("\nEmail..: %s ", email);
				System.out.printf("\nRenda Familiar..: R$ %.2f ", renda);
				System.out.printf("\nQtde Pessoas..: %d ", qtde);
			}			
			System.out.printf("\n-----------------------------------------\n");
		}
		catch(Exception erro) {
			System.out.println("Erro " + erro.getMessage());
		}
		finally {
			desconectar();
		}							
	}
	
	public void consultarId(Pessoa pessoa) {

		conectar();
		try {
			String query = "SELECT * FROM pessoa WHERE nome = '" + pessoa.getNome() + "';";
			PreparedStatement pesquisa = conexao.prepareStatement(query);
			ResultSet resultado = pesquisa.executeQuery();
			while (resultado.next()) {
				int id = resultado.getInt("id");
				pessoa.setId(id);
			}
		}
		catch(Exception erro) {
			System.out.println("Erro " + erro.getMessage());
		}
		finally {
			desconectar();
		}
		
	}

}
