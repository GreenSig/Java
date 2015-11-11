package vosters.program4;

import java.awt.Color;

public class Sphere {
	private Coord3D center;
	private double radius;
	private Color color;
	private double shine;

	public Sphere(Coord3D center, double radius, Color c, double shine) {
		this.center = center;
		this.radius = radius;
		this.color = c;
		this.shine = shine;
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

	public Color localReflectionMode(Coord3D intersectionPoint, Light l, Ray r) {

		double ambient = l.getIntensity();
		int newRed = color.getRed();
		int newGreen = color.getGreen();
		int newBlue = color.getBlue();

		// diffuse = lightIntensity * cos(angle between N and L)
		/*
		 * /* N = intersectionPoint.subtract(sphereCenter).normalize /* And L
		 * is: /* L = lightOrigin.subtract(intersectionPoint).normalize
		 */

		Coord3D N = intersectionPoint.subtract(center).normalize();
		Coord3D L = l.getOrigin().subtract(intersectionPoint).normalize();
		double totalIntensity = ambient;
		double nDotL = N.dot(L);
		double diffuse = l.getIntensity() * nDotL;

		// V = (incomingRay.getDirectionVector().normalize()).scale(-1)
		Coord3D V = (r.getDirectionVector().normalize()).scale(-1);
		// R = (((N.scale(2)).scale(N.dot(L))).subtract(L)).normalize()
		Coord3D R = (((N.scale(2)).scale(nDotL))).subtract(L).normalize();
		double rDotV = R.dot(V);
		double specular = l.getIntensity() * Math.pow(rDotV, shine);

		if (rDotV > 0) {
			totalIntensity += specular;
		}

		if (nDotL > 0) {
			totalIntensity += diffuse;
		}

		if (totalIntensity > 1) {
			totalIntensity = 1;
		}
		newRed = (int) (newRed * totalIntensity);
		newGreen = (int) (newGreen * totalIntensity);
		newBlue = (int) (newBlue * totalIntensity);

		return new Color(newRed, newGreen, newBlue);
	}
}
