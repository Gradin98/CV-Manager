package application.databases;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Clasa care permite conectarea cu Baze de date de tip Mysql
 * 
 *<ol>
 * Contine functiile:
 * <li>{@link #checkConnection()}</li>
 * <li>{@link #connect()}</li>
 * <li>{@link #openConnection()}</li>
 * <li>{@link #createDatabase()}</li>
 * <li>{@link #setContent(String, String)}</li>
 * <li>{@link #contentList(String)}</li>
 * <li>{@link #contentByDate(String, String, String)}</li>
 * <li>{@link #numeClasa()}</li>
 * </ol>
 * 
 * @author Kocsis Lorand
 *
 */
public class MysqlConnection implements Databases {
	private String host, database, username, password, port;
	private Connection connection;

	private static MysqlConnection variabila = new MysqlConnection();

	public MysqlConnection() {
	}

	public static MysqlConnection getInstance() {
		return variabila;
	}


	/**
	 * Functie care seteaza datele de conexiune ale bazei de date extrase din config
	 */
	public void connect() {
		File f = new File(System.getenv("APPDATA") + "\\CV-Reader\\config.json");
		if (f.exists()) {
			JSONParser parser = new JSONParser();
			try {

				Object obj = parser.parse(new FileReader(System.getenv("APPDATA") + "\\CV-Reader\\config.json"));

				JSONObject jsonObject = (JSONObject) obj;

				database = (String) jsonObject.get("DB Name");
				password = (String) jsonObject.get("Parola");
				host = (String) jsonObject.get("IP");
				port = (String) jsonObject.get("Port");
				username = (String) jsonObject.get("Username");

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
	}


	/**
	 * Functie care deschide conexiunea Mysql
	 * 
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public void openConnection() throws SQLException, ClassNotFoundException {

		if (connection != null && !connection.isClosed()) {
			return;
		}

		synchronized (this) {
			if (connection != null && !connection.isClosed()) {
				return;
			}
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(
					"jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database, this.username, this.password);
		}
	}


	/**
	 * 
	 */
	public void createDatabase() {
		connect();
		try {
			openConnection();
			Statement statement = connection.createStatement();
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS `CvData` (\r\n"
					+ "	`id` INT NOT NULL AUTO_INCREMENT,\r\n" + "	`content` LONGTEXT NOT NULL,\r\n"
					+ "  `fileName` VARCHAR(255),\r\n" + "	PRIMARY KEY (`id`)\r\n" + ");");
			statement.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 */
	public void setContent(String content, String name) {
		connect();
		try {
			openConnection();
			Statement statement = connection.createStatement();
			String sql = "INSERT INTO CvData (`content`, `location`, `fileName`) VALUES ('" + content + "','" + name
					+ "')";
			statement.executeUpdate(sql);
			statement.close();

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 */
	public TreeMap<String, String> contentList(String regex) {
		connect();
		try {
			openConnection();

			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM CvData");
			TreeMap<String, String> returndata = new TreeMap<String, String>();

			Pattern r = Pattern.compile(regex);
			while (result.next()) {
				String content = result.getString("content");
				String name = result.getString("fileName");

				Matcher m = r.matcher(content);
				if (m.find()) {
					returndata.put(name, content);
				}

			}
			statement.close();
			return returndata;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		return null;

	}

	@Override
	/**
	 * 
	 */
	public TreeMap<String, String> contentByDate(String regex, String dateprev, String datenexts) {
		connect();
		try {
			openConnection();

			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM cvdata WHERE `data` BETWEEN '" + dateprev
					+ " 00:00:01' AND '" + datenexts + " 23:59:59'");
			TreeMap<String, String> returndata = new TreeMap<String, String>();

			while (result.next()) {
				String content = result.getString("content");
				String name = result.getString("fileName");
				Pattern r = Pattern.compile(regex);

				Matcher m = r.matcher(content);
				if (m.find()) {
					returndata.put(name, content);
				}
			}
			statement.close();
			return returndata;

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	/**
	 * 
	 */
	public Boolean checkConnection() {
		connect();
		try {
			openConnection();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			if (e.getMessage() != null) {
				return false;
			}
		}
		return true;
	}

	@Override
	/**
	 * 
	 */
	public String numeClasa() {
		// TODO Auto-generated method stub
		return "Mysql";
	}

}
