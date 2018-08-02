package controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import model.Alunos;

public class AlunosJdbcDAO {
	private static Connection _conn = null;
	
	private static void execute(String query) throws SQLException {
		if(_conn == null) {
			try {
				_conn = JdbUtil.getConnection();
			} catch (Exception exp) {
				exp.printStackTrace();
			}
		}
		Statement statement = _conn.createStatement();
		statement.execute(query);
		statement.close();
		JdbUtil.close();
	}
	
	public static void insert(Alunos aln) throws SQLException {
		String sql = String.format(
				"INSERT INTO Alunos(nome, endereco, bairro) VALUES ('%s', '%s', '%s');",
				aln.getNome(), aln.getEndereco(), aln.getBairro());
		execute(sql);
	}
	
	public static void delete(Alunos aln) throws SQLException {
		String sql = String.format(
				"DELETE FROM Alunos WHERE (nome = '%s') AND (endereco = '%s') AND (bairro = '%s');",
				aln.getNome(), aln.getEndereco(), aln.getBairro());
		execute(sql);
	}
}
