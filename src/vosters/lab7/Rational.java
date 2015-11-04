package vosters.lab7;

public class Rational {
	// instance variables
	private int n; // Represents this rational number's numerator
	private int d; // Represents this rational number's denominator

	// constructors
	public Rational() {
		n = 1;
		d = 1;
	}

	public Rational(int num, int denom) {
		n = num;
		d = denom;
	}

	public Rational(int num) {
		n = num;
		d = 1;
	}

	public Rational(Rational r) { // copy constructor
		n = r.n;
		d = r.d;
	}

	// methods
	public Rational multiply(Rational aRational) {
		int newNum = n * aRational.n;
		int newDenom = d * aRational.d;
		return (new Rational(newNum, newDenom));
	}

	public Rational divide(Rational aRational) {
		int newNum = n * aRational.d;
		int newDenom = aRational.n * d;
		return (new Rational(newNum, newDenom));
	}

	public Rational add(Rational aRational) {
		int newNum = (n * aRational.d) + (aRational.n * d);
		int newDenom = d * aRational.d;
		return (new Rational(newNum, newDenom));
	}

	public Rational subtract(Rational aRational) {
		int newNum = (n * aRational.d) - (aRational.n * d);
		int newDenom = d * aRational.d;
		return (new Rational(newNum, newDenom));
	}

	public String toString() {
		return (new String(n + "/" + d));
	}

	public double doubleValue() {
		return ((double) n / (double) d);
	}

	public boolean equals(Object o) {
		Rational aRational = (Rational) o;
		boolean isEqual = true;
		if (n != aRational.n || d != aRational.d) {
			isEqual = false;
		}
		return isEqual;
	}

	public void square() {
		n = n * n;
		d = d * d;
	}

	public void setRational(int newNum, int newDenom) {
		n = newNum;
		d = newDenom;
	}

}
