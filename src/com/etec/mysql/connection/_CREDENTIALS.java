package com.etec.mysql.connection;

/**
 * Esta vlasse segura as credenciais para a conexão com o banco MySQL.
 * @author Wilker Santana da Silva
 */
public class _CREDENTIALS {
	
	/**
	 * IP address or ho
	 */
	private String hostname = null;
	private int    port     = -1;
	private String database = null;
	private String username = null;
	private String password = null;
	
	/**
	 * The URL to be used with the SQL Server connection.<br/>
	 * <h6>Definition on instance creation:</h6>
	 * <pre>"jdbc:sqlserver//" +hostname +':' +port +'/' +database;</pre>
	 */
	private String connURL  = null;
	
	/**
	 * Builds the credentials for connecting to the server database.
	 * @param hostname	is the address of the host. Can be an IP or a hostname.
	 * @param port		is the port to which it will connect to.
	 * 					use <code>-1</code> if there is no port needed.
	 * 					otherwise, specify the port.
	 * @param username	is the username to use as the credential.
	 * 					use <code>null</code> if there is no username needed.
	 * @param password	is the password to use with this username.
	 * 					use <code>null</code> if there is no password needed.
	 * @param database	is the database to be accessed.
	 */
	public _CREDENTIALS(String hostname, int port, String username, String password, String database) {
		this.hostname = hostname;
		this.port     = port;
		this.database = database;
		this.username = username;
		this.password = password;
		
		this.connURL = "jdbc:mysql://" +this.hostname; //server adddress
		this.connURL += ":" +this.port; //connection port
		this.connURL += '/' +this.database; //database
		this.connURL += "?useUnicode=true"; //unicode
		this.connURL += "&useSSL=true"; //certificate
		this.connURL += "&useJDBCCompliantTimezonShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC"; //timezone
	}
	
	/**
	 * Gathers the connection address for usage.
	 * @return the URL for connecting to the SQL Server database.
	 */
	public String getConnURL() {
		return this.connURL;
	}
	
	public String getHostname()              { return this.hostname;     }
	public void setHostname(String hostname) { this.hostname = hostname; }

	public int getPort()          { return this.port; }
	public void setPort(int port) { this.port = port; }
	
	public String getDatabase()              { return this.database;     }
	public void setDatabase(String database) { this.database = database; }
	
	public String getUsername()              { return this.username;     }
	public void setUsername(String username) { this.username = username; }

	public String getPassword()              { return this.password;     }
	public void setPassword(String password) { this.password = password; }

}