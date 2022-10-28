package backgammon;

public class Backgammon {

	public static void main(String[] args) {

		BackgammonBoard board = new BackgammonBoard();
		BackgammonBoardView.print(board);
		Player Player1 = new Player(Colour.BLACK);
		Player Player2 = new Player(Colour.WHITE);
		
		System.out.println("Player 1: "+Player1.toString());
		System.out.println("Player 2: "+Player2.toString());
		
		System.out.println("Roll 1: " +Dice.Roll() +"\nRoll 2: "+Dice.Roll());
	}
}
