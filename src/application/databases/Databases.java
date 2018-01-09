package application.databases;

import java.util.TreeMap;

/**
 * Interfata pentru implementarea functiilor in diferite tipuri de Baze de Date
 * 
 * @author Kocsis Lorand
 * 
 * @see
 *      <p>
 * 		{@link MysqlConnection}
 *      </p>
 *      {@link SQLiteConnection}
 *
 */
public interface Databases {

	/**
	 * Functie care verifica daca serverul de baze de date este deschis
	 * 
	 * @return true in caz ca se conecteaza la baza de date, false in caz contrar
	 */
	public Boolean checkConnection();

	/**
	 * Functie care creeaza baza de date in caz ca nu exista
	 */
	public void createDatabase();

	/**
	 * Functie care setaeza contentul bazei de date
	 * 
	 * @param content
	 * @param name
	 */
	public void setContent(String content, String name);

	/**
	 * Functie care ia datele din baza de date verificand si existenta datelor cu
	 * regex
	 * 
	 * @param regex Stringul regex care verifica existenta datelor
	 * @return TreeMap cu datele gasite prin regex. 
	 */
	public TreeMap<String, String> contentList(String regex);

	/**
	 * Functie care ia datele din baza de date verificand si existenta datelor cu
	 * regex intre 2 date
	 * @param regex Stringul regex care verifica existenta datelor
	 * @param dateprev Data initiala
	 * @param datenexts Data finala
	 * @return TreeMap cu datele gasite prin regex. 
	 */
	public TreeMap<String, String> contentByDate(String regex, String dateprev, String datenexts);

	/**
	 * Functie care returneaza numele clasei
	 * 
	 * @return String cu numele clasei
	 */
	public String numeClasa();
}
