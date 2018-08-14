package model;

public class RefParticipante extends Model{
	private int tarefa = -1;
	private int pessoa = -1;
	
	public RefParticipante(final int tarefa, final int pessoa)
			throws IllegalArgumentException {
		if(tarefa<1 || pessoa<1) {
			throw new IllegalArgumentException("Valores não podem ser menores que 1.");
		}
		this.tarefa = tarefa;
		this.pessoa = pessoa;
	}
	
	public int getTarefa() { return this.tarefa; }
	public int getPessoa() { return this.pessoa; }
}
