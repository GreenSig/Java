package vosters.Tictactoe;

import java.awt.Graphics;


import javax.swing.JPanel;

public class BoardPanel extends JPanel {
	TwoPlayerGameBoard currentBoard;

	private static final long serialVersionUID = 1L;

	public BoardPanel(TwoPlayerGameBoard currentBoard) {
		this.currentBoard = currentBoard;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (!(currentBoard == null)) {
			currentBoard.draw(g);
			repaint();
		}

	
}
	public void setBoard(TwoPlayerGameBoard newBoard) {
		currentBoard = newBoard;
		
	}
}
