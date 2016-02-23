package vosters.Convexhull;

//Rewritten based on visual editor generated code

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.*;
import java.awt.geom.*;

import javax.swing.*;
import java.util.*;

@SuppressWarnings("serial")
public class convexHullGUI extends JFrame {
	private JPanel convexhullfinderPane = null;
	private JPanel jFrameContentPane = null;
	private JLabel pointsLabel = null;
	private JTextField numPointsField = null;
	private JButton generateHullButton = null;
	private JButton generatePointsButton = null;
	private JRadioButton mergeHullButton = null;
	private JRadioButton quickHullButton = null;

	private ConvexHullPanel convexHullPanel = null;

	// My extra instance variables
	private ButtonGroup hullChoice;
	private ArrayList<Point2D> points;
	private convexhullfinder theconvexhullfinder;


	//Action Listeners that you need to complete

	// When the mouse is pressed on the panel
	// Add a single point to the screen
	public void convexHullPanel_MouseReleased(MouseEvent e) {
		Point p = new Point(e.getPoint());	
		points.add(p);
		convexHullPanel.addPoint(p);


	}

	// When the generatePoints button is pressed
	// Adds a number of points to the screen
	// You need to fill in the details
	public void generatePointsButton_ActionPerformed(java.awt.event.ActionEvent actionEvent) {
		String pointsToBeGenerated = numPointsField.getText();

		if(pointsToBeGenerated.length()<= 0){
			JOptionPane.showMessageDialog(null, "please enter a positive number");
		}else{
			points.clear();
		
			int a = Integer.parseInt(pointsToBeGenerated);

			for(int i = 0; i <a; i++){
				int x = (int)(270 * Math.random() + 15 );
				int y = (int)(270 * Math.random() + 15 );		
				Point2D rand = new Point2D.Double(x,y);
				points.add(rand);
			}
			convexHullPanel.addListPoint(points);
		}

		// Make the Vector of Point2Ds
		// Display them on the panel
	}

	// When the mergeHull button is pressed
	// Just set the convex hull finder selected
	public void mergeHullButton_ActionPerformed(ActionEvent actionEvent) {
		mergeHullButton.setSelected(true);
		quickHullButton.setSelected(false);

	}

	// When the quickHull button is pressed
	// Just set the convex hull finder selected
	public void quickHullButton_ActionPerformed(ActionEvent actionEvent) {
		mergeHullButton.setSelected(false);
		quickHullButton.setSelected(true);
	}

	// When the generateHull button is pressed
	// Generate the actual hull using the selected hull finder and display the results
	public void generateHullButton_ActionEvents() {
		List<Point2D> hullPoints = new ArrayList<Point2D>();
		ArrayList<Line2D> l = new ArrayList<Line2D>();

		if(mergeHullButton.isSelected()){
			theconvexhullfinder = new mergehull();
			hullPoints = theconvexhullfinder.computeHull(points);
			//make the lines for the hull
			for(int i = 0; i<hullPoints.size(); i++){
				Point2D a,b;
				a = hullPoints.get(i);
				//if i is the last thing in the list make b = to the first thing in the list to complete the hull
				if(i == hullPoints.size()-1){
					b = hullPoints.get(0);				
				}else{
					b= hullPoints.get(i+1);
				}
				
				Line2D temp = new Line2D.Double(a,b);
				l.add(temp);
				
			}
			convexHullPanel.addLineList(l);
			
		}else if(quickHullButton.isSelected()){
			theconvexhullfinder  = new quickhull();
			hullPoints = theconvexhullfinder.computeHull(points);
			//make the lines for the hull
			for(int i = 0; i<hullPoints.size(); i++){
				Point2D a,b;
				a = hullPoints.get(i);
				//if i is the last thing in the list make b = to the first thing in the list to complete the hull
				if(i == hullPoints.size()-1){
					b = hullPoints.get(0);				
				}else{
					b= hullPoints.get(i+1);
				}
				
				Line2D temp = new Line2D.Double(a,b);
				l.add(temp);
				
			}
			convexHullPanel.addLineList(l);
			
		}
		// Generate the hull

		// Show the results on the screen
	}


