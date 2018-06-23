package too.sqlconnect.methods;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * Esta é a principal classe responsável pela conexão com o banco de dados,
 * mas depende da classe {@code _CREDENTIALS} para obter parâmetros de conexão.
 */
public class _CONNECTION {
	
	/**
	 * A {@code String} com o nome do driver de conexão
	 */
	private final String serverDriverClass = "com.mysql.cj.jdbc.Driver";
	
	/**
	 * As credenciais usadas na conexão.
	 */
	private _CREDENTIALS credentials = null;
	/**
	 * Credenciais genéricas para a conexão com o banco MySQL.
	 * Exige um banco com o nome <code>dtb_sql</code> para funcionar.
	 */
	final   static _CREDENTIALS genericCredentials = new _CREDENTIALS("127.0.0.1", 3306, "root", null, "dtb_sql");

	/**
	 * A instância de {@code Connection} para segurar o estado da conexão
	 */
	protected Connection _conn = null;
	protected PreparedStatement query = null;
	
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
	 * /**
	 * Faz uma conexão com o servidor.
	 * @param stayConnected define se a conexão deve permanecer ativa até segunda ordem
	 * @return o estado da conexão ativa
	 */
	public Connection connect(boolean stayConnected) {
		try {
			//inicia uma instância do driver de conexão
			Class.forName(this.serverDriverClass).newInstance();
			
		} catch(ClassNotFoundException exp) { //caso o driver não exista (problema que tive nessas últimas duas semanas
			exp.printStackTrace();
			System.err.println("The class " +this.serverDriverClass +" was not found. Method failed.");
			System.err.println(exp.getMessage());
			return null;
			
		} catch(InstantiationException exp) { //caso haja um problema com a instanciação da classe do Driver
			exp.printStackTrace();
			System.err.println("Failed to instantiate " +this.serverDriverClass +". Method failed.");
			System.err.println(exp.getMessage());
			return null;
			
		} catch(IllegalAccessException exp) { //caso o driver esteja bloqueado por permissões administrativas
			exp.printStackTrace();
			System.err.println("Failed to instantiate " +this.serverDriverClass +". Denied access, method failed.");
			System.err.println(exp.getMessage());
			return null;
			
		}

		//tenta adquirir uma conexão com o servidor e returna o estado da conexão para '_conn'
		try {
			this._conn = DriverManager.getConnection(this.credentials.getConnURL(), this.credentials.getUsername(), this.credentials.getPassword());
			System.out.println("Conexão adquirida com MySQL em " +this.credentials.getConnURL() +'\n');
			
			//USE {DATABASE} in the connection
			this._conn.prepareStatement("use " +this.credentials.getDatabase()).execute();
			
			//retorna a conexão definida deste Objeto
			return this._conn;
			
		} catch(SQLException exp) { //caso haja um problema com a conexão
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
	 * Método {@code #main(String[])} usado para executar o programa
	 * @param args - Este programa não oferece nenhuma entrada para o usuário.
	 * @throws SQLException when an exception related to the SQL server occours
	 */
	public static void main(String[] args) {
		//cria uma nova instância de conexão
		_CONNECTION  cn = new _CONNECTION(genericCredentials);
		//conecta com o servidor
		cn.connect(true);
		try {
			//desativa o commit automático
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
