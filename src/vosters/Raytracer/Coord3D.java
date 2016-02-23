package vosters.Raytracer;

public class Coord3D {
	private Double x, y, z;

	public Coord3D(Double x, Double y, Double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Double getX() {
		return x;
	}

	public Double getY() {
		return y;
	}

	public Double getZ() {
		return z;
	}

	public double distanceBetween(Coord3D p) {
		// sqrt( (x1-x2)^2 + (y1-y2)^2 + (z1-z2)^2 )
		return Math.sqrt(Math.pow(x - p.getX(), 2) + Math.pow(y - p.getY(), 2)
				+ Math.pow(z - p.getZ(), 2));
	}

	public double dot(Coord3D p) {
		// computes the dot product
		// x1*x2 + y1*y2 + z1*z2
		System.out.println("dot prod:" + p.getZ() * z);
		return ((p.getX() * x) + (p.getY() * y) + (p.getZ() * z));
	}

	public Coord3D normalize() {
		double currentLen = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2)
				+ Math.pow(z, 2));
		return new Coord3D(x / currentLen, y / currentLen, z / currentLen);
	}

	public Coord3D add(Coord3D p) {
		// (x1+x2, y1+y2, z1+z2)
		return new Coord3D(p.getX() + x, p.getY() + y, p.getZ() + z);
	}

	public Coord3D subtract(Coord3D p) {
		// (x1+x2, y1+y2, z1+z2)
		return new Coord3D(x - p.getX(), y - p.getY(), z - p.getZ());
	}

	public Coord3D scale(double s) {
		return new Coord3D(x * s, y * s, z * s);
	}

	public String toString() {
		return "Point3D(" + x + ", " + y + ", " + z + ")";
	}

}
