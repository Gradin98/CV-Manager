package application.interfaceObj;

import javafx.scene.control.Button;

/**
 * 
 * Clasa care creeaza un Button cu style prestabilit
 * 
 * @author Kocsis Lorand
 *
 */
public class PurpleButton extends Button{
	
	 private final String sStyle =
	            "-fx-background-color: #6c0080; "
	            + "-fx-text-fill: #fff; "
	            + "-fx-background-radius: 20px; "
	            + "-fx-max-height: 30px;"
	            + "-fx-min-height: 30px;"
	            + "-fx-max-width: 150px;"
	            + "-fx-min-width: 150px;"
	            + "-fx-font: 14px \"Arial Bold\";";

	    public PurpleButton(final String sText) {
	        super(sText);
	        this.setStyle(sStyle);
	    }

}
