package vosters.program4;

public class Sphere {
	private Coord3D center;
	private double radius;

	public Sphere(Coord3D center, double radius) {
		this.center = center;
		this.radius = radius;
	}

	public Coord3D getCenter() {
		return center;
	}

	public double getRadius() {
		return radius;
	}

	public String toString() {
		return "Sphere:[center=" + center + ", radius=" + radius + "]";
	}

}
