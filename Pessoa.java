package Exercicio_BD_IG;

public class Pessoa {

	private int id;
	private String nome;
	private String email;
	private Float rendaFamiliar;
	private int qtdePessoas;
	
	
	public Pessoa() {
		
	}
	
	public Pessoa(int id) {
		this.id = id;
	}
	
	public Pessoa(String nome, String email) {
		this.nome = nome;
		this.email = email;
	}
	
	public Pessoa(int id, String nome, String email) {
		this.id = id;
		this.nome = nome;
		this.email = email;
	}
	
	public Pessoa(String nome, String email, Float rendaFamiliar, int qtdePessoas) {
		this.nome = nome;
		this.email = email;
		this.rendaFamiliar = rendaFamiliar;
		this.qtdePessoas = qtdePessoas;
	}	

	public Pessoa(int id, String nome, String email, Float rendaFamiliar, int qtdePessoas) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.rendaFamiliar = rendaFamiliar;
		this.qtdePessoas = qtdePessoas;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Float getRendaFamiliar() {
		return rendaFamiliar;
	}

	public void setRendaFamiliar(Float rendaFamiliar) {
		this.rendaFamiliar = rendaFamiliar;
	}

	public int getQtdePessoas() {
		return qtdePessoas;
	}

	public void setQtdePessoas(int qtdePessoas) {
		this.qtdePessoas = qtdePessoas;
	}
		
}
