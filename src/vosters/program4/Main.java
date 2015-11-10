package vosters.program4;

import java.awt.image.BufferedImage;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Camera c = new Camera();
		Sphere s = new Sphere(new Coord3D(-4.0, 1.0, 30.0), 4.0);
		
		BufferedImage picture = c.takePicture(s);
		
		
		new ImageDisplayPanel(picture);
		
		//tests for Coord3D and Sphere
		Coord3D loc = new Coord3D(1.0, 1.0, 1.0);
		Sphere myS = new Sphere(loc, 2);
		System.out.println(myS);
		
		Coord3D loc2 = new Coord3D(2.0, 2.0, 2.0);
		//loc = loc.add(loc2);
		System.out.println("1+2=" + loc.add(loc2));
		//loc = loc.subtract(loc2);
		System.out.println("1-2=" + loc.subtract(loc2));
		System.out.println("2*2=" +loc2.scale(2));
		Coord3D loc3 = new Coord3D(1.0, 2.0, 3.0);
		
		//loc3 = loc3.normalize();
		System.out.println("normalize:" + loc3.normalize());
		System.out.println("dot prod:" + loc3.dot(loc3)); //should be 14
		System.out.println("distance between:" + loc.distanceBetween(new Coord3D(1.0, 1.0, 5.0)));
		
	}

}
