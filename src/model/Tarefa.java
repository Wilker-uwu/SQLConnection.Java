package model;

public class Tarefa {
	private int id = -1;
	private String titulo = null;
	private String desc = null;
	private String prazo = null;
	private String prazoInicio = null;
	private String prazoTermino = null;
	private int metodo = -1;
	
	public Tarefa(final String titulo, final String desc, final String prazo,
			final String prazoInicio, final String prazoTermino, final int metodo) {
		this.titulo = titulo;
		this.desc = desc;
		this.prazo = prazo;
		this.prazoInicio = prazoInicio;
		this.prazoTermino = prazoTermino;
		this.metodo = metodo;
	}
	
	public Tarefa(final int id, final String titulo, final String desc,
			final String prazo, final String prazoInicio,
			final String prazoTermino, final int metodo) {
		this(titulo, desc, prazo, prazoInicio, prazoTermino, metodo);
		if(id<1) { throw new IllegalArgumentException("id cannot be lower then 1."); }
		this.id = id;
	}
	
	public int    getId()           { return this.id; }
	public String getTitulo()       { return this.titulo; }
	public String getDesc()         { return this.desc; }
	public String getPrazo()        { return this.prazo; }
	public String getPrazoInicio()  { return this.prazoInicio; }
	public String getPrazoTermino() { return this.prazoTermino; }
	public int    getMetodo()       { return this.metodo; }
}
