package application.language;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * 
 * Clasa care primeste fisierul language din config
 * 
 * <ol>
 * Contine functiile:
 * <li>{@link #getLanguage(String)}</li>
 * <li>{@link #getFileLanguage()}</li>
 * </ol>
 * 
 * @author Kocsis Lorand
 *
 */
public class GetLanguage {

	/**
	 * Functie care returneaza randuri din fisieru de language
	 * 
	 * @param row
	 *            Path-ul randului din fisier
	 * @return Stringul cu randul din fisier
	 */
	public String getLanguage(String row) {
		File f = new File(System.getenv("APPDATA") + "\\CV-Reader\\languages\\" + getFileLanguage() + ".json");

		if (f.exists()) {
			JSONParser parser = new JSONParser();
			try {

				Object obj = parser.parse(new FileReader(
						System.getenv("APPDATA") + "\\CV-Reader\\languages\\" + getFileLanguage() + ".json"));

				JSONObject jsonObject = (JSONObject) obj;

				if (jsonObject.containsKey(row))
					return (String) jsonObject.get(row);

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		return null;

	}

	/**
	 * Functie care returneaza numele fisierului setat din setarile aplicatiei
	 * 
	 * @return Numele fisierului din config.
	 * 
	 * 
	 */
	private String getFileLanguage() {

		File f = new File(System.getenv("APPDATA") + "\\CV-Reader\\config.json");

		if (f.exists()) {
			JSONParser parser = new JSONParser();
			try {

				Object obj = parser.parse(new FileReader(System.getenv("APPDATA") + "\\CV-Reader\\config.json"));

				JSONObject jsonObject = (JSONObject) obj;

				if (jsonObject.containsKey("Language"))
					return (String) jsonObject.get("Language");

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}

		}

		return null;

	}

}
