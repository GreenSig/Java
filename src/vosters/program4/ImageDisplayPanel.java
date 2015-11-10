package vosters.program4;

import java.awt.*;
import javax.swing.*;
import java.awt.image.*;

@SuppressWarnings("serial")
public class ImageDisplayPanel extends JPanel {
	private BufferedImage theImage;

	public ImageDisplayPanel(BufferedImage theImage) {
		// Save the instance variable
		this.theImage = theImage;
		// Make a JFrame to put ourself into
		// It will be slightly bigger than us to
		// make up for the frame border
		JFrame theFrame = new JFrame("Ray Tracer");
		theFrame.setSize(theImage.getWidth() + 8, theImage.getHeight() + 34);
		// Add an event listener to the JFrame so it shuts down the
		// program when the use presses the X box
		theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Add ourselves to the JFrame
		theFrame.setContentPane(this);
		// Center the JFrame in the screen
		theFrame.setLocationRelativeTo(null);
		// Make the JFrame visible
		theFrame.setVisible(true);
	}

	public void paintComponent(Graphics g) {
		// Draw any other components that happen to be on the panel
		super.paintComponent(g);
		// Draw the image on the panel
		g.drawImage(theImage, 0, 0, null);
	}
}