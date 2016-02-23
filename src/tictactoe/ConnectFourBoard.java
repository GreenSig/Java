package tictactoe;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Point2D;

public class ConnectFourBoard implements TwoPlayerGameBoard {
	// instance variables
	public String[][] board;
	public boolean playerMove;
	public int[] nextOpenPosition = { 0, 5 };
	public int[] currentMove = { 0, 5 };

	// default constructor
	// double loop sets all the board positions to empty and
	public ConnectFourBoard() {
		board = new String[7][6];
		for (int i = 0; i < 7; i++) {
			for (int k = 0; k < 6; k++) {
				board[i][k] = " ";
			}
		}
		playerMove = true;
	}

	// copy constructor
	public ConnectFourBoard(ConnectFourBoard b) {
		// creates the copied board
		this.board = new String[7][6];
		for (int i = 0; i < 7; i++) {
			for (int k = 0; k < 6; k++) {
				board[i][k] = b.board[i][k];
			}
		}
		// updates the current players move
		if (b.playerMove == true)
			this.playerMove = false;
		if (b.playerMove == false)
			this.playerMove = true;

		// updates currentMove
		this.currentMove[0] = b.currentMove[0];
		this.currentMove[1] = b.currentMove[1];
	}

	public void setPos() {
		this.nextOpenPosition[0] = 0; //x
		this.nextOpenPosition[1] = 5; //y
	}

