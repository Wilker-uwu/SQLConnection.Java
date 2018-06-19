package too.sqlconnect.methods;

import java.sql.Connection; //interface para obter o estado da conexão
import java.sql.DriverManager; //classe para gerenciar a forma como a conexão é feita com o servidor
import java.sql.SQLException; //Exceção para as conexões e ações com o banco de dados

/**
 * Esta é a principal classe responsável pela conexão com o banco de dados,
 * mas depende da classe {@code _CREDENTIALS} para obter parâmetros de conexão.
 */
public class _CONNECTION {
	
	/**
	 * A {@code String} com o nome do driver de conexão
	 */
	private final String serverDriverClass = "com.mysql.jdbc.Driver";
	
	/**
	 * As credenciais usadas na conexão.
	 */
	private        _CREDENTIALS credentials        = null;
	/**
	 * Credenciais genéricas para a conexão com o banco MySQL.
	 * Exige um banco com o nome <code>dtb_sql</code> para funcionar.
	 */
	final   static _CREDENTIALS genericCredentials = new _CREDENTIALS("localhost", -1, "admin", "", "dtb_sql");

	/**
	 * A instância de {@code Connection} para segurar o estado da conexão
	 */
	protected Connection _conn = null;
	
	/**
	 * Construtor com credenciais genéricas de conexão
	 */
	public _CONNECTION() {
		this.credentials = genericCredentials;
	}
	
	/**
	 * Construtor com credenciais de conexão definidas pelo usuário
	 * @param credentials é a instância de {@code _CREDENTIALS} com as credenciais
	 */
	public _CONNECTION(_CREDENTIALS credentials) {
		this.credentials = credentials;
	}
	
	/**
	 * Faz uma conexão com o servidor.
	 * @return o estado da conexão ativa
	 */
	public Connection connect() {
		try {
			//inicia uma instância do driver de conexão
			Class.forName(this.serverDriverClass).newInstance();
			
			//adquirire uma conexão com o servidor e returna o estado da conexão para '_conn'
			this._conn = DriverManager.getConnection(this.credentials.getConnURL());
			System.out.println("Conexão adquirida com MySQL em " +this.credentials.getConnURL());
			//retorna a conexão definida deste Objeto
			return this._conn;
			
		} catch (ClassNotFoundException exp) { //caso o driver não exista (problema que tive nessas últimas duas semanas
			exp.printStackTrace();
			System.err.println("The class " +this.serverDriverClass +" was not found. Method failed.");
			System.err.println(exp.getMessage());
			return null;
			
		} catch (SQLException exp) { //caso haja um problema com a conexão
			exp.printStackTrace();
			System.err.println("Connection to " +this.credentials.getConnURL() +" failed!");
			System.err.println(exp.getMessage());
			return null;
			
		} catch (InstantiationException exp) { //caso haja um problema com a instanciação da classe do Driver
			exp.printStackTrace();
			System.err.println("Failed to instantiate " +this.serverDriverClass +". Method failed.");
			System.err.println(exp.getMessage());
			return null;
			
		} catch (IllegalAccessException exp) { //caso o driver esteja bloqueado por permissões administrativas
			exp.printStackTrace();
			System.err.println("Failed to instantiate " +this.serverDriverClass +". Denied access, method failed.");
			System.err.println(exp.getMessage());
			return null;
			
		}
	}
	
	public _CREDENTIALS getCredentials()                         { return this.credentials;        }
	public void         setCredentials(_CREDENTIALS credentials) { this.credentials = credentials; }
	
	/**
	 * Método {@code #main(String[])} usado para executar o programa
	 * @param args - Este programa não oferece nenhuma entrada para o usuário.
	 */
	public static void main(String[] args) {
		//cria uma nova instância de conexão
		_CONNECTION  cn = new _CONNECTION(genericCredentials);
		//conecta com o servidor
		cn.connect();
	}

}
