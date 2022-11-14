package backgammon;

import java.util.Scanner;

public class Backgammon {

	public static void main(String[] args) {

		BackgammonBoard board = new BackgammonBoard();
		Scanner stringScanner = new Scanner(System.in);

		// Create players and get names
		BackgammonBoardView.promptForPlayerName(1);
		Player player1 = new Player(Colour.WHITE, stringScanner.next());
		BackgammonBoardView.promptForPlayerName(2);
		Player player2 = new Player(Colour.BLACK, stringScanner.next());

		// Game control loop
		boolean run = true; // to control whether the game should continue running
		Player activePlayer = player2;

		BackgammonBoardView.printInputOptions();

		while (run) {

			boolean isTurnOver = false;
			boolean diceHasBeenRolled = false;

			// switch the active player
			if (activePlayer == player1)
				activePlayer = player2;
			else
				activePlayer = player1;

			while (!isTurnOver) {

				BackgammonBoardView.printInfo(activePlayer.toString() + " it is your turn!");

				BackgammonBoardView.print(board, activePlayer);

				BackgammonBoardView.promptPlayerForInput();

				// convert input string to upper case in order to accept lower case inputs
				String input = stringScanner.next().toUpperCase();
				if (input.equals("QUIT")) {
					// Quit Game
					BackgammonBoardView.printInfo("Game terminated, thanks for playing!");
					board.endGame();
					isTurnOver = true;
					run = false;
				} else if (input.equals("ROLL")) {
					if (diceHasBeenRolled) {
						BackgammonBoardView.printError("Cannot re-roll dice");
					} else {
						// roll the dice
						board.rollDice();
						int[] roll = board.getLatestDiceRoll();
						BackgammonBoardView.printInfo(activePlayer + " Rolled: " + Integer.toString(roll[0]) + " & "
								+ Integer.toString(roll[1]));
						diceHasBeenRolled = true;
						board.updateLegalMoves(activePlayer);
						String[] moves = board.legalMovesToString(activePlayer);
						for (int i = 0; i < moves.length; i++) {
							System.out.println(moves[i]);
						}
					}
				} else if (input.equals("PIP")) {
					// "pip" command to report the pip count for both players
					BackgammonBoardView.printPipCounts(board, player1, player2);
				} else if (input.length() ==1 && board.getMoveKeys().contains(input.charAt(0))) {
					// User single alphabetical input to select a move
					BackgammonBoardView.printInfo("Selected Move Option: " + input);
					board.endTurn();
					isTurnOver = true; // TODO temporary option to choose - this will be replaced by actual move
				}
				// can add more else if s for other input options here
				else {
					// input error
					BackgammonBoardView.printError("INVALID INPUT");
					BackgammonBoardView.printInputOptions();
				}
				if (board.isWon()) {
					run = false;
					BackgammonBoardView.printInfo("Game Completed! Thanks for paying!");
				}
			}
		}

		// print the game one last time
		BackgammonBoardView.print(board, activePlayer);

		// close input
		stringScanner.close();

	}
}
