package application.fileconverters;

/**
 * Clasa care stabileste polimorfismul interfetei {@link ParserINTF}
 * 
 * @author Kocsis Lorand
 *
 */

public class ParserFactory {

	private final ParserINTF[] arrParsers;

	/**
	 * Constructorul clasei care initializeaza variabila {@link #arrParsers} cu 3
	 * obiecte de tip {@link ParserINTF}
	 */
	public ParserFactory() {
		arrParsers = new ParserINTF[3];
	}

	/**
	 * Functie care returneaza obiectul de tipul clasei in functie de fisier
	 * 
	 * @param sFileName
	 *            Path-ul fisierului
	 * @return Un obiect de tipul clasei
	 * 
	 */
	public ParserINTF GetParser(final String sFileName) {
		final String sFileNameLower = sFileName.toLowerCase();
		final ParserINTF parser;
		if (sFileNameLower.endsWith(".pdf")) {
			if (arrParsers[0] == null) {
				arrParsers[0] = new PDFConverter();
			}
			parser = arrParsers[0];
		} else if (sFileNameLower.endsWith(".doc")) {
			if (arrParsers[1] == null) {
				arrParsers[1] = new WordConverter();
			}
			parser = arrParsers[1];
		} else if (sFileNameLower.endsWith(".docx")) {
			if (arrParsers[2] == null) {
				arrParsers[2] = new DocxConverter();
			}
			parser = arrParsers[2];
		} else {
			parser = null;
		}
		//
		return parser;
	}
}
