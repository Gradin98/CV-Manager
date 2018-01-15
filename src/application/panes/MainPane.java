package application.panes;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import application.Main;
import application.databases.Databases;
import application.databases.MysqlConnection;
import application.databases.SQLiteConnection;
import application.fileconverters.ParserFactory;
import application.fileconverters.ParserINTF;
import application.interfaceObj.AnimationEvents;
import application.interfaceObj.PurpleButton;
import application.language.GetLanguage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * Clasa care creaza Pagina principala a aplicatiei
 * 
 * <ol>
 * Contine functiile:
 * <li>{@link #setContent(Scene)}</li>
 * <li>{@link #fileChooserEvent(Button, TextField)}</li>
 * <li>{@link #exportEvent(Button, ChoiceBox)}</li>
 * </ol>
 * 
 * @author Kocsis Lorand
 *
 */
public class MainPane {

	private List<File> lista = new ArrayList<File>();
	private final GetLanguage languagefile = new GetLanguage();
	private MysqlConnection mysql = MysqlConnection.getInstance();
	private SQLiteConnection sqlite = new SQLiteConnection();

	private ObservableList<String> list = FXCollections.observableArrayList();

	/**
	 * Functie care seteaza contentu paginii principale a aplicatiei
	 * @param scene
	 */
	public void setContent(Scene scene) {
		final Pane pane = (Pane) scene.lookup("#contentPane");
		final Pane homePane = (Pane) scene.lookup("#HomePane");
		final Pane imagePane = (Pane) scene.lookup("#SearchPane");
		final Pane settingsPane = (Pane) scene.lookup("#SettingsPane");

		homePane.setStyle(homePane.getStyle() + "-fx-background-color: #6c0080;");
		imagePane.setStyle(imagePane.getStyle() + "-fx-background-color: #212121;");
		settingsPane.setStyle(homePane.getStyle() + "-fx-background-color: #212121;");

		pane.getChildren().clear();

		final Button buttonChoseFile = new PurpleButton(languagefile.getLanguage("home-chose-file-button"));

		final Button buttonExport = new PurpleButton(languagefile.getLanguage("home-export-XML-button"));

		Main.setButtonLocation(buttonChoseFile, 450, 70);
		Main.setButtonLocation(buttonExport, 450, 250);

		final TextField textChooseFile = new TextField(languagefile.getLanguage("home-chose-file-text"));
		final TextField textExportData = new TextField(languagefile.getLanguage("home-export-text"));
		final TextField showFileLocation = new TextField("");

		final String textFile = "-fx-font: 16px \"Arial Bold\";" + "-fx-text-fill: #fff; "
				+ "-fx-background-color: transparent;";
		textChooseFile.setStyle(textFile);
		textExportData.setStyle(textFile);
		showFileLocation.setStyle(textFile
				+ "-fx-min-width: 400px; -fx-max-width: 400px; -fx-border-style: none none solid none; fx-border-width: 1px; -fx-border-color: transparent transparent white transparent;");

		textChooseFile.setEditable(false);
		textExportData.setEditable(false);
		showFileLocation.setEditable(false);

		Main.setTextLocation(textChooseFile, 20, 50);
		Main.setTextLocation(showFileLocation, 20, 70);
		Main.setTextLocation(textExportData, 50, 210);

		fileChooserEvent(buttonChoseFile, showFileLocation);

		AnimationEvents.eventHoverLeave(buttonChoseFile);
		AnimationEvents.eventHoverEntry(buttonChoseFile);

		@SuppressWarnings({ "unchecked", "rawtypes" })
		ChoiceBox<String> cb = new ChoiceBox(FXCollections.observableArrayList());

		cb.setLayoutX(20);
		cb.setLayoutY(250);
		cb.setStyle(
				"-fx-background-color: #303030;-fx-mark-color: #6c0080; -fx-border-style: none none solid none;"
						+ "-fx-border-color: transparent transparent white transparent;");

		if (mysql.checkConnection()) {
			list.add(mysql.numeClasa());
		}
		if (sqlite.checkConnection()) {
			list.add(sqlite.numeClasa());
		}

		cb.setItems(list);
		cb.getSelectionModel().selectFirst();
		AnimationEvents.eventHoverLeave(buttonExport);
		AnimationEvents.eventHoverEntry(buttonExport);

		exportEvent(buttonExport, cb);

		pane.getChildren().add(textChooseFile);
		pane.getChildren().add(textExportData);
		pane.getChildren().add(showFileLocation);
		pane.getChildren().add(buttonChoseFile);
		pane.getChildren().add(cb);
		pane.getChildren().add(buttonExport);
	}





	/**
	 * Functie care seteaza evenimentul pentru alegerea fisierelor din local machine
	 * @param button
	 * @param text
	 */
	private void fileChooserEvent(Button button, TextField text) {

		FileChooser fileC = new FileChooser();

		EventHandler<ActionEvent> buttonChooseFile = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Stage stage = new Stage();
				lista = fileC.showOpenMultipleDialog(stage);
				if (lista != null) {
					text.setText(lista.get(0).getPath());
				}
			}
		};

		button.setOnAction(buttonChooseFile);
	}
	
	/**
	 * Functie care seteaza datele in baza de date
	 * @param button
	 * @param cb
	 */
	public void exportEvent(Button button, @SuppressWarnings("rawtypes") ChoiceBox cb) {

		EventHandler<ActionEvent> buttonExportMysqlEvent = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {

				Databases database = new SQLiteConnection();

				if (cb.getValue() == "Mysql") {
					database = MysqlConnection.getInstance();
				}

				if (lista != null) {
					final ParserFactory parserFactory = new ParserFactory();

					for (final File file : lista) {

						final ParserINTF parser = parserFactory.GetParser(file.getName());
						if (parser != null) {
							parser.setContent(file.getPath());
							database.setContent(parser.getContent(), file.getName());
						}

					}
				}
			}
		};

		button.setOnAction(buttonExportMysqlEvent);
	}
}
