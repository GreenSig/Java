package vosters.Tictactoe;

public class TestClass {

	public static void main(String[] args) {
		TicTacToeBoard t = new TicTacToeBoard();
		System.out.println(t.toString());
		System.out.println(t.hasMoreChildren());
		System.out.println(t.nextChild());
		System.out.println(t.hasMoreChildren());
		System.out.println(t.nextChild());
		System.out.println(t.hasMoreChildren());
		System.out.println(t.nextChild());
		System.out.println(t.hasMoreChildren());
		
		System.out.println();
	}

}
