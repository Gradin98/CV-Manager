package application.fileconverters;

/**
 * Interfata pentru setarea functiilor de parsare de text
 * 
 * @author Kocsis Lorand
 *
 */
public interface ParserINTF {

	/**
	 * Seteaza contentu textului extras din file in variabila "content"
	 * 
	 * @param sText Path-ul fisierului
	 */
	public void setContent(final String sText);

	/**
	 * Functie care returneaza variabila "content"
	 * @return
	 */
	public String getContent();
}
