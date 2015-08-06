package vosters.lab1;

import javax.swing.JOptionPane;

public class ComputeLoanAmount {
	public static void main(String[] args) {
		/** ******************************************************************** */
		/* Program to calculate a loan amount based on a given interest rate,    */
		/* payment amount, and number of years to repay                          */
		/* Created by Jacob Vosters                                             */
		/* Date last modified:  7/31/2015                                        */
		/*********************************************************************** */
		// variables representing input interest rate
			double interestRate = Double.parseDouble(JOptionPane.showInputDialog("Enter an interest rate percentage (with no %):")) / 100;
		// variables representing length of loan in years, also total months
			double loanYears = Integer.parseInt(JOptionPane.showInputDialog("Enter a loan term in years:"));
			double loanMonths = loanYears * 12;
		// variable representing payment amount
			double monthlyPayment = Double.parseDouble(JOptionPane.showInputDialog("Enter a monthly payment amount (with no $:)"));
		// calculate the loan amount
			System.out.println(loanMonths);
			double loanAmount = (monthlyPayment/(interestRate/12))*(1 - Math.pow((1 + (interestRate/12)), -loanMonths));
		// display loan amount in a dialog box
			JOptionPane.showMessageDialog(null, "Your loan amount is: $" + String.format("%.2f", loanAmount));
	}
}
