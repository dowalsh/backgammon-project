package backgammon;

import java.util.Scanner;

public class Backgammon {

	public static void main(String[] args) {

		BackgammonBoard board = new BackgammonBoard();

		// Create players and get names
		Player player1 = new Player(Colour.BLACK);
		Player player2 = new Player(Colour.WHITE);
		System.out.println("Player 1: " + player1.toString());
		System.out.println("Player 2: " + player2.toString());

		System.out.println("Roll 1: " + Dice.Roll() + "\nRoll 2: " + Dice.Roll());

		// Game control loop
		boolean run = true; // to control whether the game should continue running
		Player activePlayer = player1;
		Scanner stringScanner = new Scanner(System.in);

		while (run) {
			
			BackgammonBoardView.print(board, player2);

			BackgammonBoardView.promptPlayerForInput();
			// convert input string to upper case in order to accept lower case inputs
			String input = stringScanner.next().toUpperCase();
			if (input.equals("Q")) {
				// Quit Game
				BackgammonBoardView.printInfo("Game terminated, thanks for playing!");
				run = false;
			}//can add else if s for other input options here
		else {
				// input error
			BackgammonBoardView.printError("INVALID INPUT");
			BackgammonBoardView.printInputOptions();
			}
			if (board.isWon()) {
				run = false;
				BackgammonBoardView.printInfo("Game Completed! Thanks for paying!");
			}
			
			// change the active player
			if (activePlayer == player1)
				activePlayer = player2;
			else
				activePlayer = player1;
		}

	}
}
