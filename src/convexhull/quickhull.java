package convexhull;

import java.awt.Point;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class quickhull implements convexhullfinder {

	public static void main(String[] args) {
		List<Point2D> allPoints = new ArrayList<Point2D>();

		// test points
		allPoints.add(new Point(3, 9));
		allPoints.add(new Point(2, 4));
		allPoints.add(new Point(5, 2));
		allPoints.add(new Point(13, 6));
		allPoints.add(new Point(10, 9));
		allPoints.add(new Point(4, 7));
		allPoints.add(new Point(8, 5));
		allPoints.add(new Point(10, 2));
		allPoints.add(new Point(12, 4));
		allPoints.add(new Point(8, 1));

		quickhull test = new quickhull();
		List<Point2D> convexHull = test.computeHull(allPoints);

		System.out.println(convexHull.toString());

	}

	private List<Point2D> recursiveQuickHull(Line2D lineAB,
			List<Point2D> pointsAB) {
		// arraylist of final points
		List<Point2D> convexPoints = new ArrayList<Point2D>();

		// ///////////////divide
		Point2D currentHighest = pointsAB.get(0);
		// get highest point
		for (int i = 0; i < pointsAB.size(); i++) {
			if (lineAB.ptSegDist(pointsAB.get(i)) > lineAB
					.ptSegDist(currentHighest)) {
				currentHighest = pointsAB.get(i);
			}
		}
		Line2D lineAC = new Line2D.Double(lineAB.getP1(), currentHighest);
		Line2D lineBC = new Line2D.Double(lineAB.getP2(), currentHighest);

		// select all points left of line AC
		List<Point2D> pointsAC = new ArrayList<Point2D>();
		for (int i = 0; i < pointsAB.size(); i++) {
			if (lineAC.relativeCCW(pointsAB.get(i)) < 0) {
				pointsAC.add(pointsAB.get(i));
			}
		}

		// select all points right of line BC
		List<Point2D> pointsBC = new ArrayList<Point2D>();
		for (int i = 0; i < pointsAB.size(); i++) {
			if (lineBC.relativeCCW(pointsAB.get(i)) > 0) {
				pointsBC.add(pointsAB.get(i));
			}
		}

		// check to see if the points line is part of the convex hull
		if (pointsBC.size() == 0) {
			convexPoints.add(lineBC.getP1());
		} else {
			Line2D lineBCnew = new Line2D.Double(lineBC.getP2(), lineBC.getP1());
			List<Point2D> newPoints = recursiveQuickHull(lineBCnew, pointsBC);
			for (int i = 0; i < newPoints.size(); i++) {
				if (!(convexPoints.contains(newPoints.get(i)))) {
					convexPoints.add(newPoints.get(i));
				}
			}
		}

		if (pointsAC.size() == 0) {
			convexPoints.add(lineAC.getP2());
		} else {
			List<Point2D> newPoints = recursiveQuickHull(lineAC, pointsAC);
			for (int i = 0; i < newPoints.size(); i++) {
				if (!(convexPoints.contains(newPoints.get(i)))) {
					convexPoints.add(newPoints.get(i));
				}
			}
		}

		return convexPoints;
	}

	public List<Point2D> computeHull(List<Point2D> points) {

		// find the most left and right points
		Point2D leftPoint = new Point((int) points.get(0).getX(), (int) points
				.get(0).getY());
		Point2D rightPoint = new Point();
		for (int i = 0; i < points.size(); i++) {
			if (points.get(i).getX() < leftPoint.getX()) {
				leftPoint.setLocation(points.get(i).getX(), points.get(i)
						.getY());
			}
			if (points.get(i).getX() > rightPoint.getX()) {
				rightPoint.setLocation(points.get(i).getX(), points.get(i)
						.getY());
			}
		}
		// make a line
		Line2D firstLine = new Line2D.Double(leftPoint, rightPoint);
		// seperate the convexhull into 2 parts
		List<Point2D> aboveLine = new ArrayList<Point2D>();
		List<Point2D> belowLine = new ArrayList<Point2D>();

		// add to the top half or bottom half
		for (int i = 0; i < points.size(); i++) {
			if (firstLine.relativeCCW(points.get(i)) < 0) {
				aboveLine.add(points.get(i));
			} else if (firstLine.relativeCCW(points.get(i)) > 0) {
				belowLine.add(points.get(i));
			} else {
				belowLine.add(points.get(i));
				aboveLine.add(points.get(i));
			}
		}

		// call the recursive method to get the top half of the hull
		aboveLine = recursiveQuickHull(firstLine, aboveLine);
		// call again to get the bottom half
		Line2D reversedFirstLine = new Line2D.Double(firstLine.getP2(),
				firstLine.getP1());
		belowLine = recursiveQuickHull(reversedFirstLine, belowLine);

		// put the two halves together
		List<Point2D> fullHull = new ArrayList<Point2D>();
		fullHull.addAll(aboveLine);
		fullHull.addAll(belowLine);

		return fullHull;
	}

}
