package dnaalignment;

import java.awt.BorderLayout;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

//Rewritten based on visual editor generated code

public class AlignmentGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private List<JLabel> labels;
	private List<JTextField> fields;
	
	private JPanel jFrameContentPane = null;
	private JPanel alignmentGUIPane = null;
	private JButton alignButton = null;
	private JLabel inputLabel = null;
	private JLabel alignLabel = null;
	private JTextField inputField1 = null;
	private JTextField inputField2 = null;
	private JTextField alignField1 = null;
	private JTextField alignField2 = null;
	private JLabel costLabel = null;
	private JLabel rowALabel = null;
	private JLabel rowCLabel = null;
	private JLabel rowGLabel = null;
	private JLabel rowTLabel = null;
	private JLabel row_Label = null;
	private JLabel colALabel = null;
	private JLabel colCLabel = null;
	private JLabel colGLabel = null;
	private JLabel colTLabel = null;
	private JLabel col_Label = null;
	private JTextField AA = null;
	private JTextField AC = null;
	private JTextField AG = null;
	private JTextField AS = null;
	private JTextField AT = null;
	private JTextField CA = null;
	private JTextField CC = null;
	private JTextField CG = null;
	private JTextField CS = null;
	private JTextField CT = null;
	private JTextField GA = null;
	private JTextField GC = null;
	private JTextField GG = null;
	private JTextField GS = null;
	private JTextField GT = null;
	private JTextField SA = null;
	private JTextField SC = null;
	private JTextField SG = null;
	private JTextField ST = null;
	private JTextField TA = null;
	private JTextField TC = null;
	private JTextField TG = null;
	private JTextField TS = null;
	private JTextField TT = null;

	public void alignButton_ActionPerformed(ActionEvent actionEvent) {

		// Grab the costMatching information and place it into a Map
		// I can't have "+2" as a String as this doesn't
		// form a new Integer properly
		Map<String, Integer> matchCost = new HashMap<String, Integer>();

		matchCost.put("AA", new Integer(AA.getText()));
		matchCost.put("AC", new Integer(AC.getText()));
		matchCost.put("AG", new Integer(AG.getText()));
		matchCost.put("AT", new Integer(AT.getText()));
		matchCost.put("A-", new Integer(AS.getText()));

		matchCost.put("CA", new Integer(CA.getText()));
		matchCost.put("CC", new Integer(CC.getText()));
		matchCost.put("CG", new Integer(CG.getText()));
		matchCost.put("CT", new Integer(CT.getText()));
		matchCost.put("C-", new Integer(CS.getText()));

		matchCost.put("GA", new Integer(GA.getText()));
		matchCost.put("GC", new Integer(GC.getText()));
		matchCost.put("GG", new Integer(GG.getText()));
		matchCost.put("GT", new Integer(GT.getText()));
		matchCost.put("G-", new Integer(GS.getText()));

		matchCost.put("TA", new Integer(TA.getText()));
		matchCost.put("TC", new Integer(TC.getText()));
		matchCost.put("TG", new Integer(TG.getText()));
		matchCost.put("TT", new Integer(TT.getText()));
		matchCost.put("T-", new Integer(TS.getText()));

		matchCost.put("-A", new Integer(SA.getText()));
		matchCost.put("-C", new Integer(SC.getText()));
		matchCost.put("-G", new Integer(SG.getText()));
		matchCost.put("-T", new Integer(ST.getText()));


		// Create a new DNA matcher with the specified cost matrix
		DNAMatcher matcher = new DNAMatcher(matchCost);

		// Ask the DNA matcher to find a local alignment
		LocalAlignment alignment = matcher.findLocalAlignment(inputField1.getText(), inputField2.getText());

		alignment.showAlignment(alignField1, alignField2);
	}



	public AlignmentGUI() {
		super();
		
		//Setup frame
		setName("AlignmentGUI");
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setSize(507, 289);
		setTitle("Local Alignment");

		//Setup JFrame Content Pane
		jFrameContentPane = new JPanel();
		jFrameContentPane.setName("JFrameContentPane");
		jFrameContentPane.setLayout(new BorderLayout());
		setContentPane(jFrameContentPane);

		//Lists to store parts of frame
		labels = new ArrayList<JLabel>();
		fields = new ArrayList<JTextField>();

		//Setup Align Button
		alignButton = new JButton();
		alignButton.setName("AlignButton");
		alignButton.setText("Align");
		alignButton.setBounds(113, 127, 85, 25);

		//Setup Table of costs
		//Label
		costLabel = new javax.swing.JLabel();
		costLabel.setName("costLabel");
		costLabel.setText("Match Costs");
		costLabel.setBounds(373, 24, 80, 14);
		labels.add(costLabel);
		SetupCostsTable();

		//Setup Input Strings
		//Label
		inputLabel = new javax.swing.JLabel();
		inputLabel.setName("inputLabel");
		inputLabel.setText("Input Strings");
		inputLabel.setBounds(117, 19, 95, 14);
		labels.add(inputLabel);
		//box 1
		inputField1 = new javax.swing.JTextField();
		inputField1.setName("inputField1");
		inputField1.setFont(new java.awt.Font("monospaced", 0, 12));
		inputField1.setText("AGATGGAATGATTAG");
		inputField1.setBounds(31, 41, 260, 20);
		fields.add(inputField1);
		//box 2
		inputField2 = new javax.swing.JTextField();
		inputField2.setName("inputField2");
		inputField2.setFont(new java.awt.Font("monospaced", 0, 12));
		inputField2.setText("CGACTATT");
		inputField2.setBounds(31, 63, 260, 20);
		fields.add(inputField2);

		//Setup Aligned Strings
		//label
		alignLabel = new javax.swing.JLabel();
		alignLabel.setName("alignLabel");
		alignLabel.setText("Aligned Strings");
		alignLabel.setBounds(113, 238, 87, 14);
		labels.add(alignLabel);
		//box 1
		alignField1 = new javax.swing.JTextField();
		alignField1.setName("alignField1");
		alignField1.setFont(new java.awt.Font("monospaced", 0, 12));
		alignField1.setBounds(30, 182, 260, 20);
		alignField1.setEditable(false);
		fields.add(alignField1);
		//box 2
		alignField2 = new javax.swing.JTextField();
		alignField2.setName("alignField2");
		alignField2.setFont(new java.awt.Font("monospaced", 0, 12));
		alignField2.setBounds(30, 213, 260, 20);
		alignField2.setEditable(false);
		fields.add(alignField2);
		
		//Setup Alignment Pane
		alignmentGUIPane = new JPanel();
		alignmentGUIPane.setName("AlignmentGUIPane");
		alignmentGUIPane.setLayout(null);
		alignmentGUIPane.add(alignButton, alignButton.getName());
		for(int i = 0; i < labels.size(); i++)
			alignmentGUIPane.add(labels.get(i), labels.get(i).getName());
		for(int i = 0; i < fields.size(); i++)
			alignmentGUIPane.add(fields.get(i), fields.get(i).getName());
		jFrameContentPane.add(alignmentGUIPane, "Center");

		//Listeners
		alignButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alignButton_ActionPerformed(e);
			};
		});
		
		CA.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {};
			public void keyTyped(KeyEvent e) {};
			public void keyReleased(KeyEvent e) {
				AC.setText(CA.getText());
			}
		});
		GA.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {};
			public void keyTyped(KeyEvent e) {};
			public void keyReleased(KeyEvent e) {
				AG.setText(GA.getText());
			}
		});
		TA.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {};
			public void keyTyped(KeyEvent e) {};
			public void keyReleased(KeyEvent e) {
				AT.setText(TA.getText());
			}
		});
		SA.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {};
			public void keyTyped(KeyEvent e) {};
			public void keyReleased(KeyEvent e) {
				AS.setText(SA.getText());
			}
		});
		GC.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {};
			public void keyTyped(KeyEvent e) {};
			public void keyReleased(KeyEvent e) {
				CG.setText(GC.getText());
			}
		});
		TC.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {};
			public void keyTyped(KeyEvent e) {};
			public void keyReleased(KeyEvent e) {
				CT.setText(TC.getText());
			}
		});
		SC.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {};
			public void keyTyped(KeyEvent e) {};
			public void keyReleased(KeyEvent e) {
				CS.setText(SC.getText());
			}
		});
		TG.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {};
			public void keyTyped(KeyEvent e) {};
			public void keyReleased(KeyEvent e) {
				GT.setText(TG.getText());
			}
		});
		SG.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {};
			public void keyTyped(KeyEvent e) {};
			public void keyReleased(KeyEvent e) {
				GS.setText(SG.getText());
			}
		});
		ST.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {};
			public void keyTyped(KeyEvent e) {};
			public void keyReleased(KeyEvent e) {
				TS.setText(ST.getText());
			}
		});
	}

	private void SetupCostsTable() {
		//AA
		AA = new JTextField();
		AA.setName("AA");
		AA.setText("2");
		AA.setBounds(358, 64, 21, 20);
		AA.setHorizontalAlignment(JTextField.RIGHT);
		fields.add(AA);
		//CA
		CA = new JTextField();
		CA.setName("CA");
		CA.setText("-2");
		CA.setBounds(383, 64, 21, 20);
		CA.setHorizontalAlignment(JTextField.RIGHT);
		fields.add(CA);
		//GA
		GA = new JTextField();
		GA.setName("GA");
		GA.setText("-2");
		GA.setBounds(406, 64, 21, 20);
		GA.setHorizontalAlignment(JTextField.RIGHT);
		fields.add(GA);
		//TA
		TA = new JTextField();
		TA.setName("TA");
		TA.setText("-2");
		TA.setBounds(430, 64, 21, 20);
		TA.setHorizontalAlignment(JTextField.RIGHT);
		fields.add(TA);
		//SA
		SA = new JTextField();
		SA.setName("SA");
		SA.setText("-1");
		SA.setBounds(454, 64, 21, 20);
		SA.setHorizontalAlignment(JTextField.RIGHT);
		fields.add(SA);
		//AC
		AC = new JTextField();
		AC.setName("AC");
		AC.setText("-2");
		AC.setBounds(358, 88, 21, 20);
		AC.setEnabled(false);
		AC.setHorizontalAlignment(JTextField.RIGHT);
		fields.add(AC);
		//CC
		CC = new JTextField();
		CC.setName("CC");
		CC.setText("4");
		CC.setBounds(383, 88, 21, 20);
		CC.setHorizontalAlignment(JTextField.RIGHT);
		fields.add(CC);
		//GC
		GC = new JTextField();
		GC.setName("GC");
		GC.setText("-1");
		GC.setBounds(406, 88, 21, 20);
		GC.setHorizontalAlignment(JTextField.RIGHT);
		fields.add(GC);
		//TC
		TC = new JTextField();
		TC.setName("TC");
		TC.setText("-3");
		TC.setBounds(430, 88, 21, 20);
		TC.setHorizontalAlignment(JTextField.RIGHT);
		fields.add(TC);
		//SC
		SC = new JTextField();
		SC.setName("SC");
		SC.setText("-1");
		SC.setBounds(454, 88, 21, 20);
		SC.setHorizontalAlignment(JTextField.RIGHT);
		fields.add(SC);
		//AG
		AG = new JTextField();
		AG.setName("AG");
		AG.setText("-2");
		AG.setBounds(358, 111, 21, 20);
		AG.setEnabled(false);
		AG.setHorizontalAlignment(JTextField.RIGHT);
		fields.add(AG);
		//CG
		CG = new JTextField();
		CG.setName("CG");
		CG.setText("-1");
		CG.setBounds(383, 111, 21, 20);
		CG.setEnabled(false);
		CG.setHorizontalAlignment(JTextField.RIGHT);
		fields.add(CG);
		//GG
		GG = new JTextField();
		GG.setName("GG");
		GG.setText("3");
		GG.setBounds(406, 111, 21, 20);
		GG.setHorizontalAlignment(JTextField.RIGHT);
		fields.add(GG);
		//TG
		TG = new JTextField();
		TG.setName("TG");
		TG.setText("-3");
		TG.setBounds(430, 111, 21, 20);
		TG.setHorizontalAlignment(JTextField.RIGHT);
		fields.add(TG);
		//SG
		SG = new JTextField();
		SG.setName("SG");
		SG.setText("-1");
		SG.setBounds(454, 111, 21, 20);
		SG.setHorizontalAlignment(JTextField.RIGHT);
		fields.add(SG);
		//AT
		AT = new JTextField();
		AT.setName("AT");
		AT.setText("-2");
		AT.setBounds(358, 134, 21, 20);
		AT.setEnabled(false);
		AT.setHorizontalAlignment(JTextField.RIGHT);
		fields.add(AT);
		//CT
		CT = new JTextField();
		CT.setName("CT");
		CT.setText("-3");
		CT.setBounds(383, 134, 21, 20);
		CT.setEnabled(false);
		CT.setHorizontalAlignment(JTextField.RIGHT);
		fields.add(CT);
		//GT
		GT = new JTextField();
		GT.setName("GT");
		GT.setText("-3");
		GT.setBounds(406, 134, 21, 20);
		GT.setEnabled(false);
		GT.setHorizontalAlignment(JTextField.RIGHT);
		fields.add(GT);
		//TT
		TT = new JTextField();
		TT.setName("TT");
		TT.setText("2");
		TT.setBounds(430, 134, 21, 20);
		TT.setHorizontalAlignment(JTextField.RIGHT);
		fields.add(TT);
		//ST
		ST = new JTextField();
		ST.setName("ST");
		ST.setText("-1");
		ST.setBounds(454, 134, 21, 20);
		ST.setHorizontalAlignment(JTextField.RIGHT);
		fields.add(ST);
		//AS
		AS = new JTextField();
		AS.setName("AS");
		AS.setText("-1");
		AS.setBounds(357, 158, 21, 20);
		AS.setEnabled(false);
		AS.setHorizontalAlignment(JTextField.RIGHT);
		fields.add(AS);
		//CS
		CS = new JTextField();
		CS.setName("CS");
		CS.setText("-1");
		CS.setBounds(382, 158, 21, 20);
		CS.setEnabled(false);
		CS.setHorizontalAlignment(JTextField.RIGHT);
		fields.add(CS);
		//GS
		GS = new JTextField();
		GS.setName("GS");
		GS.setText("-1");
		GS.setBounds(405, 158, 21, 20);
		GS.setEnabled(false);
		GS.setHorizontalAlignment(JTextField.RIGHT);
		fields.add(GS);
		//TS
		TS = new JTextField();
		TS.setName("TS");
		TS.setText("-1");
		TS.setBounds(429, 158, 21, 20);
		TS.setEnabled(false);
		TS.setHorizontalAlignment(JTextField.RIGHT);
		fields.add(TS);

		//Labels
		rowALabel = new javax.swing.JLabel();
		rowALabel.setName("rowALabel");
		rowALabel.setText("A");
		rowALabel.setBounds(342, 68, 12, 14);
		labels.add(rowALabel);

		rowCLabel = new javax.swing.JLabel();
		rowCLabel.setName("rowCLabel");
		rowCLabel.setText("C");
		rowCLabel.setBounds(342, 91, 12, 14);
		labels.add(rowCLabel);

		rowGLabel = new javax.swing.JLabel();
		rowGLabel.setName("rowGLabel");
		rowGLabel.setText("G");
		rowGLabel.setBounds(342, 113, 12, 14);
		labels.add(rowGLabel);

		rowTLabel = new javax.swing.JLabel();
		rowTLabel.setName("rowTLabel");
		rowTLabel.setText("T");
		rowTLabel.setBounds(342, 136, 12, 14);
		labels.add(rowTLabel);

		row_Label = new javax.swing.JLabel();
		row_Label.setName("row_Label");
		row_Label.setText("---");
		row_Label.setBounds(342, 160, 12, 14);
		labels.add(row_Label);

		colALabel = new javax.swing.JLabel();
		colALabel.setName("colALabel");
		colALabel.setText("A");
		colALabel.setBounds(368, 46, 12, 14);
		labels.add(colALabel);

		colCLabel = new javax.swing.JLabel();
		colCLabel.setName("colCLabel");
		colCLabel.setText("C");
		colCLabel.setBounds(389, 46, 12, 14);
		labels.add(colCLabel);

		colGLabel = new javax.swing.JLabel();
		colGLabel.setName("colGLabel");
		colGLabel.setText("G");
		colGLabel.setBounds(412, 46, 12, 14);
		labels.add(colGLabel);

		colTLabel = new javax.swing.JLabel();
		colTLabel.setName("colTLabel");
		colTLabel.setText("T");
		colTLabel.setBounds(438, 46, 12, 14);
		labels.add(colTLabel);

		col_Label = new javax.swing.JLabel();
		col_Label.setName("col_Label");
		col_Label.setText("---");
		col_Label.setBounds(460, 46, 12, 14);
		labels.add(col_Label);

	}

	public static void main(java.lang.String[] args) {
		try {
			/* Set native look and feel */
			//UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

			/* Create the frame */
			AlignmentGUI aAlignmentGUI = new AlignmentGUI();

			/* Add a windowListener for the windowClosedEvent */
			aAlignmentGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			aAlignmentGUI.setVisible(true);

		} catch (Throwable exception) {
			System.err.println("Exception occurred in main() of AlignmentGUI");
			exception.printStackTrace(System.out);
		}

	}
}
