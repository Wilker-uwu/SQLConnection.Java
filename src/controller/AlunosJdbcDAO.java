package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Alunos;

public class AlunosJdbcDAO {
	private static Connection _conn = null;
	
	public AlunosJdbcDAO(final Connection conn) {
		_conn = conn;
	}
	
	private static void execute(String query) throws SQLException {
		PreparedStatement pStatement= _conn.prepareStatement(query);
		pStatement.executeQuery();
		pStatement.close();
	}
	
	public static void insert(Alunos aln) throws SQLException {
		String sql = String.format(
				"INSERT INTO Alunos VALUES ('%s', '%s', '%s');",
				aln.getNome(), aln.getEndereco(), aln.getBairro());
		execute(sql);
	}
	
	public static void delete(Alunos aln) throws SQLException {
		String sql = String.format(
				"DELETE FROM Alunos AS al WHERE (al.nome = '%s') AND (al.endereco = '%s') AND (bairro = '%s');",
				aln.getNome(), aln.getEndereco(), aln.getBairro());
		execute(sql);
	}
}
