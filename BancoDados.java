package Exercicio_BD_IG;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.SQLSyntaxErrorException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class BancoDados {

	static Connection conexao = null;
	static Statement declaracao = null;
	static ResultSet resultado = null;
	static Pessoa pessoa = new Pessoa();
	static IG ig = new IG();
	
	public BancoDados() {
		
	}

	public void conectar() {
		String url = "jdbc:mysql://localhost:3306/bd_pessoa?serverTimezone=UTC";

		try {
			conexao = DriverManager.getConnection(url, "root", "");			
			declaracao = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
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
		    JOptionPane.showMessageDialog(null,"Inclusão realizada com sucesso!!!");
		}
		catch(Exception erro) {
			JOptionPane.showMessageDialog(null, "Erro " + erro.getMessage());
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
		    JOptionPane.showMessageDialog(null,"Alteração realizada com sucesso!!!");
		}
		catch(Exception erro) {
			JOptionPane.showMessageDialog(null, "Erro " + erro.getMessage());
		}		
		finally {
			desconectar();
		}							
	}


	public List<Pessoa> listarPessoas() throws SQLException {

		Pessoa p = null;
		List<Pessoa> lista = new ArrayList<>();
		conectar();
	    String query = "SELECT * FROM pessoa";
		PreparedStatement pesquisa = conexao.prepareStatement(query);
		ResultSet resultado = pesquisa.executeQuery();
		while (resultado.next()) {
			p = new Pessoa();
			p.setId(resultado.getInt("id"));
			p.setNome(resultado.getString("nome"));
			p.setEmail(resultado.getString("email"));
			p.setRendaFamiliar(resultado.getFloat("rendaFamiliar"));
			p.setQtdePessoas(resultado.getInt("qtdePessoas"));
			lista.add(p);	
		}	
		desconectar();
		return lista;									
	}
		
	public void excluirUsuario(Pessoa pessoa) {

		conectar();
		try {

    		String query = "DELETE FROM pessoa " +
 			       "WHERE id = " + pessoa.getId() + ";";
		    PreparedStatement inserir = conexao.prepareStatement(query);
		    inserir.executeUpdate();
		    
		    JOptionPane.showMessageDialog(null,"Exclusao realizada com sucesso!!!");
		}
		catch(Exception erro) {
			JOptionPane.showMessageDialog(null, "Erro " + erro.getMessage());
		}		
		finally {
			desconectar();
		}							
	}

	public Pessoa consultarUsuario(Pessoa pessoa) {
		Pessoa pes = null;
		conectar();
		try {
			String query = "SELECT * FROM pessoa WHERE id = " + pessoa.getId() + ";";
			PreparedStatement pesquisa = conexao.prepareStatement(query);
			ResultSet resultado = pesquisa.executeQuery();
			
			while (resultado.next()) {
				pes = new Pessoa(resultado.getInt("id"), resultado.getString("nome"), resultado.getString("email"), resultado.getFloat("rendaFamiliar"), resultado.getInt("qtdePessoas"));
			}
		}
		catch(Exception erro) {
			JOptionPane.showMessageDialog(null, "Erro " + erro.getMessage());
		}
		finally {
			desconectar();
			
		}	
		return pes;
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
				String idString = Integer.toString(id);
				ig.setIdTxt(new JTextField(idString));
			}
		}
		catch(Exception erro) {
			JOptionPane.showMessageDialog(null, "Erro " + erro.getMessage());
		}
		finally {
			desconectar();
		}
		
	}

}
