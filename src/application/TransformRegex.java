package application;

/**
 * Clasa care permite folosirea searchului fara cunostinte Regex
 * 
 * <ol>
 * Contine functiile:
 * <li>{@link #transform(String)}</li>
 * </ol>
 * @author Kocsis Lorand
 *
 */
public class TransformRegex {

	/**
	 * Functie care transforma caracterele speciale Regex astfel incat
	 * search-ul aplicatiei sa nu necesite caractere speciale
	 * 
	 * @param regex String-ul initial Regex
	 * @return Stringul transformat
	 */
	public String transform(String regex) {
		String transformat;
		transformat = regex.replaceAll("[+]", "[+]");
		transformat = transformat.replaceAll("[*]", "[*]");
		transformat = transformat.replaceAll("[#]", "[#]");
		transformat = transformat.replaceAll("[?]", "[?]");
		transformat = transformat.replaceAll("[/]", "[/]");
		transformat = transformat.replaceAll("[$]", "[$]");
		transformat = transformat.replaceAll("[{]", "[{]");
		transformat = transformat.replaceAll("[}]", "[}]");
		transformat = transformat.replaceAll("[:]", "[:]");
		return transformat;
	}
}
