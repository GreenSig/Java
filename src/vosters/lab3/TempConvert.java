package vosters.lab3;

import java.math.RoundingMode;
import java.text.DecimalFormat;

import javax.swing.JOptionPane;

public class TempConvert {

	public static void main(String[] args) {
		String input;
		double tempF, tempC;
		
		DecimalFormat df = new DecimalFormat("#.#");
		df.setRoundingMode(RoundingMode.DOWN);

		do {
			input = JOptionPane
					.showInputDialog("Enter a temperature in Fahrenheit:");
			if (!input.equals("")) {
				tempF = Double.parseDouble(input);
				tempC = (tempF - 32) * (5.0 / 9.0);
				JOptionPane.showMessageDialog(null,
						"The temperature in Celsius is "
								+ df.format(tempC));
			}
		} while (!input.equals(""));
	}

}