	public void draw(Graphics g) {
		g.setColor(Color.YELLOW);
		g.fillRect(20, 20, 525, 450);

		g.setColor(Color.GRAY);
		for (int i = 0; i < 7; i++) {
			for (int k = 0; k < 6; k++) {
				g.fillOval(i * 75 + 25, k * 75 + 25, 60, 60);
			}
		}

		for (int i = 0; i < 7; i++) {
			for (int k = 0; k < 6; k++) {
				if (board[i][k].equals("X")) {
					g.setColor(Color.RED);
					g.fillOval(i * 75 + 25, k * 75 + 25, 60, 60);

				} else if (board[i][k].equals("O")) {
					g.setColor(Color.BLACK);
					g.fillOval(i * 75 + 25, k * 75 + 25, 60, 60);
				}
			}
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
		// checks for a space
		if (nextOpenPosition[0] >= 6 && nextOpenPosition[1] <= 1) {
			return false;
		}

		for (int i = 0; i < 7; i++) {
			for (int k = 0; k < 6; k++) {
				if (board[i][k].equals(" ")) {
					return true;
				}

			}
		}

		return false;
	}

	public boolean isComputerWinner() {
		// check left and right sides, see if it equals more than 4
		// left side

		// check up and down, see if it equals more than 4

		// check diagonal up-left and diagonal down-right

		// check diagonal up-right and diagonal down-left

		return false;
	}

	public boolean isDraw() {
		if (isUserWinner() == false && isComputerWinner() == false
				&& !(this.hasMoreChildren())) {

			return true;
		}
		return false;
	}

	public boolean isUserWinner() {

		return false;
	}

	public TwoPlayerGameBoard nextChild() {
		boolean isFeasible = false;
		ConnectFourBoard child = null;
		// find the next open position
		while (isFeasible == false) {
			// if the nextOpenPosition == 0, place the move
			if (board[nextOpenPosition[0]][nextOpenPosition[1]].equals(" ")) {
				isFeasible = true;

			} else {
				// if y is full, go to x+1
				if (nextOpenPosition[1] == 0) {
					nextOpenPosition[1] = 5;
					nextOpenPosition[0]++;
				} else {
					nextOpenPosition[1]--;
				}
			}

		}

		// create the child
		child = new ConnectFourBoard(this);
		String newMove = "";
		// find out who's move it is
		if (child.playerMove) {
			newMove = "X";
		} else {
			newMove = "O";
		}
		// set the move to the board
		child.board[nextOpenPosition[0]][nextOpenPosition[1]] = newMove;

		currentMove[0] = nextOpenPosition[0];
		currentMove[1] = nextOpenPosition[1];

		
		if (nextOpenPosition[0] == 6) {
			nextOpenPosition[1]--;
			nextOpenPosition[0] = 0;
		} else {
			nextOpenPosition[0]++;
		}

		return child;
	}

	public void placeUserMove(Point2D mouseLocation) throws Exception {
		double mouseX = mouseLocation.getX();
		this.setPos();
		
		if ((25 < mouseX) && (mouseX < 85)) {
			// searching for the next open position
			// start the index at 0
			int placementIndex = 0;
			// update the current move
			currentMove[0] = 0;
			// loop thru finding the place of the move
			while (placementIndex > 6) {
				if (!board[0][placementIndex].equals(" ")) {
					placementIndex++;
				} else {
					break;
				}
			}

			// if it isnt a full row
			if (!(placementIndex == 6)) {
				board[0][5 - placementIndex] = "X";
				currentMove[1] = placementIndex;
			}
		}
		if ((100 < mouseX) && (mouseX < 160)) {
			// searching for the next open position
			// start the index at 0
			int placementIndex = 0;
			// update the current move
			currentMove[0] = 1;
			// loop thru finding the place of the move
			while (placementIndex > 6) {
				if (!board[1][placementIndex].equals(" ")) {
					placementIndex++;
				} else {
					break;
				}
			}

			// if it isnt a full row
			if (!(placementIndex == 6)) {
				board[1][5 - placementIndex] = "X";
				currentMove[1] = placementIndex;
			}
		}
		if ((175 < mouseX) && (mouseX < 235)) {
			// searching for the next open position
			// start the index at 0
			int placementIndex = 0;
			// update the current move
			currentMove[0] = 2;
			// loop thru finding the place of the move
			while (placementIndex > 6) {
				if (!board[2][placementIndex].equals(" ")) {
					placementIndex++;
				} else {
					break;
				}
			}

			// if it isnt a full row
			if (!(placementIndex == 6)) {
				board[2][5 - placementIndex] = "X";
				currentMove[1] = placementIndex;
			}
		}
		if ((250 < mouseX) && (mouseX < 310)) {
			// searching for the next open position
			// start the index at 0
			int placementIndex = 0;
			// update the current move
			currentMove[0] = 3;
			// loop thru finding the place of the move
			while (placementIndex > 6) {
				if (!board[3][placementIndex].equals(" ")) {
					placementIndex++;
				} else {
					break;
				}
			}

			// if it isnt a full row
			if (!(placementIndex == 6)) {
				board[3][5 - placementIndex] = "X";
				currentMove[1] = placementIndex;
			}
		}
		if ((325 < mouseX) && (mouseX < 385)) {
			// searching for the next open position
			// start the index at 0
			int placementIndex = 0;
			// update the current move
			currentMove[0] = 4;
			// loop thru finding the place of the move
			while (placementIndex > 6) {
				if (!board[4][placementIndex].equals(" ")) {
					placementIndex++;
				} else {
					break;
				}
			}

			// if it isnt a full row
			if (!(placementIndex == 6)) {
				board[4][5 - placementIndex] = "X";
				currentMove[1] = placementIndex;
			}
		}
		if ((400 < mouseX) && (mouseX < 460)) {
			// searching for the next open position
			// start the index at 0
			int placementIndex = 0;
			// update the current move
			currentMove[0] = 5;
			// loop thru finding the place of the move
			while (placementIndex > 6) {
				if (!board[5][placementIndex].equals(" ")) {
					placementIndex++;
				} else {
					break;
				}
			}

			// if it isnt a full row
			if (!(placementIndex == 6)) {
				board[5][5 - placementIndex] = "X";
				currentMove[1] = placementIndex;
			}
		}

		if ((475 < mouseX) && (mouseX < 535)) {
			// searching for the next open position
			// start the index at 0
			int placementIndex = 0;
			// update the current move
			currentMove[0] = 6;
			// loop thru finding the place of the move
			while (placementIndex > 6) {
				if (!board[6][placementIndex].equals(" ")) {
					placementIndex++;
				} else {
					break;
				}
			}

			// if it isnt a full row
			if (!(placementIndex == 6)) {
				board[6][5 - placementIndex] = "X";
				currentMove[1] = placementIndex;
			}
		}

	}

}
