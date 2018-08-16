package com.wilkers1.sqlconnection.model;

public class RefParticipante extends Model{
	private int tarefa = -1;
	private int pessoa = -1;
	
	public RefParticipante(final int tarefa, final int pessoa) throws IllegalArgumentException {
		if(tarefa<1 || pessoa<1) {
			throw new IllegalArgumentException("Valores n�o podem ser menores que 1.");
		}
		this.tarefa = tarefa;
		this.pessoa = pessoa;
	}
	
	public int getTarefa() { return this.tarefa; }
	public int getPessoa() { return this.pessoa; }
	
	@Override
	public String toString() { return String.format("(%d, %d)", this.getTarefa(), this.getPessoa()); }
	public String toString(boolean remove) throws IllegalAccessException {
		return remove?
				String.format("(idTarefa = %d, idPessoa = %d)", this.getTarefa(), this.getPessoa()):
				this.toString();
	}
}
