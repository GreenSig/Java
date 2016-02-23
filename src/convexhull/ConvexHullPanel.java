package convexhull;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class ConvexHullPanel extends JPanel {

	List<Point2D> points = new ArrayList<Point2D>();
	List<Dots> dots = new ArrayList<Dots>();
	List<Line2D> lines = new ArrayList<Line2D>();
	List<hullLine> hullLines = new ArrayList<hullLine>();
	public ConvexHullPanel(){
		super();
		setBackground(Color.white);
	}
	
	public void addPoint(Point2D p){
		points.add(p);
		Dots d = new Dots(p);
		dots.add(d);
	}
	public void addLine(Line2D l){
		lines.add(l);
		hullLine hl = new hullLine(l);
		hullLines.add(hl);
	}
	public void addListPoint(ArrayList<Point2D> p){
		dots.clear();
		points = p;
		for(int i =0;i<p.size(); i++){
			Dots d = new Dots(p.get(i));
			dots.add(d);
		}
	}
	public void addLineList(ArrayList<Line2D> l){
		lines = l;
		hullLines.clear();
		
		for(int i =0;i<l.size(); i++){
			hullLine hl = new hullLine(l.get(i));
			hullLines.add(hl);
		}
	}
	protected void paintComponent(Graphics g){
		
		super.paintComponent(g);
		
		for(Dots obj: dots){
			obj.draw(g);
		}
		for(hullLine obj : hullLines){
			obj.draw(g);
		}
		repaint();
	}
	public class Dots{
		int x;
		int y;
		public Dots(Point2D a){
		
		this.x=(int)a.getX();
		this.y=(int)a.getY();
	}
		public void draw(Graphics g){
			g.setColor(Color.BLACK);
			g.fillRect(x-2, y-2, 5,5);
		}
}

	public class hullLine {
		public Line2D line;
		public hullLine(Line2D l){
			this.line = l;
		}
		public void draw(Graphics g){
			g.setColor(Color.RED);
			g.drawLine((int)line.getX1(),(int)line.getY1(), (int)line.getX2(),(int)line.getY2());
		}

	}

}
