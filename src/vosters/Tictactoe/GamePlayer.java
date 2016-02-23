package vosters.Tictactoe;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

import javax.swing.*;

public class GamePlayer {

	private BoardPanel BP;
	private TwoPlayerGameBoard currentBoard;
	private int maxLevel = 2;
	private boolean isComputerMoving = false;


	public void ticTacToeMenuItem_ActionPerformed(ActionEvent actionEvent) {
		TicTacToeBoard newBoard = new TicTacToeBoard();
		BP.setBoard(newBoard);
	}

	public void connectFourMenuItem_ActionPerformed(
			java.awt.event.ActionEvent actionEvent) {
		ConnectFourBoard newBoard = new ConnectFourBoard();
		BP.setBoard(newBoard);
	}

	public void easyButton_ActionPerformed(ActionEvent actionEvent) {
		maxLevel = 2;
	}

	public void mediumButton_ActionPerformed(ActionEvent actionEvent) {
		maxLevel = 5;
	}

	public void hardButton_ActionPerformed(ActionEvent actionEvent) {
		maxLevel = 8;
	}

	public void computerDone(TwoPlayerGameBoard newBoard) {
		newBoard.setPos();
		BP.currentBoard = newBoard;
		isComputerMoving = false;

		if (BP.currentBoard.isComputerWinner()) {
			String[] options = { "New Game" };
			int n = JOptionPane.showOptionDialog(null, "You have lost!",
					"LOSE :( ", JOptionPane.YES_OPTION,
					JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
			if (n == 0) {
				newBoard = new TicTacToeBoard();
				BP.setBoard(newBoard);
			}

		}
		if (BP.currentBoard.isDraw()) {
			String[] options = { "New Game" };
			int n = JOptionPane.showOptionDialog(null, "Nobody wins!",
					"TIE :/ ", JOptionPane.YES_OPTION,
					JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
			if (n == 0) {
				newBoard = new TicTacToeBoard();
				BP.setBoard(newBoard);
			}
		}
	}

	public void boardPanel_MouseReleased(MouseEvent mouseEvent) {
		Point2D click = new Point(mouseEvent.getPoint());
		if (isComputerMoving == false) {
			try {
				// places player's move
				BP.currentBoard.placeUserMove(click);
				// checks winner
				if (BP.currentBoard.isUserWinner()) {
					String[] options = { "New Game" };
					int n = JOptionPane.showOptionDialog(null, "You have won!",
							"WINNER :) ", JOptionPane.YES_OPTION,
							JOptionPane.INFORMATION_MESSAGE, null, options,
							options[0]);
					if (n == 0) {
						TicTacToeBoard newBoard = new TicTacToeBoard();
						BP.setBoard(newBoard);
					}

					// if no winner check draw
				} else {
					if (BP.currentBoard.isDraw()) {
						String[] options = { "New Game" };
						int n = JOptionPane.showOptionDialog(null,
								"Nobody wins!", "TIE :/ ",
								JOptionPane.YES_OPTION,
								JOptionPane.INFORMATION_MESSAGE, null, options,
								options[0]);
						if (n == 0) {
							TicTacToeBoard newBoard = new TicTacToeBoard();
							BP.setBoard(newBoard);
						}
						// JOptionPane.showMessageDialog(null, "Nobody wins!",
						// "TIE :/ ", JOptionPane.INFORMATION_MESSAGE);
					} else {
						// make new Minimax
						MiniMax computerMove = new MiniMax(maxLevel);
						// create new thread for computer's move
						ComputerThinkingThread compMove = new ComputerThinkingThread(
								computerMove, BP, this);
						Thread t1 = new Thread(compMove);
						isComputerMoving = true;
						t1.start();

					}
				}
			} catch (Exception e) {
				// JOptionPane.showMessageDialog(null,
				// "Please place your move inside the grid");
			}
		}
	}

	// ***************************************************************************
	// CONSTRUCTOR TO SETUP THE VARIOUS GUI PIECES
	// YOU MAY WANT TO INITIALIZE VARIABLES HERE
	// ***************************************************************************
	public GamePlayer() {

		// Setup frame
		JFrame f = new JFrame();
		f.setTitle("Let's Play!");
		f.setSize(800, 800);
		f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		// Setup Menu Bar
		JMenuBar menuBar = new JMenuBar();

		// Game Menu
		JMenu gameMenu = new JMenu();
		gameMenu.setText("Game");

		JMenuItem TTTMI = new JMenuItem();
		TTTMI.setText("TicTacToe");
		gameMenu.add(TTTMI);

		JMenuItem C4MI = new JMenuItem();
		C4MI.setText("ConnectFour");
		gameMenu.add(C4MI);

		menuBar.add(gameMenu);

		// Difficulty Menu
		JMenu diffMenu = new JMenu();
		diffMenu.setText("Difficulty");

		JRadioButtonMenuItem easyMI = new JRadioButtonMenuItem();
		easyMI.setText("Easy");
		easyMI.setSelected(true);
		diffMenu.add(easyMI);

		JRadioButtonMenuItem medMI = new JRadioButtonMenuItem();
		medMI.setText("Medium");
		diffMenu.add(medMI);

		JRadioButtonMenuItem hardMI = new JRadioButtonMenuItem();
		hardMI.setText("Hard");
		diffMenu.add(hardMI);

		ButtonGroup bg = new ButtonGroup();
		bg.add(easyMI);
		bg.add(medMI);
		bg.add(hardMI);

		menuBar.add(diffMenu);
		f.setJMenuBar(menuBar);

		// Setup Board Panel
		currentBoard = null;
		BP = new BoardPanel(currentBoard);
		BP.setBackground(Color.black);
		f.setContentPane(BP);

		// Listeners
		// Mouse Listener for user's placement of game piece
		BP.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				boardPanel_MouseReleased(e);
			};
		});
		// Menu Item Listeners
		TTTMI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ticTacToeMenuItem_ActionPerformed(e);
			};
		});
		C4MI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connectFourMenuItem_ActionPerformed(e);
			};
		});
		easyMI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				easyButton_ActionPerformed(e);
			};
		});
		medMI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mediumButton_ActionPerformed(e);
			};
		});
		hardMI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hardButton_ActionPerformed(e);
			};
		});

		f.setVisible(true);

		// YOUR INITIALIZE CODE GOES HERE
		// SET ANY VARIABLES THAT YOU WANT INITIALIZED AT THE START HERE
	}

	public static void main(String[] args) {
		new GamePlayer();
	}

}
