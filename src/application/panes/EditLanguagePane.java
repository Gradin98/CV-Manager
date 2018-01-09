package application.panes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import application.interfaceObj.AnimationEvents;
import application.interfaceObj.PurpleButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * 
 * Clasa care creeaza panoul de editare pentru fisierele de Language
 * 
 * <ol>
 * Contine functiile:
 * <li>{@link #setContent(Scene, ChoiceBox)}</li>
 * <li>{@link #makeTextArea(String, double, double)}</li>
 * <li>{@link #makeTextField(double, double)}</li>
 * </ol>
 * 
 * @author Kocsis Lorand
 *
 */
public class EditLanguagePane {

	private String style = "-fx-font: 16px \"Arial Bold\";" + "-fx-text-fill: #fff; "
			+ "-fx-background-color: transparent;";

	/**
	 * Functie care seteaza contentu pentru pagina de editat fisier de Language
	 * 
	 * @param scene
	 * @param box
	 */
	public void setContent(Scene scene, ChoiceBox<String> box) {

		final Pane pane = (Pane) scene.lookup("#contentPane");
		pane.getChildren().clear();

		final ScrollPane sc = new ScrollPane();
		sc.setLayoutX(0);
		sc.setLayoutY(10);
		sc.setStyle("-fx-background: #303030;-fx-border-color: #303030; -fx-max-width: 680px; -fx-min-width: 680px;");
		sc.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
		sc.setPrefSize(600, 400);
		pane.getChildren().add(sc);

		final Pane paneContent = new Pane();

		final Image img = new Image("image/back-arrow.png");
		final ImageView image = new ImageView(img);
		image.setLayoutX(580);
		paneContent.getChildren().add(image);

		final TextField fileName = makeTextArea("File name:", 20, 20);
		final TextField fieldName = makeTextArea(box.getValue(), 130, 20);

		final TextField text1 = makeTextArea("Choose File:", 20, 60);
		final TextField field1 = makeTextField(20, 80);

		final TextField text2 = makeTextArea("Choose your file", 20, 120);
		final TextField field2 = makeTextField(20, 140);

		final TextField text3 = makeTextArea("Export JSON", 20, 180);
		final TextField field3 = makeTextField(20, 200);

		final TextField text4 = makeTextArea("Export Mysql", 20, 240);
		final TextField field4 = makeTextField(20, 260);

		final TextField text5 = makeTextArea("Export type", 20, 300);
		final TextField field5 = makeTextField(20, 320);

		final TextField text6 = makeTextArea("Export XML", 20, 360);
		final TextField field6 = makeTextField(20, 380);

		final TextField text7 = makeTextArea("Regex Mode", 20, 420);
		final TextField field7 = makeTextField(20, 440);

		final TextField text8 = makeTextArea("Search", 20, 480);
		final TextField field8 = makeTextField(20, 500);

		final TextField text9 = makeTextArea("Search for someting", 20, 540);
		final TextField field9 = makeTextField(20, 560);

		final File f = new File(System.getenv("APPDATA") + "\\CV-Reader\\languages\\" + box.getValue() + ".json");

		if (f.exists()) {
			JSONParser parser = new JSONParser();
			try {

				Object obj = parser.parse(new FileReader(
						System.getenv("APPDATA") + "\\CV-Reader\\languages\\" + box.getValue() + ".json"));

				JSONObject jsonObject = (JSONObject) obj;

				field1.setText((String) jsonObject.get("home-chose-file-button"));
				field2.setText((String) jsonObject.get("home-chose-file-text"));
				field3.setText((String) jsonObject.get("home-export-JSON-button"));
				field4.setText((String) jsonObject.get("home-export-mysql-button"));
				field5.setText((String) jsonObject.get("home-export-text"));
				field6.setText((String) jsonObject.get("home-export-XML-button"));
				field7.setText((String) jsonObject.get("search-regex-mode"));
				field8.setText((String) jsonObject.get("search-search-button"));
				field9.setText((String) jsonObject.get("search-search-message"));

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		final Button buttonSave = new PurpleButton("Save");

		AnimationEvents.eventHoverLeave(buttonSave);
		AnimationEvents.eventHoverEntry(buttonSave);
		buttonSave.setLayoutX(20);
		buttonSave.setLayoutY(630);
		paneContent.getChildren().add(buttonSave);

		paneContent.setPadding(new Insets(0, 10, 30, 10));

		paneContent.getChildren().add(fileName);
		paneContent.getChildren().add(fieldName);
		paneContent.getChildren().add(text1);
		paneContent.getChildren().add(field1);

		paneContent.getChildren().add(text2);
		paneContent.getChildren().add(field2);

		paneContent.getChildren().add(text3);
		paneContent.getChildren().add(field3);

		paneContent.getChildren().add(text4);
		paneContent.getChildren().add(field4);

		paneContent.getChildren().add(text5);
		paneContent.getChildren().add(field5);

		paneContent.getChildren().add(text6);
		paneContent.getChildren().add(field6);

		paneContent.getChildren().add(text7);
		paneContent.getChildren().add(field7);

		paneContent.getChildren().add(text8);
		paneContent.getChildren().add(field8);

		paneContent.getChildren().add(text9);
		paneContent.getChildren().add(field9);

		EventHandler<ActionEvent> saveButtonEvent = new EventHandler<ActionEvent>() {

			@SuppressWarnings("unchecked")
			@Override
			public void handle(ActionEvent arg0) {

				if (fieldName.getText().equals("")) {
					return;
				}

				final JSONObject language = new JSONObject();

				final TextField[] array = new TextField[] { field1, field2, field3, field4, field5, field6, field7,
						field8, field9 };
				final String[] keys = new String[] { "home-chose-file-button", "home-chose-file-text",
						"home-export-JSON-button", "home-export-mysql-button", "home-export-text",
						"home-export-XML-button", "search-regex-mode", "search-search-button",
						"search-search-message" };

				for (int i = 0; i < array.length; i++) {
					language.put(keys[i], array[i].getText());
				}

				try (FileWriter file1 = new FileWriter(
						System.getenv("APPDATA") + "\\CV-Reader\\languages\\" + fieldName.getText() + ".json")) {

					file1.write(language.toJSONString());
					file1.flush();

				} catch (IOException e) {
					e.printStackTrace();
				}

				pane.getChildren().clear();

				final SettingsPane pane = new SettingsPane();
				pane.setContent(scene);

			}

		};

		EventHandler<MouseEvent> backEvent = new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				pane.getChildren().clear();

				final SettingsPane pane = new SettingsPane();
				pane.setContent(scene);

			}

		};

		buttonSave.setOnAction(saveButtonEvent);
		image.setOnMouseClicked(backEvent);
		sc.setContent(paneContent);

	}

	/**
	 * 
	 * Functie care creeaza {@link TextField} in functie de locatie si nume
	 * 
	 * @param text
	 * @param x
	 * @param y
	 * @return {@link TextField} modificat
	 */
	public TextField makeTextArea(String text, double x, double y) {
		final TextField value = new TextField(text);

		value.setStyle(style);
		value.setLayoutX(x);
		value.setLayoutY(y);
		value.setEditable(false);

		return value;
	}

	/**
	 * 
	 * Functie care creeaza {@link TextField} in functie de locatie
	 * 
	 * @param x
	 * @param y
	 * @return {@link TextField} modificat
	 */
	public TextField makeTextField(double x, double y) {
		final TextField value = new TextField();

		value.setStyle(style
				+ "-fx-min-width: 400px; -fx-max-width: 400px; -fx-border-style: none none solid none; fx-border-width: 1px; -fx-border-color: transparent transparent white transparent;");
		value.setLayoutX(x);
		value.setLayoutY(y);

		return value;
	}

}
