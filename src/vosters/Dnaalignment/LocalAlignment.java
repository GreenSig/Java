package vosters.Dnaalignment;

import javax.swing.JTextField;
import javax.swing.text.Caret;

public class LocalAlignment {
	public String stringX;
	public String stringY;
	public int string1MatchStart;
	public int string2MatchStart;
	public int matchLength;
	
	public LocalAlignment(String string1, String string2,
			int string1MatchStart, int string2MatchStart, int matchLength) {
		stringX = string1;
		stringY = string2;
		this.string1MatchStart = string1MatchStart;
		this.string2MatchStart = string2MatchStart;
		this.matchLength = matchLength;


	}
	public void showAlignment(JTextField string1Field, JTextField string2Field){
		string1Field.setText(stringY);
		string2Field.setText(stringX);
		Caret s1 = string1Field.getCaret();
		Caret s2 = string2Field.getCaret();
		s1.setDot(string2MatchStart);
		s1.moveDot(string2MatchStart + matchLength);
		s1.setSelectionVisible(true);
		s2.setDot(string1MatchStart);
		s2.moveDot(string1MatchStart + matchLength);
		s2.setSelectionVisible(true);
		string1Field.setCaret(s1);
		string2Field.setCaret(s2);
		
	
	}
}
