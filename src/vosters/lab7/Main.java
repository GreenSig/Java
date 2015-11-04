package vosters.lab7;

public class Main {

	public static void main(String[] args) {
		Rational r1 = new Rational(3, 2);
		Rational r2 = new Rational(3, 2);

		System.out.println(r1);
		System.out.println(r2.toString());

		System.out.println("Added: " + r1.add(r2).doubleValue());
		System.out.println("Subtracted: " + r1.subtract(r2).doubleValue());
		System.out.println("Multiplied: " + r1.multiply(r2).doubleValue());
		System.out.println("Divided: " + r1.divide(r2).doubleValue());

		if (r1.equals(r2)) {
			System.out.println("Equal");
		} else {
			System.out.println("Not Equal");
		}

		Rational r3 = new Rational(r1);
		System.out.println(r3); // Prints 3/2
		r1.square();
		System.out.println(r1); // Prints 9/4
		System.out.println(r3); // Prints 3/2

		Rational newR = new Rational(10, 3);
		newR.setRational(7, 3);
		System.out.println(newR);
	}

}
