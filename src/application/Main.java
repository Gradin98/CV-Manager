package application;

import java.io.IOException;

import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;

import application.databases.MysqlConnection;
import application.databases.SQLiteConnection;
import application.language.CreateDefaultLanguage;
import application.panes.MainPane;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Functia Main a aplicatiei
 * <ol>
 * Contine functiile:
 * <li>{@link #start(Stage)}</li>
 * <li>{@link #setTextLocation(TextField, double, double)}</li>
 * <li>{@link #setButtonLocation(Button, double, double)}</li>
 * <li>{@link #databaseCreate()}</li>
 * <li>{@link #main(String[])}</li>
 * </ol>
 * 
 * @author Kocsis Lorand
 */

public class Main extends Application {

	@FXML
	private static Pane contentPane;

	@Override
	public void start(Stage primaryStage) {
		try {

			Parent root = FXMLLoader.load(getClass().getResource("Sample.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("CV reader");
			primaryStage.setScene(scene);

			MainPane home = new MainPane();
			home.setContent(scene);

			CreateDefaultLanguage language = new CreateDefaultLanguage();
			language.createLanguage();
			primaryStage.initStyle(StageStyle.UNDECORATED);
			primaryStage.show();

			databaseCreate();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Functie care seteaza locatia unui {@link TextField}
	 * 
	 * @param text
	 *            Obiectul la care se aduc modificari
	 * @param x
	 *            Locatia x
	 * @param y
	 *            Locatia y
	 */
	public static void setTextLocation(TextField text, double x, double y) {
		text.setLayoutX(x);
		text.setLayoutY(y);
	}

	/**
	 * Functie care seteaza locatia unui {@link Button}
	 * 
	 * @param button
	 *            Obiectul la care se aduc modificari
	 * @param x
	 *            Locatia x
	 * @param y
	 *            Locatia y
	 */
	public static void setButtonLocation(Button button, double x, double y) {
		button.setLayoutX(x);
		button.setLayoutY(y);
	}

	/**
	 * Functia care creaza tabelele bazei de date in caz ca nu sunt facute.
	 * <p>
	 * Creeaza 2 obiecte {@link MysqlConnection} si {@link SQLiteConnection}
	 * </p>
	 * <p>
	 * Pentru mysql: Verifica daca serverul este deschis apoi creeaza in caz ca nu e
	 * creat
	 * </p>
	 * <p>
	 * Pentru sqlite: By default fisierele sqlite se creaza local, astfel se
	 * apeleaza doar functia de creeaza a bazei de date in caz ca nu e.
	 * </p>
	 */
	public static void databaseCreate() {
		MysqlConnection mysql = MysqlConnection.getInstance();
		SQLiteConnection sqlite = new SQLiteConnection();

		if (mysql.checkConnection()) {
			mysql.createDatabase();
		}

		sqlite.createDatabase();
	}

	/**
	 * Functia main care aprinde programul.
	 * @param args
	 * @throws InvalidPasswordException
	 * @throws IOException
	 */
	public static void main(String[] args) throws InvalidPasswordException, IOException {
		launch(args);

	}
}
