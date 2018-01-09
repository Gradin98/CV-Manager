package application.language;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONObject;

/**
 * 
 * Clasa care creeaza fisierul default de language
 * 
 * @author Kocsis Lorand
 *
 */
public class CreateDefaultLanguage {

	/**
	 * Functie care scrie fisierul default de language
	 * <p>
	 * **Necesita libraria JSON Simple
	 * </p>
	 * 
	 * 
	 * <ol>
	 * Contine functiile:
	 * <li>{@link #createLanguage()}</li>
	 * </ol>
	 */
	@SuppressWarnings("unchecked")
	public void createLanguage() {

		JSONObject language = new JSONObject();

		language.put("home-chose-file-text", "Choose your file");
		language.put("home-chose-file-button", "Choose File");
		language.put("home-export-text", "Export type");
		language.put("home-export-mysql-button", "Export Mysql");
		language.put("home-export-JSON-button", "Export JSON");
		language.put("home-export-XML-button", "Export XML");

		language.put("search-search-message", "Search for someting");
		language.put("search-search-button", "Search");
		language.put("search-regex-mode", "Regex Mode");
		language.put("search-date-mode", "Date Mode");

		new File(System.getenv("APPDATA") + "\\CV-Reader\\languages").mkdirs();

		try (FileWriter file1 = new FileWriter(System.getenv("APPDATA") + "\\CV-Reader\\languages\\en_default.json")) {

			file1.write(language.toJSONString());
			file1.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
