package vosters.lab3;

import javax.swing.JOptionPane;

public class Palindrome {

	public static void main(String[] args) {

		String input;

		do {

			input = JOptionPane.showInputDialog(null,
					"Enter a palindrome (or not one):");
			if (!input.equals("")) {
				input = input.replace(" ", ""); // remove all whitespace
				input = input.toLowerCase(); //make them lower
				int i = 0;
				while (i < input.length()
						&& input.charAt(i) == input.charAt(input.length() - i
								- 1)) {
					i++;
				}

				if (i < input.length()) {
					JOptionPane.showMessageDialog(null,
							"This is not a palindrome");
				} else {
					JOptionPane.showMessageDialog(null,
							"This is a palindrome! Yay!");
				}
			}
		} while (!input.equals(""));
		
	}
}
