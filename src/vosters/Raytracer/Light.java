package vosters.Raytracer;

public class Light {
	private Coord3D origin;
	private double intensity; // In range [0..1]

	public Light(Coord3D origin, double intensity) {

		this.origin = origin;
		this.intensity = intensity;
	}

	public Coord3D getOrigin() {
		return origin;
	}

	public double getIntensity() {
		return intensity;
	}

	public String toString() {
		return "Light [origin=" + origin + ", intensity=" + intensity + "]";
	}
	
	

}
