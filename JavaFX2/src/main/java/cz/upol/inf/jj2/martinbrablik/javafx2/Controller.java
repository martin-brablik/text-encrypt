package cz.upol.inf.jj2.martinbrablik.javafx2;

import javafx.stage.Stage;

public abstract class Controller {
	
	protected Stage stage;
	
	public void setPrimaryStage(Stage stage) {
		this.stage = stage;
	}
	
	public abstract void initialize();
}
