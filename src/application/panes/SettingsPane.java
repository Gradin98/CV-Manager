package application.panes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import application.Main;
import application.interfaceObj.AnimationEvents;
import application.interfaceObj.PurpleButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 * 
 * Clasa care seteaza continul pentru pagina de setari a aplicatiei.
 * 
 * <ol>
 * Contine functiile:
 * <li>{@link #setContent(Scene)}</li>
 * <li>{@link #editFileLanguageEvent(Scene, Pane, Button, ChoiceBox)}</li>
 * <li>{@link #newFileLanguageEvent(Scene, Pane, Button)}</li>
 * </ol>
 * 
 * @author Kocsis Lorand
 *
 */
public class SettingsPane {

	JSONObject config = new JSONObject();

	/**
	 * Functie care seteaza contentu pentru pagina de setari
	 * 
	 * @param scene
	 * @param box
	 */
	public void setContent(Scene scene) {
		Pane homePane = (Pane) scene.lookup("#HomePane");
		Pane imagePane = (Pane) scene.lookup("#SearchPane");
		Pane settingsPane = (Pane) scene.lookup("#SettingsPane");

		homePane.setStyle(homePane.getStyle() + "-fx-background-color: #212121;");
		imagePane.setStyle(imagePane.getStyle() + "-fx-background-color: #212121;");
		settingsPane.setStyle(homePane.getStyle() + "-fx-background-color: #6c0080;");

		Pane pane = (Pane) scene.lookup("#contentPane");
		pane.getChildren().clear();

		String styleInfoMesaj = "-fx-font: 16px \"Arial Bold\";" + "-fx-text-fill: #fff; "
				+ "-fx-background-color: transparent;" + "-fx-min-width: 200px;" + "-fx-max-width: 200px;";

		String styleContentMesaj = styleInfoMesaj + "-fx-border-style: none none solid none;"
				+ "-fx-border-color: transparent transparent white transparent;";

		TextField infoMesaj = new TextField("Settings for Mysql Connection");
		infoMesaj.setEditable(false);
		infoMesaj.setStyle(styleInfoMesaj + "-fx-min-width: 300px; -fx-max-width: 300px;");
		Main.setTextLocation(infoMesaj, 20, 50);
		pane.getChildren().add(infoMesaj);

		TextField mesajIP = new TextField("IP:");
		mesajIP.setStyle(styleInfoMesaj);
		mesajIP.setEditable(false);
		Main.setTextLocation(mesajIP, 20, 80);

		TextField mesajUsername = new TextField("Username:");
		mesajUsername.setStyle(styleInfoMesaj);
		mesajUsername.setEditable(false);
		Main.setTextLocation(mesajUsername, 20, 120);

		TextField mesajParola = new TextField("Parola:");
		mesajParola.setStyle(styleInfoMesaj);
		mesajParola.setEditable(false);
		Main.setTextLocation(mesajParola, 20, 160);

		TextField mesajPort = new TextField("Port:");
		mesajPort.setStyle(styleInfoMesaj);
		mesajPort.setEditable(false);
		Main.setTextLocation(mesajPort, 20, 200);

		TextField mesajNameDB = new TextField("Name DB:");
		mesajNameDB.setStyle(styleInfoMesaj);
		mesajNameDB.setEditable(false);
		Main.setTextLocation(mesajNameDB, 20, 240);

		pane.getChildren().add(mesajNameDB);
		pane.getChildren().add(mesajPort);
		pane.getChildren().add(mesajParola);
		pane.getChildren().add(mesajUsername);
		pane.getChildren().add(mesajIP);

		TextField fieldIP = new TextField("");
		fieldIP.setStyle(styleContentMesaj);
		Main.setTextLocation(fieldIP, 150, 80);
		fieldIP.setId("#fieldIP");

		TextField fieldUsername = new TextField("");
		fieldUsername.setStyle(styleContentMesaj);
		Main.setTextLocation(fieldUsername, 150, 120);
		fieldUsername.setId("#fieldUsername");

		PasswordField fieldParola = new PasswordField();
		fieldParola.setStyle(styleContentMesaj);
		Main.setTextLocation(fieldParola, 150, 160);
		fieldParola.setId("#fieldParola");

		TextField fieldPort = new TextField("");
		fieldPort.setStyle(styleContentMesaj);
		Main.setTextLocation(fieldPort, 150, 200);
		fieldPort.setId("#fieldPort");

		TextField fieldDbName = new TextField("");
		fieldDbName.setStyle(styleContentMesaj);
		Main.setTextLocation(fieldDbName, 150, 240);
		fieldDbName.setId("#fieldDbName");

		pane.getChildren().add(fieldIP);
		pane.getChildren().add(fieldUsername);
		pane.getChildren().add(fieldParola);
		pane.getChildren().add(fieldPort);
		pane.getChildren().add(fieldDbName);

		TextField languageInfo = new TextField("Language:");
		languageInfo.setStyle(styleInfoMesaj);
		languageInfo.setEditable(false);
		Main.setTextLocation(languageInfo, 370, 50);
		pane.getChildren().add(languageInfo);
		@SuppressWarnings({ "rawtypes", "unchecked" })
		ChoiceBox<String> cb = new ChoiceBox(FXCollections.observableArrayList());

		cb.setLayoutX(370);
		cb.setLayoutY(85);
		cb.setStyle(
				"-fx-background-color: #303030;-fx-mark-color: #6c0080; -fx-border-style: none none solid none;\"\r\n"
						+ "				+ \"-fx-border-color: transparent transparent white transparent;");

		File[] flanguages = new File(System.getenv("APPDATA") + "\\CV-Reader\\languages").listFiles();
		ObservableList<String> list = FXCollections.observableArrayList();

		for (File file : flanguages) {
			String filename = file.getName().substring(0, file.getName().length() - 5);
			list.add(filename);
		}

		cb.setItems(list);

		pane.getChildren().add(cb);

		Button submit = new PurpleButton("Submit");
		String styleButton = "-fx-background-color: #6c0080; " + "-fx-text-fill: #fff; "
				+ "-fx-background-radius: 20px; " + "-fx-max-height: 30px;" + "-fx-min-height: 30px;"
				+ "-fx-max-width: 150px;" + "-fx-min-width: 150px;" + "-fx-font: 14px \"Arial Bold\";";
		submit.setStyle(styleButton);
		Main.setButtonLocation(submit, 100, 350);

		TextField errorMessage = new TextField();
		errorMessage.setStyle(styleInfoMesaj);
		errorMessage.setEditable(false);
		Main.setTextLocation(errorMessage, 20, 340);
		File f = new File(System.getenv("APPDATA") + "\\CV-Reader\\config.json");

		Button newLanguageFile = new PurpleButton("New Language");
		newLanguageFile.setStyle(styleButton);
		Main.setButtonLocation(newLanguageFile, 260, 350);

		AnimationEvents.eventHoverLeave(newLanguageFile);
		AnimationEvents.eventHoverEntry(newLanguageFile);

		pane.getChildren().add(newLanguageFile);

		Button editLanguageFile = new PurpleButton("Edit Language");
		editLanguageFile.setStyle(styleButton);
		Main.setButtonLocation(editLanguageFile, 420, 350);

		AnimationEvents.eventHoverLeave(editLanguageFile);
		AnimationEvents.eventHoverEntry(editLanguageFile);

		pane.getChildren().add(editLanguageFile);

		newFileLanguageEvent(scene, pane, newLanguageFile);
		editFileLanguageEvent(scene, pane, editLanguageFile, cb);

		if (f.exists()) {
			JSONParser parser = new JSONParser();
			try {

				Object obj = parser.parse(new FileReader(System.getenv("APPDATA") + "\\CV-Reader\\config.json"));

				JSONObject jsonObject = (JSONObject) obj;

				String DBName = (String) jsonObject.get("DB Name");
				fieldDbName.setText(DBName);
				String parola = (String) jsonObject.get("Parola");
				fieldParola.setText(parola);
				String ip = (String) jsonObject.get("IP");
				fieldIP.setText(ip);
				String port = (String) jsonObject.get("Port");
				fieldPort.setText(port);
				String username = (String) jsonObject.get("Username");
				fieldUsername.setText(username);

				if (jsonObject.containsKey("Language")) {
					cb.setValue((String) jsonObject.get("Language"));
				} else {
					cb.getSelectionModel().selectFirst();
				}

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		EventHandler<ActionEvent> buttonHandler = new EventHandler<ActionEvent>() {
			@SuppressWarnings("unchecked")
			@Override
			public void handle(ActionEvent event) {
				if (!fieldIP.getText().equals("")) {
					config.put("IP", fieldIP.getText());
					errorMessage.clear();
				} else {
					fieldIP.clear();
					errorMessage.setText("Eroare la ip-ul bazei de date!");
					if (!pane.getChildren().contains(errorMessage))
						pane.getChildren().add(errorMessage);
					return;
				}

				if (!fieldUsername.getText().equals("")) {
					config.put("Username", fieldUsername.getText());
					errorMessage.clear();
				} else {
					fieldUsername.clear();
					errorMessage.setText("Eroare la username-ul bazei de date!");
					if (!pane.getChildren().contains(errorMessage))
						pane.getChildren().add(errorMessage);
					return;
				}

				if (fieldParola.getText().equals("")) {
					config.put("Parola", fieldParola.getText());
					errorMessage.clear();
				}

				if (!fieldPort.getText().equals("")) {
					config.put("Port", fieldPort.getText());
					errorMessage.clear();
				} else {
					fieldPort.clear();
					errorMessage.setText("Eroare la port bazei de date!");
					if (!pane.getChildren().contains(errorMessage))
						pane.getChildren().add(errorMessage);
					return;
				}

				if (!fieldDbName.getText().equals("")) {
					config.put("DB Name", fieldDbName.getText());
					errorMessage.clear();
				} else {
					fieldDbName.clear();
					errorMessage.setText("Eroare la numele bazei de date!");
					if (!pane.getChildren().contains(errorMessage))
						pane.getChildren().add(errorMessage);
					return;
				}

				config.put("Language", cb.getValue());
				new File(System.getenv("APPDATA") + "\\CV-Reader").mkdirs();

				try (FileWriter file1 = new FileWriter(System.getenv("APPDATA") + "\\CV-Reader\\config.json")) {

					file1.write(config.toJSONString());
					file1.flush();

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};

		AnimationEvents.eventHoverLeave(submit);
		AnimationEvents.eventHoverEntry(submit);
		submit.setOnAction(buttonHandler);
		pane.getChildren().add(submit);
	}

	/**
	 * Functie care seteaza evenimentul pentru butonul de creeare nou fisier de
	 * language
	 * 
	 * @param scene
	 * @param pane
	 * @param button
	 */
	private void newFileLanguageEvent(Scene scene, Pane pane, Button button) {
		EventHandler<ActionEvent> buttonHandler = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				pane.getChildren().clear();
				MakeLanguagePane language = new MakeLanguagePane();
				language.setContent(scene);

			}

		};

		button.setOnAction(buttonHandler);
	}

	/**
	 * Functie care seteaza evenimentul pentru butonul de editare fisier language
	 * 
	 * @param scene
	 * @param pane
	 * @param button
	 */
	private void editFileLanguageEvent(Scene scene, Pane pane, Button button, ChoiceBox<String> box) {
		EventHandler<ActionEvent> buttonHandler = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				pane.getChildren().clear();
				EditLanguagePane language = new EditLanguagePane();
				language.setContent(scene, box);

			}

		};

		button.setOnAction(buttonHandler);
	}

}
