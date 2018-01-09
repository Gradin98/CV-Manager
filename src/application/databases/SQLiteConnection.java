package application.databases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Clasa care permite conectarea cu Baze de date de tip SQLite
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

public class SQLiteConnection implements Databases {

	Connection c = null;
	Statement stmt = null;

	/**
	 * Functie care se conecteaza la baza de date sqlite locala
	 */
	public void connect() {
		try {
			c = DriverManager.getConnection("jdbc:sqlite:" + System.getenv("APPDATA") + "\\CV-Reader\\cvdatabase.db");
			stmt = c.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	/**
	 * 
	 */
	public void createDatabase() {
		connect();
		try {
			Class.forName("org.sqlite.JDBC");

			String sql = "CREATE TABLE IF NOT EXISTS `CvData` (\r\n"
					+ "	`id`	INTEGER PRIMARY KEY AUTOINCREMENT,\r\n" + "	`content`	TEXT NOT NULL,\r\n"
					+ "	`Filename`	TEXT NOT NULL,\r\n" + "	`Date`	DATETIME DEFAULT CURRENT_TIMESTAMP\r\n" + ")";
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}

	}

	@Override
	/**
	 * 
	 */
	public void setContent(String content, String name) {
		connect();
		try {

			String sql = "INSERT INTO CvData (content,filename)" + " VALUES('" + content + "','" + name + "')";
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	/**
	 * 
	 */
	public TreeMap<String, String> contentList(String regex) {
		connect();
		try {
			ResultSet result = stmt.executeQuery("SELECT * FROM CvData;");

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
			stmt.close();
			return returndata;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
			ResultSet result = stmt.executeQuery("SELECT * FROM CvData WHERE DATE BETWEEN '" + dateprev
					+ " 00:00:01' AND '" + datenexts + " 23:59:59'");

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
			stmt.close();
			return returndata;
		} catch (SQLException e) {
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
		try {
			c = DriverManager.getConnection("jdbc:sqlite:" + System.getenv("APPDATA") + "\\CV-Reader\\cvdatabase.db");
			stmt = c.createStatement();
		} catch (SQLException e) {
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
		return "Sqlite";
	}

}
