package cz.upol.inf.jj2.martinbrablik.javafx2;

import java.io.IOException;
import java.util.function.UnaryOperator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextFormatter.Change;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

public class MainWindowController extends Controller {
	
	@FXML private MenuBar menuBar;
	@FXML private Menu menuFile;
	@FXML private MenuItem itemCypher;
	@FXML private MenuItem itemDecypher;
	@FXML private MenuItem itemExit;
	@FXML private VBox textsPane;
	@FXML private Text lbDecypher;
	@FXML private TextArea decypherIn;
	@FXML private Text lbCypher;
	@FXML private TextArea cypherIn;
	@FXML private VBox controlsPane;
	@FXML private TextField keyIn;
	@FXML private Button btnCypher;
	@FXML private Button btnDecypher;
	@FXML private Button btnExplain;
	
	@Override
	public void initialize() {
		decypherIn.textProperty().addListener((a, b, c) -> btnExplain.setDisable(true));
		cypherIn.textProperty().addListener((a, b, c) -> btnExplain.setDisable(true));
		
		UnaryOperator<Change> integerFilter = change -> filterInteger(change);
		keyIn.setTextFormatter(new TextFormatter<Integer>(new IntegerStringConverter(), 0, integerFilter));
	}
	
	@FXML public void actionExit() {
		stage.close();
	}
	
	@FXML public void actionCypher(ActionEvent e) {
		String input = decypherIn.getText();
		char key = (char) Integer.parseUnsignedInt(keyIn.getText());
		if(!Caesar.validateKey(key)) {
			showAlert("Chybnì zadaný klíè", "Klíè musí bžt v rozsahu 0 - 26");
			return;
		}
		if(Caesar.validateInput(input)) {
			cypherIn.setText(Caesar.cypher(input, key));
			btnExplain.setDisable(false);
			return;
		}
		showAlert("Chybnì zadaný vstup", "Povoleny jsou pouze písmena (a - z), (A - Z), mezera a teèka");
	}
	
	@FXML public void actionDecypher(ActionEvent e) {
		String input = cypherIn.getText();
		char key = (char) Integer.parseUnsignedInt(keyIn.getText());
		if(!Caesar.validateKey(key)) {
			showAlert("Chybnì zadaný klíè", "Klíè musí bžt v rozsahu 0 - 26");
			return;
		}
		if(Caesar.validateInput(input)) {
			decypherIn.setText(Caesar.decypher(input, key));
			btnExplain.setDisable(false);
			return;
		}
		showAlert("Chybnì zadaný vstup", "Povoleny jsou pouze písmena (a - z), (A - Z), mezera a teèka");
	}
	
	@FXML public void actionExplain(ActionEvent e) throws IOException {
		FXMLLoader loader = new FXMLLoader(MainWindowController.class.getResource("/javafx2/ExplanationWindow.fxml"));
		Parent root = loader.load();
		
		Stage explainStage = new Stage();
		explainStage.initOwner(stage);
		explainStage.initModality(Modality.WINDOW_MODAL);
		explainStage.setTitle("Caesarova šifra - Vysvìtlení");	
		Scene scene = new Scene(root, 300, 200);
		explainStage.setScene(scene);
		explainStage.show();
	}
	
	private Change filterInteger(Change change) {
		String newText = change.getControlNewText();
	    if (newText.matches("([1-9][0-9]*)?")) { 
	        return change;
	    }
	    return null;
	}
	
	private void showAlert(String header, String content) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Chyba");
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.showAndWait();
	}
	
	public String getDecypherInText() {
		return decypherIn.getText();
	}
	
	public String getCypherInText() {
		return cypherIn.getText();
	}
	
	public String getKeyInText() {
		return keyIn.getText();
	}
}
