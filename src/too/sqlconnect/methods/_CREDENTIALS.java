package too.sqlconnect.methods;

/**
 * Esta vlasse segura as credenciais para a conexão com o banco MySQL
 */
public class _CREDENTIALS {
	
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
	 * @param hostname is the address of the host. Can be an IP or a hostname.
	 * @param port is the port to which it will connect to. <b>(default is <code>1433</code>)<b>
	 * @param username is the username to use as the credential.
	 * @param password is the password to use with this username.
	 * @param database is the database to be connected.
	 */
	public _CREDENTIALS(String hostname, int port, String username, String password, String database) {
		//gathers the credentials
		this.hostname = hostname;
		this.port = port;
		this.database = database;
		this.username = username;
		this.password = password;
		
		//mounts the host
		this.connURL = "jdbc:mysql//" +this.hostname;
		//mounts the port
		if(this.port == -1) { //if there is a specified port
			this.connURL += ':' +this.port; //implements is
		}
		
		this.connURL += '/' +this.database +"&user=" +this.username +"&password=" +this.password;
		
	}
	
	/**
	 * Gathers the connection address for usage.
	 * @return the URL for connecting to the SQL Server database.
	 */
	public String getConnURL() {
		return this.connURL;
	}
	
	public String getUsername()              { return this.username;     }
	public void setUsername(String username) { this.username = username; }

	public String getPassword()              { return this.password;     }
	public void setPassword(String password) { this.password = password; }
}