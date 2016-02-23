package vosters.Convexhull;

import java.awt.Point;
import java.awt.Polygon;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class mergehull implements convexhullfinder {

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

		mergehull test = new mergehull();
		List<Point2D> convexHull = test.computeHull(allPoints);

		System.out.println(convexHull.toString());

	}

	@SuppressWarnings("unchecked")
	public List<Point2D> computeHull(List<Point2D> points) {
		// sort points
		Collections.sort(points, new PointComp());
		System.out.println();
		points = recursiveMergeHull(points);

		return points;

	}

	private List<Point2D> recursiveMergeHull(List<Point2D> points) {

		// divide points into halves
		List<Point2D> rightPoints = new ArrayList<Point2D>(points.subList(
				points.size() / 2, points.size()));
		List<Point2D> leftPoints = new ArrayList<Point2D>(points.subList(0,
				points.size() / 2));

		if (rightPoints.size() > 3) {
			rightPoints = recursiveMergeHull(rightPoints);
		}
		if (leftPoints.size() > 3) {
			leftPoints = recursiveMergeHull(leftPoints);
		}

		Point2D leftPoint = new Point(300, 0);
		Point2D rightPoint = new Point(-300, 0);

		ArrayList<Point2D> rightList = new ArrayList<Point2D>();
		ArrayList<Point2D> leftList = new ArrayList<Point2D>();
		int rightIndex = 0;
		int leftIndex = 0;

		// finds the most left point of the right convexhull
		for (int i = 0; i < rightPoints.size(); i++) {
			if (rightPoints.get(i).getX() < leftPoint.getX()) {
				leftPoint = rightPoints.get(i);
				rightIndex = i;
			}
		}

		// if the index is at position 0, put the last into the first, and the
		// index as pos 1
		if (rightIndex == 0) {
			rightList.add(rightPoints.get(rightPoints.size() - 1));
			rightList.addAll(rightPoints.subList(0, rightPoints.size() - 1));

		} else {
			// if the index is not pos 0, add index - 1 to pos 0, and the index
			// to pos 1
			rightList.add(rightPoints.get(rightIndex - 1));
			rightList.add(rightPoints.get(rightIndex));
			// if there is more points after the index
			if (!(rightPoints.get(rightPoints.size() - 1).equals(rightPoints
					.get(rightIndex)))) {
				// add all the points from the index to the end
				rightList.addAll(rightPoints.subList(rightIndex + 1,
						rightPoints.size()));
			}
			// the index can't be at pos 0, so
			// add all the points from the start to the index - 1
			rightList.addAll(rightPoints.subList(0, rightIndex - 1));

		}
		// checks to see if it was a 2 point list
		if (rightList.size() == 2) {
			rightList.add(rightList.get(0));
		}

		// finds the most right point of the left convex hull
		for (int i = 0; i < leftPoints.size(); i++) {
			if (leftPoints.get(i).getX() > rightPoint.getX()) {
				rightPoint = leftPoints.get(i);
				leftIndex = i;
			}
		}

		if (leftIndex == 0) {
			leftList.add(leftPoints.get(leftPoints.size() - 1));
			leftList.addAll(leftPoints.subList(0, leftPoints.size() - 1));
		} else {
			leftList.add(leftPoints.get(leftIndex - 1));
			leftList.add(leftPoints.get(leftIndex));
			if (!(leftPoints.get(leftPoints.size() - 1).equals(leftPoints
					.get(leftIndex)))) {
				leftList.addAll(leftPoints.subList(leftIndex + 1, leftPoints
						.size()));
			}
			leftList.addAll(leftPoints.subList(0, leftIndex - 1));
		}
		if (leftList.size() == 2) {
			leftList.add(leftList.get(0));
		}

		ArrayList<Point2D> rightListTop = new ArrayList<Point2D>(rightList);
		ArrayList<Point2D> leftListTop = new ArrayList<Point2D>(leftList);

		// ///////////////Get top tangent
		Line2D topTangent = new Line2D.Double(leftListTop.get(1), rightListTop
				.get(1));
		// checks to see that bottomTangent is not the lower tangent to both
		// left and right

		while (topTangent.relativeCCW(rightListTop.get(2)) < 0
				|| topTangent.relativeCCW(rightListTop.get(0)) < 0
				|| topTangent.relativeCCW(leftListTop.get(2)) < 0
				|| topTangent.relativeCCW(leftListTop.get(0)) < 0) {

			// Top tangent of the left half
			while (topTangent.relativeCCW(leftListTop.get(2)) < 0
					|| topTangent.relativeCCW(leftListTop.get(0)) < 0) {
				// moves the last one to the first place
				leftListTop.add(0, leftListTop.get(leftListTop.size() - 1));
				leftListTop.remove(leftListTop.size() - 1);

				// makes a new Line
				topTangent.setLine(leftListTop.get(1), topTangent.getP2());
			}

			// Top tangent of the right half
			while (topTangent.relativeCCW(rightListTop.get(2)) < 0
					|| topTangent.relativeCCW(rightListTop.get(0)) < 0) {
				// moves the first one to the end
				rightListTop.add(rightListTop.get(0));
				rightListTop.remove(0);

				// makes a new Line
				topTangent.setLine(topTangent.getP1(), rightListTop.get(1));
			}
		}

		// ///////////////Get bottom tangent
		Line2D bottomTangent = new Line2D.Double(leftList.get(1), rightList
				.get(1));
		// checks to see that bottomTangent is not the lower tangent to both
		// left and right

		while (bottomTangent.relativeCCW(rightList.get(2)) > 0
				|| bottomTangent.relativeCCW(rightList.get(0)) > 0
				|| bottomTangent.relativeCCW(leftList.get(2)) > 0
				|| bottomTangent.relativeCCW(leftList.get(0)) > 0) {

			// Bottom tangent of the left half
			while (bottomTangent.relativeCCW(leftList.get(2)) > 0
					|| bottomTangent.relativeCCW(leftList.get(0)) > 0) {
				// moves the first one to the end
				leftList.add(leftList.get(0));
				leftList.remove(0);

				// makes a new Line
				bottomTangent.setLine(leftList.get(1), bottomTangent.getP2());
			}
			// Bottom tangent of the right half
			while (bottomTangent.relativeCCW(rightList.get(2)) > 0
					|| bottomTangent.relativeCCW(rightList.get(0)) > 0) {

				// moves the last one to the first place
				rightList.add(0, rightList.get(rightList.size() - 1));
				rightList.remove(rightList.size() - 1);

				// makes a new Line
				bottomTangent.setLine(bottomTangent.getP1(), rightList.get(1));
			}
		}

		// creates a new arrayList for convex points
		List<Point2D> convexPoints = new ArrayList<Point2D>();

		// removes non-convex points from the left
		int[] xs = {

		(int) topTangent.getP1().getX(), (int) topTangent.getP2().getX(),
				(int) bottomTangent.getP2().getX(),
				(int) bottomTangent.getP1().getX() };

		int[] ys = { (int) topTangent.getP1().getY(),
				(int) topTangent.getP2().getY(),
				(int) bottomTangent.getP2().getY(),
				(int) bottomTangent.getP1().getY() };

		// if the points are in the polygon, they are not convex
		Polygon polygon = new Polygon(xs, ys, 4);
		for (int i = 0; i < leftPoints.size(); i++) {

			
			if (topTangent.getP1().equals(leftPoints.get(i))
					|| bottomTangent.getP1().equals(leftPoints.get(i))) {
				if (!(convexPoints.contains(leftPoints.get(i)))) {
					convexPoints.add(leftPoints.get(i));
				}
			} else {

				if (!(polygon.contains(leftPoints.get(i)))) {
					if (!(convexPoints.contains(leftPoints.get(i)))) {
						convexPoints.add(leftPoints.get(i));
					}
				}
			}

		}
		for (int i = 0; i < rightPoints.size(); i++) {

			if (topTangent.getP2().equals(rightPoints.get(i))
					|| bottomTangent.getP2().equals(rightPoints.get(i))) {
				if (!(convexPoints.contains(rightPoints.get(i)))) {
					convexPoints.add(rightPoints.get(i));
				}
			} else {

				if (!(polygon.contains(rightPoints.get(i)))) {
					if (!(convexPoints.contains(rightPoints.get(i)))) {
						convexPoints.add(rightPoints.get(i));
					}
				}
			}

		}
		return organizer(points, convexPoints);

	}

	public static List<Point2D> organizer(List<Point2D> allPoints,
			List<Point2D> convexPoints) {
		// sorts in lowest X value -> highest X value
		for (int i = allPoints.size() - 1; i >= 0; i--) {
			if (!(convexPoints.contains(allPoints.get(i)))) {
				allPoints.remove(i);
			}
		}

		// Take the highest and lowest x value and split the points up
		// based on Above/below the line
		List<Point2D> topHalf = new ArrayList<Point2D>();
		List<Point2D> bottomHalf = new ArrayList<Point2D>();

		Line2D midLine = new Line2D.Double(allPoints.get(0), allPoints
				.get(allPoints.size() - 1));

		for (int i = 0; i < allPoints.size(); i++) {
			if (midLine.relativeCCW(allPoints.get(i)) < 0) {
				topHalf.add(allPoints.get(i));
			} else if (midLine.relativeCCW(allPoints.get(i)) > 0) {
				bottomHalf.add(allPoints.get(i));
			} else if (allPoints.get(i).equals(allPoints.get(0))) {
				topHalf.add(allPoints.get(i));
			} else if ((allPoints.get(i).equals(allPoints
					.get(allPoints.size() - 1)))) {
				bottomHalf.add(allPoints.get(i));
			}

		}

		// add top half of convex to bottom half (excluding the duplicate point)
		List<Point2D> returnInfo = new ArrayList<Point2D>();

		// add the top half in reverse order (to make it CCW)
		for (int i = topHalf.size() - 1; i >= 0; i--) {
			returnInfo.add(topHalf.get(i));
		}

		returnInfo.addAll(bottomHalf);
		return returnInfo;

	}

	// class for Sorting arraylist of Points
	@SuppressWarnings("unchecked")
	public class PointComp implements Comparator {
		public int compare(Object o1, Object o2) {
			Point2D p1 = (Point2D) o1;
			Point2D p2 = (Point2D) o2;
			Double x1d = new Double(((Point2D) p1).getX());
			Double x2d = new Double(((Point2D) p2).getX());
			return x1d.compareTo(x2d);
		}
	}

}
