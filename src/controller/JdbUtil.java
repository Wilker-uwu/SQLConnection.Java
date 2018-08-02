package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbUtil {
	private static final String serverDriverClass = "com.mysql.cj.jdbc.Driver";
	
	private static Connection _conn = null;
	final   static _credentials credentials = new _credentials("127.0.0.1", -1, "root", null, "alunos");
	
	public static Connection getConnection() throws SQLException, InstantiationException,
	IllegalAccessException, ClassNotFoundException {
		if(_conn != null) {
			//skip
		} else {
			Class.forName(serverDriverClass).newInstance();
			_conn = DriverManager.getConnection(
					credentials.toString(),
					credentials.getUsername(),
					credentials.getPassword());
			debug("succesfully connected to " +credentials.toString());
		}
		return _conn;
	}
	
	public static Connection close() throws SQLException {
		if(_conn != null) {
			_conn.close();}
		return _conn;
	}
	
	private static void debug(String message) {
		System.out.println("JdbUtil.debug:\t" +message);
	}
}
