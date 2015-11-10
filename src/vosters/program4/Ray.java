package vosters.program4;

public class Ray {
	private Coord3D origin;
	private Coord3D directionVector; // A normalized direction VECTOR

	public Ray(Coord3D origin, Coord3D directionVector) {

		this.origin = origin;
		this.directionVector = directionVector.normalize();
	}

	public Coord3D getOrigin() {
		return origin;
	}

	public Coord3D getDirectionVector() {
		return directionVector;
	}

	public String toString() {
		return "Ray [origin=" + origin + ", directionVector=" + directionVector
				+ "]";
	}

	public Coord3D intersectionPoint(Sphere s) {
		// B = 2 * ( (Xd * (- Xc)) + (Yd * (- Yc)) + (Zd * (- Zc)) )
		
		double B = 2.0 * ((directionVector.getX() * (0.0 - s.getCenter().getX()))
				+ (directionVector.getY() * (0.0 - s.getCenter().getY()))
				+ (directionVector.getZ() * (0.0 - s.getCenter().getZ())));

		// C = (Xo - Xc) 2 + (Yo - Yc) 2 + (Zo - Zc) 2 - Sr2
		double C = Math.pow(origin.getX() - s.getCenter().getX(), 2)
				+ Math.pow(origin.getY() - s.getCenter().getY(), 2)
				+ Math.pow(origin.getZ() - s.getCenter().getZ(), 2)
				- Math.pow(s.getRadius(), 2);

		double discriminant = Math.pow(B, 2) - (4 * C);

		if (discriminant < 0.0) {
			return null;
		} else {
			// (-B - sqrt ( discriminant )) / 2
			double t1 = ((0 - B) - Math.sqrt(discriminant)) / 2.0;
			double t2 = ((0 - B) + Math.sqrt(discriminant)) / 2.0;
			if (t1 < t2) {
				return directionVector.scale(t1);
			} else {
				return directionVector.scale(t2);
			}
		}
	}
}
