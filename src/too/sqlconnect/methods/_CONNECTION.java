package too.sqlconnect.methods;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * Esta � a principal classe respons�vel pela conex�o com o banco de dados,
 * mas depende da classe {@code _CREDENTIALS} para obter par�metros de conex�o.
 */
public class _CONNECTION {
	
	/**
	 * A {@code String} com o nome do driver de conex�o
	 */
	private final String serverDriverClass = "com.mysql.cj.jdbc.Driver";
	
	/**
	 * As credenciais usadas na conex�o.
	 */
	private _CREDENTIALS credentials = null;
	/**
	 * Credenciais gen�ricas para a conex�o com o banco MySQL.
	 * Exige um banco com o nome <code>dtb_sql</code> para funcionar.
	 */
	final   static _CREDENTIALS genericCredentials = new _CREDENTIALS("127.0.0.1", 3306, "root", null, "dtb_sql");

	/**
	 * A inst�ncia de {@code Connection} para segurar o estado da conex�o
	 */
	protected Connection _conn = null;
	protected PreparedStatement query = null;
	
	/**
	 * Construtor com credenciais gen�ricas de conex�o
	 */
	public _CONNECTION() {
		this.credentials = genericCredentials;
	}
	
	/**
	 * Construtor com credenciais de conex�o definidas pelo usu�rio
	 * @param credentials � a inst�ncia de {@code _CREDENTIALS} com as credenciais
	 */
	public _CONNECTION(_CREDENTIALS credentials) {
		this.credentials = credentials;
	}
	
	/**
	 * /**
	 * Faz uma conex�o com o servidor.
	 * @param stayConnected define se a conex�o deve permanecer ativa at� segunda ordem
	 * @return o estado da conex�o ativa
	 */
	public Connection connect(boolean stayConnected) {
		try {
			//inicia uma inst�ncia do driver de conex�o
			Class.forName(this.serverDriverClass).newInstance();
			
		} catch(ClassNotFoundException exp) { //caso o driver n�o exista (problema que tive nessas �ltimas duas semanas
			exp.printStackTrace();
			System.err.println("The class " +this.serverDriverClass +" was not found. Method failed.");
			System.err.println(exp.getMessage());
			return null;
			
		} catch(InstantiationException exp) { //caso haja um problema com a instancia��o da classe do Driver
			exp.printStackTrace();
			System.err.println("Failed to instantiate " +this.serverDriverClass +". Method failed.");
			System.err.println(exp.getMessage());
			return null;
			
		} catch(IllegalAccessException exp) { //caso o driver esteja bloqueado por permiss�es administrativas
			exp.printStackTrace();
			System.err.println("Failed to instantiate " +this.serverDriverClass +". Denied access, method failed.");
			System.err.println(exp.getMessage());
			return null;
			
		}

		//tenta adquirir uma conex�o com o servidor e returna o estado da conex�o para '_conn'
		try {
			this._conn = DriverManager.getConnection(this.credentials.getConnURL(), this.credentials.getUsername(), this.credentials.getPassword());
			System.out.println("Conex�o adquirida com MySQL em " +this.credentials.getConnURL() +'\n');
			
			//USE {DATABASE} in the connection
			this._conn.prepareStatement("use " +this.credentials.getDatabase()).execute();
			
			//retorna a conex�o definida deste Objeto
			return this._conn;
			
		} catch(SQLException exp) { //caso haja um problema com a conex�o
			exp.printStackTrace();
			System.err.println("Connection to " +this.credentials.getConnURL() +" failed!");
			System.err.println(exp.getMessage());
			return null;
			
		} finally {
			try {
				if (!stayConnected)
					if(this._conn != null)
							this._conn.close();
			} catch(Exception exp) {
				exp.printStackTrace();
			}
		}
	}
	
	public Connection disconnect() {
		try {
			this._conn.close();
			
		} catch(NullPointerException exp) {
			//ignore
		} catch(Exception exp) {
			exp.printStackTrace();
			
		}
		return this._conn;
	}
	
	public _CREDENTIALS getCredentials()                         { return this.credentials;        }
	public void         setCredentials(_CREDENTIALS credentials) { this.credentials = credentials; }
	
	/**
	 * M�todo {@code #main(String[])} usado para executar o programa
	 * @param args - Este programa n�o oferece nenhuma entrada para o usu�rio.
	 * @throws SQLException when an exception related to the SQL server occours
	 */
	public static void main(String[] args) {
		//cria uma nova inst�ncia de conex�o
		_CONNECTION  cn = new _CONNECTION(genericCredentials);
		//conecta com o servidor
		cn.connect(true);
		try {
			//desativa o commit autom�tico
			cn._conn.setAutoCommit(false);
			
			//escreve um comando SQL a ser executado
			String get = "SELECT * FROM tb_items WHERE item_name LIKE '%dummy%';";
			//prepara esse comando para ser executado
			PreparedStatement getter = cn._conn.prepareStatement(get);
			
			
			
			//imprime na tela este comando
			System.out.println(get);
			
			ResultSet output = getter.executeQuery();
			ResultSetMetaData outmeta = output.getMetaData();
			if(output.next()) {
				for(int c=0; c<outmeta.getColumnCount(); c++) {
					System.out.println(outmeta.getColumnLabel(c+1) +":\t" +output.getObject(c+1));
				}
				output.close();
			}
			
		} catch (SQLException exp) {
			exp.printStackTrace();
			
		} finally {
			cn.disconnect();
		}
	}
}
