package cz.upol.inf.jj2.martinbrablik.javafx2;

import java.util.function.BinaryOperator;

public class Caesar {
	
	public static final int lettersStart = 65;
	public static final int lettersEnd = 90;
	public static final int capitalDifference = 32;
	public static final int alphabetLettersCount = 26;
	
	private static String applyChanges(String in, char key, BinaryOperator<Character> method) {
		var charArray = in.toCharArray();
		for(int i = 0; i < in.length(); i++) {
			charArray[i] = method.apply(charArray[i], key);
		}
		return new String(charArray);
	}
	
	public static String decypher(String in, char key) {
		return applyChanges(in, key, Caesar::decypherChar);
	}
	public static String cypher(String in, char key) {
		return applyChanges(in, key, Caesar::cypherChar);
	}
	
	public static boolean validateInput(String in) {
		var charArray = in.toCharArray();
		for(int i = 0; i < in.length() ; i++) {
			if(!Character.isLetter(charArray[i]) && charArray[i] != ' ' && charArray[i] != '.')
				return false;
		}
		return true;
	}
	
	public static boolean validateKey(char key) {
		return key <= alphabetLettersCount;
	}
	
	private static char cypherChar(char c, char key) {
		if(!Character.isLetter(c))
			return c;
		char newChar = (char) (c + key);
		boolean rectify = Character.isLowerCase(c) ? newChar > lettersEnd + capitalDifference : newChar > lettersEnd;
		return (char) (rectify ? newChar - (lettersEnd - lettersStart) - 1 : newChar);
	}
	
	private static char decypherChar(char c, char key) {
		if(!Character.isLetter(c))
			return c;
		char newChar = (char) (c - key);
		boolean rectify = Character.isLowerCase(c) ? newChar < lettersStart + capitalDifference : newChar < lettersStart;
		return (char) (rectify ? newChar + (lettersEnd - lettersStart) + 1 : newChar);
	}
}