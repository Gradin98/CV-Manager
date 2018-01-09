package application.panes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import application.Main;
import application.Events.Search.EventSetup;
import application.Events.Search.NormalRegexSearch;
import application.databases.MysqlConnection;
import application.databases.SQLiteConnection;
import application.interfaceObj.AnimationEvents;
import application.interfaceObj.PurpleButton;
import application.language.GetLanguage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.util.StringConverter;

/**
 * 
 * Clasa care seteaza contentu pentru pagina de search
 * 
 * <ol>
 * Contine functiile:
 * <li>{@link #setContent(Scene, ChoiceBox)}</li>
 * </ol>
 * 
 * @author Kocsis Lorand
 *
 */
public class SearchPane {
	private final GetLanguage languagefile = new GetLanguage();
	private final MysqlConnection mysql = MysqlConnection.getInstance();
	private final SQLiteConnection sqlite = new SQLiteConnection();

	final private TextField searchfield = new TextField();
	final private String textFile = "-fx-font: 16px \"Arial Bold\";" + "-fx-text-fill: #fff; "
			+ "-fx-background-color: transparent;" + "-fx-min-width: 400px;" + "-fx-max-width: 400px;";

	final private DatePicker data1 = new DatePicker();
	final private DatePicker data2 = new DatePicker();
	@SuppressWarnings({ "unchecked", "rawtypes" })
	final private ChoiceBox<String> cb = new ChoiceBox(FXCollections.observableArrayList());

	final private TextField searchInfo = new TextField(languagefile.getLanguage("search-search-message"));
	final private Button submitButton = new PurpleButton(languagefile.getLanguage("search-search-button"));
	final private CheckBox checkRegex = new CheckBox(languagefile.getLanguage("search-regex-mode"));
	final private CheckBox checkDate = new CheckBox(languagefile.getLanguage("search-date-mode"));

	/**
	 * Functie care seteaza contentu pentru pagina de search a aplicatiei
	 * 
	 * @param scene
	 * @param box
	 */
	public void setContent(Scene scene) {

		final Pane homePane = (Pane) scene.lookup("#HomePane");
		final Pane imagePane = (Pane) scene.lookup("#SearchPane");
		final Pane settingsPane = (Pane) scene.lookup("#SettingsPane");
		final Pane pane = (Pane) scene.lookup("#contentPane");

		homePane.setStyle(homePane.getStyle() + "-fx-background-color: #212121;");
		imagePane.setStyle(imagePane.getStyle() + "-fx-background-color: #6c0080;");
		settingsPane.setStyle(homePane.getStyle() + "-fx-background-color: #212121;");

		pane.getChildren().clear();

		searchfield.setStyle(textFile + "-fx-border-style: none none solid none;"
				+ "-fx-border-color: transparent transparent white transparent;");
		Main.setTextLocation(searchfield, 20, 70);

		cb.setLayoutX(20);
		cb.setLayoutY(50);
		cb.setStyle(
				"-fx-background-color: #303030;-fx-mark-color: #6c0080; -fx-border-style: none none solid none;\"\r\n"
						+ "				+ \"-fx-border-color: transparent transparent white transparent;");

		ObservableList<String> list = FXCollections.observableArrayList();

		if (mysql.checkConnection()) {
			list.add(mysql.numeClasa());
		}
		if (sqlite.checkConnection()) {
			list.add(sqlite.numeClasa());
		}

		cb.setItems(list);
		cb.getSelectionModel().selectFirst();
		data1.setLayoutX(20);
		data1.setLayoutY(160);

		data2.setLayoutX(220);
		data2.setLayoutY(160);
		data2.setConverter(new StringConverter<LocalDate>() {
			private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyy-MM-dd");

			@Override
			public String toString(LocalDate localDate) {
				if (localDate == null)
					return "";
				return dateTimeFormatter.format(localDate);
			}

			@Override
			public LocalDate fromString(String dateString) {
				if (dateString == null || dateString.trim().isEmpty()) {
					return null;
				}
				return LocalDate.parse(dateString, dateTimeFormatter);
			}
		});

		pane.getChildren().add(cb);
		pane.getChildren().add(data1);
		pane.getChildren().add(data2);

		searchInfo.setStyle(textFile);
		searchInfo.setEditable(false);
		Main.setTextLocation(searchInfo, 100, 50);

		pane.getChildren().add(searchfield);
		pane.getChildren().add(searchInfo);

		Main.setButtonLocation(submitButton, 450, 70);
		AnimationEvents.eventHoverLeave(submitButton);
		AnimationEvents.eventHoverEntry(submitButton);
		pane.getChildren().add(submitButton);
		checkRegex.setLayoutX(20);
		checkRegex.setLayoutY(120);
		checkRegex.setStyle(textFile);
		NormalRegexSearch.functie(searchfield, pane, submitButton, cb);

		checkDate.setLayoutX(180);
		checkDate.setLayoutY(120);
		checkDate.setStyle(textFile);
		EventSetup.regexSearchFunction(checkRegex, checkDate, searchfield, pane, submitButton, data1, data2, cb);
		EventSetup.dateSearchFunction(checkRegex, checkDate, searchfield, pane, submitButton, data1, data2, cb);

		pane.getChildren().add(checkRegex);
		pane.getChildren().add(checkDate);

	}

}
