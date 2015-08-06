package vosters.lab2;

import javax.swing.JOptionPane;

public class TriangleValidation {

	public static void main(String[] args) {
		while(true){
			//get the inputs for each side of the triangle
			double side1 = Double.parseDouble(JOptionPane.showInputDialog("Enter side 1:"));
			double side2 = Double.parseDouble(JOptionPane.showInputDialog("Enter side 2:"));
			double side3 = Double.parseDouble(JOptionPane.showInputDialog("Enter side 3:"));

			//determine if equilateral, isosceles, or right
			boolean equilateral = false, isoceles = false, right = false;
			if((side1 == side2) && (side1 == side3)){
				equilateral = true;
			}
			if((side1 > side3) && (side2 > side3) || (side1 > side2) && (side3 > side2) || (side2 > side1) && (side3 > side1)
					|| (side1 == side2) && (side1 == side3)
					|| ((side1 < side3) && (side2 < side3) && ((side1 + side2) > side3))
					|| ((side1 < side2) && (side3 < side2) && ((side1 + side3) > side2))
					|| ((side2 < side1) && (side3 < side1) && ((side3 + side2) > side1))){
				isoceles = true;
			}
			if(Math.pow(side1, 2) + Math.pow(side2, 2) == Math.pow(side3, 2)
					|| Math.pow(side1, 2) + Math.pow(side3, 2) == Math.pow(side2, 2)
					|| Math.pow(side3, 2) + Math.pow(side2, 2) == Math.pow(side1, 2)){
				right = true;
			}

			//Display the triangle types
			String output = "";
			if(equilateral == false && isoceles == false && right == false){
				output = "Not Valid";			
			} else {
				if (equilateral == true){
					output += "Equilateral\n";
				}
				if (isoceles == true){
					output += "Isoceles\n";
				}
				if (right == true){
					output += "Right";
				}
			}
			JOptionPane.showMessageDialog(null, "The triangle with sides " + side1 + ", " + side2 + ", " + side3 + " is: \n" + output);

		}
	}
}
