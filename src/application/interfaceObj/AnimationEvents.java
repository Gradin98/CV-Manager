package application.interfaceObj;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

/**
 * 
 * Clasa care seteaza efectul de hover la butoane
 * 
 * @author Kocsis Lorand
 *
 */
public class AnimationEvents {

	/**
	 * Functie care stabileste designul la trecerea mouse-ului peste buton
	 * 
	 * @param button
	 *            Buton care se adauga evenimentul
	 */
	public static void eventHoverEntry(Button button) {
		EventHandler<MouseEvent> buttonHandler = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				String style = "-fx-background-color: #212121; " + "-fx-text-fill: #fff; "
						+ "-fx-background-radius: 20px; " + "-fx-max-height: 30px;" + "-fx-min-height: 30px;"
						+ "-fx-max-width: 150px;" + "-fx-min-width: 150px;" + "-fx-font: 14px \"Arial Bold\";";
				button.setStyle(style);
			}
		};
		button.setCursor(Cursor.HAND);
		button.setOnMouseEntered(buttonHandler);
	}

	/**
	 * Functie care restabileste designul initial al butonului
	 * 
	 * @param button
	 *            Buton care se adauga evenimentul
	 */
	public static void eventHoverLeave(Button button) {
		EventHandler<MouseEvent> buttonHandler = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				String style = "-fx-background-color: #6c0080; " + "-fx-text-fill: #fff; "
						+ "-fx-background-radius: 20px; " + "-fx-max-height: 30px;" + "-fx-min-height: 30px;"
						+ "-fx-max-width: 150px;" + "-fx-min-width: 150px;" + "-fx-font: 14px \"Arial Bold\";";
				button.setStyle(style);
			}
		};
		button.setCursor(Cursor.DEFAULT);
		button.setOnMouseExited(buttonHandler);
	}

}
