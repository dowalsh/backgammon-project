package backgammon;

public class Backgammon {

	public static void main(String[] args) {

		BackgammonBoard board = new BackgammonBoard();
		Player player1 = new Player(Colour.BLACK);
		Player player2 = new Player(Colour.WHITE);
		
		BackgammonBoardView.print(board, player2);

		
		System.out.println("Player 1: "+player1.toString());
		System.out.println("Player 2: "+player2.toString());
		
		System.out.println("Roll 1: " +Dice.Roll() +"\nRoll 2: "+Dice.Roll());
	}
}
