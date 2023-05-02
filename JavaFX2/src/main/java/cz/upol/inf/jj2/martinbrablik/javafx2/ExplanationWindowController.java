package cz.upol.inf.jj2.martinbrablik.javafx2;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class ExplanationWindowController extends Controller {
	
	private String decypherIn;
	private String cypherIn;
	private String keyIn;

	@FXML private Text explanation;
	
	public ExplanationWindowController() {
		decypherIn = Main.mainWindowController.getDecypherInText();
		cypherIn = Main.mainWindowController.getCypherInText();
		keyIn = Main.mainWindowController.getKeyInText();
	}
	
	@Override
	public void initialize() {
		explanation.setText(makeExplanation());
	}
	
	private String makeExplanation() {
		if(decypherIn.length() <= 0 || cypherIn.length() <= 0)
			return "";
		if(decypherIn.length() != cypherIn.length())
			return "";
		
		int length = cypherIn.length();
		StringBuilder sb = new StringBuilder();
		var decypherChars = decypherIn.toCharArray();
		var cypherChars = cypherIn.toCharArray();		
		for(int i = 0; i < length; i++) {
			int charNumber = decypherChars[i] - (Character.isUpperCase(decypherChars[i]) ? Caesar.lettersStart : Caesar.lettersStart - Caesar.capitalDifference) + 1;
			int cypheredCharNumber = charNumber + Integer.parseInt(keyIn);
			 sb.append(decypherChars[i]).append(" = ").append(charNumber)
			 .append("\n")
			 .append(charNumber).append(" + ").append(keyIn).append(" = ").append(cypheredCharNumber)
			 .append("\n")
			 .append(cypheredCharNumber).append(" = ").append(cypherChars[i])
			 .append("\n\n");
		}
		sb.delete(sb.length() - 2, sb.length());
		return sb.toString();
	}
}
