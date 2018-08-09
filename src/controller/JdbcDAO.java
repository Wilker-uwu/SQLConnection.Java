package controller;

import java.lang.SecurityException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import model.Pessoa;

abstract class JdbcDAO {
	private static Connection _conn = null;
	private static SecurityException idExp = new SecurityException("The constructor with ID is not intended for data registrations.");
	private static SecurityException noIdExp = new SecurityException("Cannot delete registry with an unespecified ID. Risk of unintended deletion.");
	
	protected static void execute(String query) throws SQLException, NullPointerException {
		if(_conn == null) {
			_conn = JdbUtil.getConnection();
		}
		try {
			Statement statement = _conn.createStatement();
			statement.execute(query);
			statement.close();
		} catch (NullPointerException exp) {
			throw new NullPointerException("Could not execute this action.");
		}
		JdbUtil.close();
	}
	
	public abstract void insert(Pessoa ppl) throws SQLException;
	public abstract void delete(Pessoa aln) throws SQLException;
}
