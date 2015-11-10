package vosters.program4;

import java.awt.image.BufferedImage;

import org.omg.CORBA.FixedHolder;

public class Camera {
	// instance variables
	private double focalLength;
	private int filmResolutionX;
	private int filmResolutionY;

	// constructors
	public Camera() {
		this.focalLength = 1;
		this.filmResolutionX = 500;
		this.filmResolutionY = 500;
	}

	public Camera(double focalLength, int filmResolutionX, int filmResolutionY) {
		this.focalLength = focalLength;
		this.filmResolutionX = filmResolutionX;
		this.filmResolutionY = filmResolutionY;
	}

	public BufferedImage takePicture(Sphere s) {
		BufferedImage newImage = new BufferedImage(filmResolutionX,
				filmResolutionY, BufferedImage.TYPE_INT_RGB);

		double planeX, planeY, planeZ;
		for (int x = 0; x < filmResolutionX; x++) {
			for (int y = 0; y < filmResolutionX; y++) {
				planeX = -.5 + x / (double) filmResolutionX;
				planeY = .5 - y / (double) filmResolutionY;
				planeZ = 1.0;

				Ray r = new Ray(new Coord3D(0.0, 0.0, 0.0), new Coord3D(planeX,
						planeY, planeZ));
				Coord3D intersectionpoint = r.intersectionPoint(s);
				if (intersectionpoint == null) {
					newImage.setRGB(x, y, 0);
				} else {
					newImage.setRGB(x, y, 0xD72448);
				}

				if (x == 115 && y == 223) {
					System.out.println();
					r.intersectionPoint(s);
				}
			}
		}
		return newImage;

	}

}
