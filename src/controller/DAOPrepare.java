package controller;

import java.sql.SQLException;

import model.Pessoa;

public class DAOPrepare {
	public static class DAOPessoa extends JdbcDAO {
		
		//TODO send object to pre
		
		@Override
		public void insert(Pessoa ppl) throws SQLException {
			if(ppl.getId()!=-1) { throw idExp; }
			String sql = String.format(
					"INSERT INTO tbPessoa(pessoaNome, pessoaEmail) VALUES ('%s', '%s');",
					ppl.getNome(), ppl.getEmail());
			execute(sql);
		}
		
		@Override
		public void delete(Pessoa aln) throws SQLException {
			if(aln.getId()==-1) { throw noIdExp; }
			String sql = String.format(
					"DELETE FROM tbpessoa WHERE (idPessoa = '%d') AND (pessoaNome = '%s') AND (pessoaEmail = '%s');",
					aln.getId(), aln.getNome(), aln.getEmail());
			execute(sql);
		}
		
		@Override
		public String toString() {
			
		}
	}
}
