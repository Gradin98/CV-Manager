package application;

import application.panes.MainPane;
import application.panes.SearchPane;
import application.panes.SettingsPane;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Aceasta clasa este controlerul de evenimente pentru windowul facut cu
 * builder.
 * 
 * @author Kocsis Lorand
 * @version 1.0

 * 
 */
public class Controler {
	private double xOffset = 0;
	private double yOffset = 0;
	private Boolean Home = false;
	private Boolean Settings = false;
	private Boolean Search = false;

	@FXML
	private Button closeButton;
	@FXML
	private Button minimalizeButton;
	@FXML
	private ImageView imageExit;
	@FXML
	private Pane panouPrincipal;
	@FXML
	private Pane contentPane;
	@FXML
	private ImageView imageSearch;
	@FXML
	private ImageView imageHome;
	@FXML
	private ImageView imageSettings;
	@FXML
	private ImageView reloadButton;

	@FXML
	/**
	 * Eveniment care face functional butonul de inchidere al aplicatiei
	 * 
	 * @param event
	 */
	public void CloseButton(ActionEvent event) {
		Stage stage = (Stage) closeButton.getScene().getWindow();
		stage.close();
	}

	@FXML
	/**
	 * Eveniment care face functional butonul de minimalizare al aplicatiei
	 * 
	 * @param event
	 *
	 */
	public void MinimalizeButton(ActionEvent event) {
		Stage stage = (Stage) minimalizeButton.getScene().getWindow();
		stage.setIconified(true);
	}

	@FXML
	/**
	 * Eveniment care face functional butonul de minimalizare al aplicatiei
	 * in meniul din stanga
	 * 
	 * @param event
	 */
	public void CloseButton2(MouseEvent event) {
		Stage stage = (Stage) imageExit.getScene().getWindow();
		stage.close();
	}

	@FXML
	/**
	 * Evenimentul de reload al aplicatiei
	 * TO DO:
	 * 		De remediat
	 * @param event
	 */
	public void Reload(MouseEvent event) {
		Scene scene = (Scene) reloadButton.getScene();
		if (Home) {
			MainPane home = new MainPane();
			home.setContent(scene);

		} else if (Search) {
			SearchPane search = new SearchPane();
			search.setContent(scene);

		} else if (Settings) {
			SettingsPane settings = new SettingsPane();
			settings.setContent(scene);
		}
	}

	@FXML
	/**
	 * Evenimentul care permite mutarea aplicatiei alaturi de functia MouseDragWindow
	 *
	 * @param event
	 */
	public void MousePressWindow(MouseEvent event) {
		if (event.getButton() == MouseButton.PRIMARY && event.getSceneY() < 30
				&& (event.getSceneX() > 100 && event.getSceneX() < 650)) {
			Stage stage = (Stage) panouPrincipal.getScene().getWindow();

			xOffset = stage.getX() - event.getScreenX();
			yOffset = stage.getY() - event.getScreenY();
		}
	}

	@FXML
	/**
	 * Evenimentul care permite mutarea aplicatiei alaturi de functia MousePressWindow
	 * 
	 * @param event
	 */
	public void MouseDragWindow(MouseEvent event) {
		if (event.getButton() == MouseButton.PRIMARY && event.getSceneY() < 30
				&& (event.getSceneX() > 100 && event.getSceneX() < 650)) {
			Stage stage = (Stage) panouPrincipal.getScene().getWindow();
			stage.setX(event.getScreenX() + xOffset);
			stage.setY(event.getScreenY() + yOffset);
		}
	}

	/**
	 * Functie care creaza panoul pentru Home
	 * <p>
	 * Apeleaza clasa {@link MainPane}
	 * </p
	 * @param event
	 */
	public void homePanel(MouseEvent event) {
		final Scene scene = (Scene) imageHome.getScene();
		final MainPane home = new MainPane();

		Home = true;
		Settings = false;
		Search = false;

		home.setContent(scene);
	}

	/**
	 * Functie care creaza panoul pentru Search
	 * <p>
	 * Apeleaza clasa {@link SearchPane}
	 * </p
	 * @param event
	 */
	public void searchPanel(MouseEvent event) {
		Scene scene = (Scene) imageSearch.getScene();
		SearchPane search = new SearchPane();

		Home = false;
		Settings = false;
		Search = true;

		search.setContent(scene);

	}

	/**
	 * Functie care creaza panoul pentru Settings
	 * <p>
	 * Apeleaza clasa {@link SettingsPane}
	 * </p
	 * @param event
	 */
	public void settingsPanel(MouseEvent event) {
		Scene scene = (Scene) imageSettings.getScene();
		SettingsPane settings = new SettingsPane();

		Home = false;
		Settings = true;
		Search = false;

		settings.setContent(scene);

	}

}
