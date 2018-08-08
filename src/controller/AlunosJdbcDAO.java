package controller;

import java.lang.SecurityException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import model.Alunos;

public class AlunosJdbcDAO {
	private static Connection _conn = null;
	private static SecurityException idExp = new SecurityException("The constructor with ID is not intended for data registrations.");
	private static SecurityException noIdExp = new SecurityException("Cannot delete registry with an unespecified ID. Risk of unintended deletion.");
	
	private static void execute(String query) throws SQLException, NullPointerException {
		if(_conn == null) {
			try {
				_conn = JdbUtil.getConnection();
			} catch (Exception exp) {
				exp.printStackTrace();
			}
		}
		try {
			Statement statement = _conn.createStatement();
			statement.execute(query);
			statement.close();
		} catch (NullPointerException exp) {
			throw new NullPointerException("Could not find " +Connection.class.getName() +" instance.");
		}
		JdbUtil.close();
	}
	
	public static void insert(Alunos aln) throws SQLException {
		if(aln.getId()!=-1) { throw idExp; }
		String sql = String.format(
				"INSERT INTO Alunos(nome, endereco, bairro) VALUES ('%s', '%s', '%s');",
				aln.getNome(), aln.getEndereco(), aln.getBairro());
		execute(sql);
	}
	
	public static void delete(Alunos aln) throws SQLException {
		if(aln.getId()==-1) { throw noIdExp; }
		String sql = String.format(
				"DELETE FROM Alunos WHERE (id = '%s') AND (nome = '%s') AND (endereco = '%s') AND (bairro = '%s');",
				aln.getId(), aln.getNome(), aln.getEndereco(), aln.getBairro());
		execute(sql);
	}
}
