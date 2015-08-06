package vosters.lab2;

import javax.swing.JOptionPane;

public class LeapYear {

	public static void main(String[] args) {
		while(true){
			// Input a year
			int year = Integer.parseInt(JOptionPane.showInputDialog("Enter a year:"));
			
			//calculate if it is a leap year
			//Is a leap year if it is divisible by 4 and not divisible by 100 OR divisible by 4 and divisible by 400
			boolean isLeap = false;
			if(year%4 == 0 && year%100 != 0){
				isLeap = true;
			} else if(year%4 == 0 && year%400 == 0){
				isLeap = true;
			}
			
			//output answer
			if(isLeap == true){
				JOptionPane.showMessageDialog(null, year + " is a leap year.");
			} else {
				JOptionPane.showMessageDialog(null, year + " is not a leap year.");
			}
		}
	}
}
