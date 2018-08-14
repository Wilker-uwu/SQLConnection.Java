package controller;

import java.sql.SQLException;

import model.Model;
import model.Pessoa;
import model.RefParticipante;
import model.Tarefa;

class DAOPrepare {
	private static SecurityException idExp = new SecurityException("The constructor with ID is not intended for data registrations.");
	private static SecurityException noIdExp = new SecurityException("Cannot delete registry with an unespecified ID. Risk of unintended deletion.");
	private static ClassCastException invalidModelExp = new ClassCastException("Invalid Model class received.");
	private static String pplInsert = "INSERT INTO tbpessoa(pessoaNome, pessoaEmail) VALUES ";
	private static String pplDelete = "DELETE FROM tbpessoa WHERE ";
	private static String tarInsert = "INSERT INTO tbtarefa (`idTarefa`, `tarefaTitulo`, `tarefaDesc`, `tarefaPrazo`, `tarefaPrazoInicio`, `tarefaPrazoTermino`, `idMetodo`) VALUES ";
	private static String tarDelete = "DELETE FROM tbtarefa";
	
	public static class DAOPessoa extends JdbcDAO {
		
		@Override
		public void insert(Model mdl) throws SQLException {
			if(!(mdl instanceof Pessoa)) { throw invalidModelExp; }
			Pessoa ppl = (Pessoa)mdl;
			if(ppl.getId()!=-1) { throw idExp; }
			String sql = pplInsert +String.format("('%s', '%s');",
					ppl.getNome(), ppl.getEmail());
			execute(pplInsert +sql);
		}
		
		@Override
		public void delete(Model mdl) throws SQLException {
			if(!(mdl instanceof Pessoa)) { throw invalidModelExp; }
			Pessoa ppl = (Pessoa)mdl;
			if(ppl.getId()==-1) { throw noIdExp; }
			String sql = String.format("(idPessoa = '%d') AND (pessoaNome = '%s') AND (pessoaEmail = '%s');",
					ppl.getId(), ppl.getNome(), ppl.getEmail());
			execute(pplDelete +sql);
		}
		
	}
	
	public static class DAORefPar extends JdbcDAO {
		
		@Override
		public void insert(Model mdl) throws SQLException {
			if(!(mdl instanceof RefParticipante)) { throw invalidModelExp; }
			RefParticipante ref = (RefParticipante)mdl;
			if(ref.getPessoa()<1 || ref.getTarefa()<1) { throw idExp; }
			String sql = String.format("('%s', '%s');", ref.getTarefa(), ref.getPessoa());
			execute(pplInsert +sql);
		}
		
		@Override
		public void delete(Model mdl) throws SQLException {
			if(!(mdl instanceof RefParticipante)) { throw invalidModelExp; }
			RefParticipante ref = (RefParticipante)mdl;
			if(ref.getPessoa()==-1 || ref.getTarefa()==-1) { throw noIdExp; }
			String sql = String.format("(idPessoa = '%d') AND (pessoaNome = '%s') AND (pessoaEmail = '%s');",
					ref.getId(), ref.getNome(), ref.getEmail());
			execute(pplDelete +sql);
		}
		
	}
	
	private static String prepare
}
