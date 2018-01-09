package application.Events.Search;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 * Clasa care seteaza evenimentul corect in functie de ce mod este bifat in
 * panoul de Search.
 * 
 * <ol>
 * Contine functiile:
 * <li>{@link #regexSearchFunction(CheckBox, CheckBox, TextField, Pane, Button, DatePicker, DatePicker, ChoiceBox)}</li>
 * <li>{@link #dateSearchFunction(CheckBox, CheckBox, TextField, Pane, Button, DatePicker, DatePicker, ChoiceBox)}</li>
 * </ol>
 * 
 * @author Kocsis Lorand
 * 
 */

public class EventSetup {

	/**
	 * Functie care seteaza evenimentul evenimentul pentru Checkboxul "Regex Mode"
	 * 
	 * @param check1
	 *            Checkboxul "Regex Mode"
	 * @param check2
	 *            CheckBoxul "Date Mode"
	 * @param searchfield
	 *            Fieldul in care se adauga cautarea
	 * @param pane
	 *            Panoul la care se adauga obiectele gasite in baza de date
	 * @param submitButton
	 *            Butonul care executa functia finala de cauare
	 * @param data1
	 *            Data initiala pentru cautare
	 * @param data2
	 *            Data finala pentru cautare
	 * @param cb
	 *            ChoiceBox-ul care seteaza tipul bazei de date
	 */
	public static void regexSearchFunction(CheckBox check1, CheckBox check2, TextField searchfield, Pane pane,
			final Button submitButton, DatePicker data1, DatePicker data2, ChoiceBox<String> cb) {

		EventHandler<ActionEvent> checkerhandler = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				if (check1.isSelected() && check2.isSelected()) {
					DateSearch.functie(searchfield, pane, submitButton, data1, data2, cb);
				} else if (check2.isSelected()) {
					DateSearch.functie(searchfield, pane, submitButton, data1, data2, cb);
				} else if (check1.isSelected()) {
					RegexSearch.functie(searchfield, pane, submitButton, cb);
				} else {
					NormalRegexSearch.functie(searchfield, pane, submitButton, cb);
				}

			}
		};

		check1.setOnAction(checkerhandler);

	}

	/**
	 * 
	 * Functie care seteaza evenimentul evenimentul pentru Checkboxul "Date Mode"
	 * 
	 * @param check1
	 *            Checkboxul "Regex Mode"
	 * @param check2
	 *            CheckBoxul "Date Mode"
	 * @param searchfield
	 *            Fieldul in care se adauga cautarea
	 * @param pane
	 *            Panoul la care se adauga obiectele gasite in baza de date
	 * @param submitButton
	 *            Butonul care executa functia finala de cauare
	 * @param data1
	 *            Data initiala pentru cautare
	 * @param data2
	 *            Data finala pentru cautare
	 * @param cb
	 *            ChoiceBox-ul care seteaza tipul bazei de date
	 */
	public static void dateSearchFunction(CheckBox check1, CheckBox check2, TextField searchfield, Pane pane,
			final Button submitButton, DatePicker data1, DatePicker data2, ChoiceBox<String> cb) {

		EventHandler<ActionEvent> checkerhandler = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				if (check1.isSelected() && check2.isSelected()) {
					DateSearch.functie(searchfield, pane, submitButton, data1, data2, cb);
				} else if (check2.isSelected()) {
					DateRegexSearch.functie(searchfield, pane, submitButton, data1, data2, cb);
				} else if (check1.isSelected()) {
					RegexSearch.functie(searchfield, pane, submitButton, cb);
				} else {
					NormalRegexSearch.functie(searchfield, pane, submitButton, cb);
				}

			}
		};

		check2.setOnAction(checkerhandler);

	}
}
