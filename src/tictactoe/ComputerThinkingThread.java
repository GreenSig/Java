package tictactoe;

public class ComputerThinkingThread implements Runnable{
	public MiniMax computerMove;
	public BoardPanel BP;
	public GamePlayer game;
	
	public ComputerThinkingThread(MiniMax computerMove, BoardPanel BP, GamePlayer game) {
		this.computerMove = computerMove;
		this.BP = BP;
		this.game = game;
	}

	public void run() {
		TwoPlayerGameBoard newBoard = computerMove.generateNextMove(BP.currentBoard);
		game.computerDone(newBoard);
	}

}
