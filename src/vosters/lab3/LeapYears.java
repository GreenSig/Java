package vosters.lab3;

import javax.swing.JOptionPane;

public class LeapYears {
	public static void main(String[] args) {
		int startYear, endYear;
		String output = "";

		startYear = Integer.parseInt(JOptionPane.showInputDialog(null,
				"Please enter a starting year:"));
		endYear = Integer.parseInt(JOptionPane.showInputDialog(null,
				"Please enter an ending year:"));
		
		int format = 0;
		for (int i = startYear; i < endYear; i++) {
			
			if (i % 4 == 0 && i % 100 != 0) {
				output += " " + i;
				format++;
			} else if (i % 4 == 0 && i % 400 == 0) {
				output += "" + i;
				format++;
			}
			
			if(output.length() != 0 && format % 5 == 0 && output.charAt(output.length()-1) != '\n'){
				output += "\n";
			}
			
		}
		
		JOptionPane.showMessageDialog(null, output);
	}

}
