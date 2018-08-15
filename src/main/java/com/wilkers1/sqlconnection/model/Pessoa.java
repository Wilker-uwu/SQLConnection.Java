package com.wilkers1.sqlconnection.model;

import java.lang.IllegalArgumentException;

public class Pessoa extends Model{
	private int    id = -1;
	private String nome = null;
	private String email = null;
	
	public Pessoa(final String nome, final String email)
			throws IllegalArgumentException {
		if(nome.length() == 0) {
			throw new IllegalArgumentException("Name cannot be empty.");
		} else if(email.length()<4 || !email.contains("@")) {
			throw new IllegalArgumentException("Invalid or empty email.");
		}
		this.nome = nome;
		this.email = email;
	}
	
	public Pessoa(final int id, final String nome, final String endereco) {
		this(nome, endereco);
		if(id<1) { throw new IllegalArgumentException("id cannot be lower then 1."); }
		this.id = id;
	}
	
	public int    getId()       { return this.id; }
	public String getNome()     { return this.nome; }
	public String getEmail()    { return this.email; }
	
	@Override
	public String toString() { return String.format("('%s', '%s');", this.nome, this.email); }
}
