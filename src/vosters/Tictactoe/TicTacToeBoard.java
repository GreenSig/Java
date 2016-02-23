package vosters.Tictactoe;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class TicTacToeBoard implements TwoPlayerGameBoard {
	// instance variables
	public ArrayList<String> board;
	public boolean playerMove;
	public int nextOpenPosition = 0;

	// default constructor	
	public TicTacToeBoard() {
		this.board = new ArrayList<String>();
		for (int i = 0; i < 9; i++) {
			board.add(" ");
		}

		// hardcoded values for testing
//		board.set(0, "O");
//		board.set(1, " ");
//		board.set(2, "O");
//		board.set(3, " ");
//		board.set(4, "O");
//		board.set(5, "X");
//		board.set(6, "X");
//		board.set(7, " ");
//		board.set(8, " ");
		
		playerMove = true;
	}

	// copy constructor
	public TicTacToeBoard(TicTacToeBoard b) {
		// creates the copied board
		this.board = new ArrayList<String>(b.board);
		// updates the current players move
		if (b.playerMove == true)
			this.playerMove = false;
		if (b.playerMove == false)
			this.playerMove = true;
	}
	public void setPos(){
		this.nextOpenPosition = 0;
	}

	public void draw(Graphics g) {
		g.setColor(Color.WHITE);
		g.drawLine(130, 50, 130, 350);
		g.drawLine(230, 50, 230, 350);
		g.drawLine(30, 150, 330, 150);
		g.drawLine(30, 250, 330, 250);

		
		if (board.get(0).equals("X")) {
			g.setColor(Color.GREEN);
			g.drawLine(35, 55, 125, 145);
			g.drawLine(35, 145, 125, 55);
		} else if (board.get(0).equals("O")) {
			g.setColor(Color.RED);
			g.drawOval(35, 55, 90, 90);
		}

		if (board.get(1).equals("X")) {
			g.setColor(Color.GREEN);
			g.drawLine(135, 55, 225, 145);
			g.drawLine(135, 145, 225, 55);
		} else if (board.get(1).equals("O")) {
			g.setColor(Color.RED);
			g.drawOval(135, 55, 90, 90);
		}

		if (board.get(2).equals("X")) {
			g.setColor(Color.GREEN);
			g.drawLine(235, 55, 325, 145);
			g.drawLine(235, 145, 325, 55);
		} else if (board.get(2).equals("O")) {
			g.setColor(Color.RED);
			g.drawOval(235, 55, 90, 90);
		}

		if (board.get(3).equals("X")) {
			g.setColor(Color.GREEN);
			g.drawLine(35, 155, 125, 245);
			g.drawLine(35, 245, 125, 155);
		} else if (board.get(3).equals("O")) {
			g.setColor(Color.RED);
			g.drawOval(35, 155, 90, 90);
		}

		if (board.get(4).equals("X")) {
			g.setColor(Color.GREEN);
			g.drawLine(135, 155, 225, 245);
			g.drawLine(135, 245, 225, 155);
		} else if (board.get(4).equals("O")) {
			g.setColor(Color.RED);
			g.drawOval(135, 155, 90, 90);
		}

		if (board.get(5).equals("X")) {
			g.setColor(Color.GREEN);
			g.drawLine(235, 155, 325, 245);
			g.drawLine(235, 245, 325, 155);
		} else if (board.get(5).equals("O")) {
			g.setColor(Color.RED);
			g.drawOval(235, 155, 90, 90);
		}

		if (board.get(6).equals("X")) {
			g.setColor(Color.GREEN);
			g.drawLine(35, 255, 125, 345);
			g.drawLine(35, 345, 125, 255);
		} else if (board.get(6).equals("O")) {
			g.setColor(Color.RED);
			g.drawOval(35, 255, 90, 90);
		}

		if (board.get(7).equals("X")) {
			g.setColor(Color.GREEN);
			g.drawLine(135, 255, 225, 345);
			g.drawLine(135, 345, 225, 255);
		} else if (board.get(7).equals("O")) {
			g.setColor(Color.RED);
			g.drawOval(135, 255, 90, 90);
		}

		if (board.get(8).equals("X")) {
			g.setColor(Color.GREEN);
			g.drawLine(235, 255, 325, 345);
			g.drawLine(235, 345, 325, 255);
		} else if (board.get(8).equals("O")) {
			g.setColor(Color.RED);
			g.drawOval(235, 255, 90, 90);
		}

	}

	public double evaluateValue() {
		// if computer wins print out -1
		if (this.isComputerWinner())
			return -1;
		// if player wins print out 1
		if (this.isUserWinner())
			return 1;
		// draws print out 0
		return 0;
	}

	public boolean hasMoreChildren() {
		if (board.subList(nextOpenPosition, board.size()).contains(" ")) {
			return true;
		} else {
			return false;
		}

	}

	public boolean isComputerWinner() {
		if ( // horizontal win combos
		(board.get(0) == "O" && board.get(1) == "O" && board.get(2) == "O")
				|| (board.get(3) == "O" && board.get(4) == "O" && board.get(5) == "O")
				|| (board.get(6) == "O" && board.get(7) == "O" && board.get(8) == "O")
				||
				// vertical win combos
				(board.get(0) == "O" && board.get(3) == "O" && board.get(6) == "O")
				|| (board.get(1) == "O" && board.get(4) == "O" && board.get(7) == "O")
				|| (board.get(2) == "O" && board.get(5) == "O" && board.get(8) == "O")
				||
				// diagonal win combos
				(board.get(0) == "O" && board.get(4) == "O" && board.get(8) == "O")
				|| (board.get(2) == "O" && board.get(4) == "O" && board.get(6) == "O")) {
			
			return true;
		}

		return false;
	}

	public boolean isDraw() {
		if (isUserWinner() == false && isComputerWinner() == false
				&& !(board.contains(" "))) {
			
			return true;
		}
		return false;
	}

	public boolean isUserWinner() {
		if ( // horizontal win combos
		(board.get(0) == "X" && board.get(1) == "X" && board.get(2) == "X")
				|| (board.get(3) == "X" && board.get(4) == "X" && board.get(5) == "X")
				|| (board.get(6) == "X" && board.get(7) == "X" && board.get(8) == "X")
				||
				// vertical win combos
				(board.get(0) == "X" && board.get(3) == "X" && board.get(6) == "X")
				|| (board.get(1) == "X" && board.get(4) == "X" && board.get(7) == "X")
				|| (board.get(2) == "X" && board.get(5) == "X" && board.get(8) == "X")
				||
				// diagonal win combos
				(board.get(0) == "X" && board.get(4) == "X" && board.get(8) == "X")
				|| (board.get(2) == "X" && board.get(4) == "X" && board.get(6) == "X")) {
			
			return true;
		}

		return false;
	}

	public TwoPlayerGameBoard nextChild() {
		boolean isFeasible = false;
		TicTacToeBoard child = null;
		// find the next open position
		while (isFeasible == false) {
			if (board.get(nextOpenPosition).equals(" ")) {
				isFeasible = true;
			} else {
				nextOpenPosition++;
			}
		}
		// create the child
		child = new TicTacToeBoard(this);
		String newMove = "";
		// find out who's move it is
		if (child.playerMove) {
			newMove = "X";
		} else {
			newMove = "O";
		}
		// set the move to the board
		child.board.set(nextOpenPosition, newMove);
		nextOpenPosition++;
		return child;
	}

	public void placeUserMove(Point2D mouseLocation) throws Exception {
		playerMove = true;
		if (mouseLocation.getX() > 30 && mouseLocation.getX() < 130
				&& mouseLocation.getY() > 50 && mouseLocation.getY() < 150) {
			if (board.get(0) == " ") {
				board.set(0, "X");
			} else
				throw new NullPointerException();
		} else if (mouseLocation.getX() > 130 && mouseLocation.getX() < 230
				&& mouseLocation.getY() > 50 && mouseLocation.getY() < 150) {
			if (board.get(1) == " ") {
				board.set(1, "X");
			} else
				throw new NullPointerException();
		} else if (mouseLocation.getX() > 230 && mouseLocation.getX() < 330
				&& mouseLocation.getY() > 50 && mouseLocation.getY() < 150) {
			if (board.get(2) == " ") {
				board.set(2, "X");
			} else
				throw new NullPointerException();
		} else if (mouseLocation.getX() > 30 && mouseLocation.getX() < 130
				&& mouseLocation.getY() > 150 && mouseLocation.getY() < 250) {
			if (board.get(3) == " ") {
				board.set(3, "X");
			} else
				throw new NullPointerException();
		} else if (mouseLocation.getX() > 130 && mouseLocation.getX() < 230
				&& mouseLocation.getY() > 150 && mouseLocation.getY() < 250) {
			if (board.get(4) == " ") {
				board.set(4, "X");
			} else
				throw new NullPointerException();
		} else if (mouseLocation.getX() > 230 && mouseLocation.getX() < 330
				&& mouseLocation.getY() > 150 && mouseLocation.getY() < 250) {
			if (board.get(5) == " ") {
				board.set(5, "X");
			} else
				throw new NullPointerException();
		} else if (mouseLocation.getX() > 30 && mouseLocation.getX() < 130
				&& mouseLocation.getY() > 250 && mouseLocation.getY() < 350) {
			if (board.get(6) == " ") {
				board.set(6, "X");
			} else
				throw new NullPointerException();
		} else if (mouseLocation.getX() > 130 && mouseLocation.getX() < 230
				&& mouseLocation.getY() > 250 && mouseLocation.getY() < 350) {
			if (board.get(7) == " ") {
				board.set(7, "X");
			} else
				throw new NullPointerException();
		} else if (mouseLocation.getX() > 230 && mouseLocation.getX() < 330
				&& mouseLocation.getY() > 250 && mouseLocation.getY() < 350) {
			if (board.get(8) == " ") {
				board.set(8, "X");
			} else
				throw new NullPointerException();
		} else
			throw new NullPointerException();
		
		
	}

	public String toString() {
		// print out the board in the format of:
		// [ ][ ][ ]
		// [ ][ ][ ]
		// [ ][ ][ ]
		String result = "[" + board.get(0) + "]" + "[" + board.get(1) + "]"
				+ "[" + board.get(2) + "]\n" + "[" + board.get(3) + "]" + "["
				+ board.get(4) + "]" + "[" + board.get(5) + "]\n" + "["
				+ board.get(6) + "]" + "[" + board.get(7) + "]" + "["
				+ board.get(8) + "]";

		return result;

	}

}
