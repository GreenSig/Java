package vosters.lab1;
import javax.swing.JOptionPane;

public class ComputePayment {

	public static void main(String[] args) {
		/***********************************************************************/
		/* Program to calculate a loan payment based on a given interest rate, */
		/* loan amount, and number of years to repay                           */
		/* Created by Your Name Here                                           */
		/* Date last modified:  7/31/2015                                       */
		/***********************************************************************/
		// variables representing interest rates
		double annualInterestRate = 0.0;                      // annual rate
		double monthlyInterestRate = 0.0;                     // monthly rate

		// other variables
		int numberOfYears = 0;                    // number of years for loan
		int numPayments = 0;                     // total number of payments
		double loanAmount = 0.0;                 // loan amount

		// setting initial values (we will later replace some of these with input statements)
		String inputValue = JOptionPane.showInputDialog(null, "Enter the yearly interest rate as a percentage (no % sign):");
		annualInterestRate = Double.parseDouble(inputValue) / 100;                    
		monthlyInterestRate = annualInterestRate / 12;
		inputValue = JOptionPane.showInputDialog(null, "Enter the loan term in years:");
		numberOfYears = Integer.parseInt(inputValue);                       
		numPayments = numberOfYears * 12;
		inputValue = JOptionPane.showInputDialog(null, "Enter the loan amount in dollars (no $ sign):");
		loanAmount = Double.parseDouble(inputValue);

		// calculate the monthly payment
		double monthlyPayment = (monthlyInterestRate + (monthlyInterestRate
				/ (Math.pow(1 + monthlyInterestRate, numPayments) - 1)))  * loanAmount;

		// format payment to the nearest dollar
		int formattedPaymentAmount = (int)(monthlyPayment * 100);
		monthlyPayment = (double)formattedPaymentAmount / 100;

		// display monthly payment
		JOptionPane.showMessageDialog(null, "Monthly Payment: " + monthlyPayment + "\nTotal Interest Paid: " + String.format("%.2f",((monthlyPayment * numberOfYears * 12) - loanAmount)));


	}

}
