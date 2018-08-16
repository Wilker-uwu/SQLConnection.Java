package com.wilkers1.sqlconnection.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class JdbUtil {
	private static final String serverDriverClass = "com.mysql.cj.jdbc.Driver";
	
	private static Connection _conn = null;
	final   static _credentials credentials = new _credentials("127.0.0.1", -1, "root", null, "dtbsql");
	
	protected static Connection getConnection() throws SQLException, NullPointerException {
		if(_conn != null) {
			//skip
		} else {
			try {
				Class.forName(serverDriverClass).newInstance();
				_conn = DriverManager.getConnection(
						credentials.toString(),
						credentials.getUsername(),
						credentials.getPassword());
				debug("succesfully connected to " +credentials.toString());
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException exp) {
				throw new NullPointerException("An internal error occurred.");
			} catch (SQLException exp) {
				throw new SQLException("A connection or internal server error occurred.");
			}
		}
		return _conn;
	}
	
	protected static Connection close() throws SQLException {
		if(_conn != null) {
			_conn.close();}
		return _conn;
	}
	
	private static void debug(String message) {
		System.out.println("JdbUtil.debug:\t" +message);
	}
}
