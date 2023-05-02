package cz.upol.inf.jj2.martinbrablik.javafx2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	public static MainWindowController mainWindowController;
	
	public static void main(String[] args) {
		launch(args);
	}

	@SuppressWarnings("static-access")
	@Override
	public void start(Stage stage) throws Exception {
		FXMLLoader loader = new FXMLLoader(MainWindowController.class.getResource("/javafx2/MainWindow.fxml"));
		Parent root = loader.load();
		mainWindowController = loader.getController();
		mainWindowController.setPrimaryStage(stage);
		stage.setTitle("Caesarova šifra");
		stage.setScene(new Scene(root, 670,430));
		stage.show();
	}
}
