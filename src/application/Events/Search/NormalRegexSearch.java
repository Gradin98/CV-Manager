package application.Events.Search;

import java.util.TreeMap;
import java.util.Map.Entry;

import application.TransformRegex;
import application.databases.Databases;
import application.databases.MysqlConnection;
import application.databases.SQLiteConnection;
import application.interfaceObj.AnimationEvents;
import application.interfaceObj.PurpleButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 * Clasa care seteaza evenimentul pentru butonul de submit al pagini de search
 * <p>
 * cand modul regex nu este bifat.
 * </p>
 * 
 * <ol>
 * Contine functiile:
 * <li>{@link #functie(TextField, Pane, Button, ChoiceBox)}</li>
 * </ol>
 * 
 * @author Kocsis Lorand
 * 
 */

public class NormalRegexSearch {

	/**
	 * 
	 * Functie care seteaza evenimentul pe butonul submitButton cand
	 * <p>
	 * modul regex nu este activat
	 * </p>
	 * 
	 * @param searchfield
	 *            Fieldul de inserare a cautarii
	 * @param pane
	 *            Panoul care adauga obiectele gasite in baza de date
	 * @param submitButton
	 *            Butonul la care este setat evenimentul
	 * @param cb
	 *            Alegerea tipului de baze de date
	 */
	public static void functie(TextField searchfield, Pane pane, Button submitButton, ChoiceBox<String> cb) {
		EventHandler<ActionEvent> submitNormalHandler = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {

				final TransformRegex transform = new TransformRegex();
				Databases connection = new SQLiteConnection();
				if (cb.getValue().equals("Mysql")) {
					connection = MysqlConnection.getInstance();
				}

				final TreeMap<String, String> date = connection.contentList(transform.transform(searchfield.getText()));
				int valueX = 20;
				int contor_posturi = 0;
				int valueY = 0;

				final ScrollPane sc = new ScrollPane();
				sc.setLayoutX(0);
				sc.setLayoutY(210);
				sc.setStyle(
						"-fx-background: #303030;-fx-border-color: #303030; -fx-max-width: 680px; -fx-min-width: 680px; -fx-min-height: 200px; -fx-max-height: 200px;");
				pane.getChildren().add(sc);
				sc.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
				final Pane panel2 = new Pane();
				panel2.setPadding(new Insets(0, 0, 20, 0));
				for (Entry<String, String> entry : date.entrySet()) {
					final Button button = new PurpleButton(entry.getKey());
					button.setLayoutX(valueX);
					button.setLayoutY(valueY);
					panel2.getChildren().add(button);

					AnimationEvents.eventHoverLeave(button);
					AnimationEvents.eventHoverEntry(button);

					EventHandler<ActionEvent> buttonHandlerPane = new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent arg0) {

							SearchedContent newinterface = new SearchedContent();
							newinterface.createInterface(entry, searchfield);
						}
					};
					button.setOnAction(buttonHandlerPane);

					valueX += 160;
					contor_posturi++;
					if (contor_posturi % 4 == 0) {
						valueX = 20;
						valueY += 50;
					}
				}
				sc.setContent(panel2);

			}

		};
		submitButton.setOnAction(submitNormalHandler);
	}

}
