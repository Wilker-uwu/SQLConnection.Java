package model;

public class Alunos {
	private String nome = null;
	private String endereco = null;
	private String bairro = null;
	
	public Alunos(String nome, String endereco, String bairro) {
		this.nome = nome;
		this.endereco = endereco;
		this.bairro = bairro;
	}

	public String getNome() { return this.nome; }
	public String getEndereco() { return this.endereco; }
	public String getBairro() { return this.bairro; }
}