	public convexHullGUI() {
		points = new ArrayList<Point2D>();

		//Setup frame
		setName("convexhullfinder");
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setSize(490, 360);
		setTitle("convexhullfinder");

		//Setup JFrame Content Pane
		jFrameContentPane = new JPanel();
		jFrameContentPane.setName("JFrameContentPane");
		jFrameContentPane.setLayout(new BorderLayout());
		setContentPane(jFrameContentPane);

		//Setup Generate Points Button
		generatePointsButton = new JButton();
		generatePointsButton.setName("GeneratePointsButton");
		generatePointsButton.setText("Generate Points");
		generatePointsButton.setBounds(19, 90, 129, 30);

		//Setup Generate Hull Button
		generateHullButton = new JButton();
		generateHullButton.setName("GenerateHullButton");
		generateHullButton.setText("Generate Hull");
		generateHullButton.setBounds(20, 265, 128, 25);

		//Setup Convex Hull Panel
		//You will need to create the ConvexHullPanel Class
		convexHullPanel = new ConvexHullPanel();
		convexHullPanel.setName("ConvexHullPanel");
		convexHullPanel.setBackground(Color.white);
		convexHullPanel.setBounds(165, 18, 300, 300);

		//Setup Points Label
		pointsLabel = new JLabel();
		pointsLabel.setName("pointsLabel");
		pointsLabel.setText("# of points");
		pointsLabel.setBounds(49, 35, 65, 14);

		//Setup Number of Points Field
		numPointsField = new JTextField();
		numPointsField.setName("numPointsField");
		numPointsField.setText("30");
		numPointsField.setBounds(32, 55, 99, 20);
		numPointsField.setHorizontalAlignment(JTextField.RIGHT);

		//Setup Merge Hull Button
		mergeHullButton = new javax.swing.JRadioButton();
		mergeHullButton.setName("MergeHullButton");
		mergeHullButton.setText("MergeHull");
		mergeHullButton.setBounds(40, 229, 108, 22);

		//Setup Quick Hull Button
		quickHullButton = new javax.swing.JRadioButton();
		quickHullButton.setName("QuickHullButton");
		quickHullButton.setSelected(true);
		quickHullButton.setText("QuickHull");
		quickHullButton.setBounds(40, 196, 108, 22);
		theconvexhullfinder = new quickhull();  //Do this b/c radio button is defaulted to quick hull

		//Setup Convex Hull Finder Pane
		convexhullfinderPane = new JPanel();
		convexhullfinderPane.setName("convexhullfinderPane");
		convexhullfinderPane.setLayout(null);
		convexhullfinderPane.add(numPointsField, numPointsField.getName());
		convexhullfinderPane.add(pointsLabel, pointsLabel.getName());
		convexhullfinderPane.add(generatePointsButton, generatePointsButton.getName());
		convexhullfinderPane.add(generateHullButton, generateHullButton.getName());
		convexhullfinderPane.add(quickHullButton, quickHullButton.getName());
		convexhullfinderPane.add(mergeHullButton, mergeHullButton.getName());
		convexhullfinderPane.add(convexHullPanel, convexHullPanel.getName());
		jFrameContentPane.add(convexhullfinderPane, "Center");

		hullChoice = new ButtonGroup();
		hullChoice.add(quickHullButton);
		hullChoice.add(mergeHullButton);

		//Listeners
		generatePointsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				generatePointsButton_ActionPerformed(e);
			};
		});
		generateHullButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				generateHullButton_ActionEvents();
			};
		});
		quickHullButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quickHullButton_ActionPerformed(e);
			};
		});
		mergeHullButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mergeHullButton_ActionPerformed(e);
			};
		});
		convexHullPanel.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				convexHullPanel_MouseReleased(e);
			};
		});
	}	

	public static void main(java.lang.String[] args) {
		/* Set native look and feel */
		//UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		/* Create the frame */
		convexHullGUI aconvexHullGUI = new convexHullGUI();
		/* Add a windowListener for the windowClosedEvent */
		aconvexHullGUI.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosed(java.awt.event.WindowEvent e) {
				System.exit(0);
			};
		});	
		aconvexHullGUI.setVisible(true);
	}

}