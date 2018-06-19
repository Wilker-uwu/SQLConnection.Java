package too.sqlconnect.methods;

import java.sql.Connection; //interface para obter o estado da conex�o
import java.sql.DriverManager; //classe para gerenciar a forma como a conex�o � feita com o servidor
import java.sql.SQLException; //Exce��o para as conex�es e a��es com o banco de dados

/**
 * Esta � a principal classe respons�vel pela conex�o com o banco de dados,
 * mas depende da classe {@code _CREDENTIALS} para obter par�metros de conex�o.
 */
public class _CONNECTION {
	
	/**
	 * A {@code String} com o nome do driver de conex�o
	 */
	private final String serverDriverClass = "com.mysql.jdbc.Driver";
	
	/**
	 * As credenciais usadas na conex�o.
	 */
	private        _CREDENTIALS credentials        = null;
	/**
	 * Credenciais gen�ricas para a conex�o com o banco MySQL.
	 * Exige um banco com o nome <code>dtb_sql</code> para funcionar.
	 */
	final   static _CREDENTIALS genericCredentials = new _CREDENTIALS("localhost", -1, "admin", "", "dtb_sql");

	/**
	 * A inst�ncia de {@code Connection} para segurar o estado da conex�o
	 */
	protected Connection _conn = null;
	
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
	 * Faz uma conex�o com o servidor.
	 * @return o estado da conex�o ativa
	 */
	public Connection connect() {
		try {
			//inicia uma inst�ncia do driver de conex�o
			Class.forName(this.serverDriverClass).newInstance();
			
			//adquirire uma conex�o com o servidor e returna o estado da conex�o para '_conn'
			this._conn = DriverManager.getConnection(this.credentials.getConnURL());
			System.out.println("Conex�o adquirida com MySQL em " +this.credentials.getConnURL());
			//retorna a conex�o definida deste Objeto
			return this._conn;
			
		} catch (ClassNotFoundException exp) { //caso o driver n�o exista (problema que tive nessas �ltimas duas semanas
			exp.printStackTrace();
			System.err.println("The class " +this.serverDriverClass +" was not found. Method failed.");
			System.err.println(exp.getMessage());
			return null;
			
		} catch (SQLException exp) { //caso haja um problema com a conex�o
			exp.printStackTrace();
			System.err.println("Connection to " +this.credentials.getConnURL() +" failed!");
			System.err.println(exp.getMessage());
			return null;
			
		} catch (InstantiationException exp) { //caso haja um problema com a instancia��o da classe do Driver
			exp.printStackTrace();
			System.err.println("Failed to instantiate " +this.serverDriverClass +". Method failed.");
			System.err.println(exp.getMessage());
			return null;
			
		} catch (IllegalAccessException exp) { //caso o driver esteja bloqueado por permiss�es administrativas
			exp.printStackTrace();
			System.err.println("Failed to instantiate " +this.serverDriverClass +". Denied access, method failed.");
			System.err.println(exp.getMessage());
			return null;
			
		}
	}
	
	public _CREDENTIALS getCredentials()                         { return this.credentials;        }
	public void         setCredentials(_CREDENTIALS credentials) { this.credentials = credentials; }
	
	/**
	 * M�todo {@code #main(String[])} usado para executar o programa
	 * @param args - Este programa n�o oferece nenhuma entrada para o usu�rio.
	 */
	public static void main(String[] args) {
		//cria uma nova inst�ncia de conex�o
		_CONNECTION  cn = new _CONNECTION(genericCredentials);
		//conecta com o servidor
		cn.connect();
	}

}
