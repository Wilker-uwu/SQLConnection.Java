package too.sqlconnect.methods;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class _CONNECTION {
	
	private final String serverDriverClass = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	
	/**
	 * The credentials to be used in the connection;
	 */
	private _CREDENTIALS credentials = null;

	/**
	 * The <code>Connection</code> instance to hold the connection state.
	 */
	protected Connection _conn = null;
	
	public _CONNECTION() {
		
	}
	
	public _CONNECTION(_CREDENTIALS credentials) {
		this.credentials = credentials;
	}
	
	public Connection connect() {
		try {
			Class.forName(this.serverDriverClass);
			this._conn =
					DriverManager.getConnection(
							credentials.getConnURL(),
							credentials.getUsername(),
							credentials.getPassword()
							);
			return this._conn;
		} catch (ClassNotFoundException exp) {
			exp.printStackTrace();
			System.err.println("The class " +this.serverDriverClass +" was not found. Method failed.");
			return null;
		} catch (SQLException exp) {
			exp.printStackTrace();
			System.err.println("Connection to " +credentials.getConnURL() +" failed!");
			return null;
		}
	}
	
	public _CREDENTIALS getCredentials()                         { return this.credentials;        }
	public void         setCredentials(_CREDENTIALS credentials) { this.credentials = credentials; }
	
	public static void main(String[] args) {
		_CREDENTIALS cr = new _CREDENTIALS("localhost", 1433, "sa", "info211", "dataDB");
		_CONNECTION  cn = new _CONNECTION(cr);
		
		cn.connect();
	}

}
