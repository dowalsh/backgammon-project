package backgammon;

import java.util.Scanner;

public class Backgammon {

	public static void main(String[] args) {

		BackgammonBoard board = new BackgammonBoard();
//		BackgammonBoard board =  BackgammonBoard.createTestBoard("EXAMPLE TEST");

		
		Scanner scan = new Scanner(System.in);
		

		// Create players and get names
		BackgammonBoardView.promptForPlayerName(1);
		Player player1 = new Player(Colour.WHITE, scan.next());
		BackgammonBoardView.promptForPlayerName(2);
		Player player2 = new Player(Colour.BLACK, scan.next());
		int matchLength = BackgammonBoardView.getIntegerFromUser("Please Enter The Desired Match Length",scan);
		
		// Game control loop
		boolean run = true; // to control whether the game should continue running
		Player activePlayer;

		BackgammonBoardView.printInputOptions();
		
		BackgammonBoardView.printInfo("Match Length: " + matchLength);

		BackgammonBoardView.printInfo("Rolling Dice to see who goes first");
		
		activePlayer = choosePlayerToMoveFirst(player1,player2);
		
		while (run) {

			boolean isTurnOver = false;

			// switch the active player
			if (activePlayer == player1)
				activePlayer = player2;
			else
				activePlayer = player1;

			board.beginTurn();
			while (!isTurnOver) {
			
				BackgammonBoardView.printInfo(activePlayer.toString() + " it is your turn!");
				BackgammonBoardView.printScores(matchLength,player1,player2);
				BackgammonBoardView.printBoard(board, activePlayer);

				BackgammonBoardView.promptPlayerForInput();

				// convert input string to upper case in order to accept lower case inputs
				String input = scan.next().toUpperCase();
				if (input.equals("QUIT")) {
					// Quit Game
					BackgammonBoardView.printInfo("Game terminated, thanks for playing!");
					board.endGame();
					isTurnOver = true;
					run = false;
				} else if (input.equals("ROLL")) {
					if (board.isDiceRolled()) {
						BackgammonBoardView.printError("Cannot re-roll dice");
					} else {
						// roll the dice
						board.rollDice(activePlayer);
						int[] roll = board.getLatestDiceRoll();
						BackgammonBoardView.printInfo(activePlayer + " Rolled: " + Integer.toString(roll[0]) + " & "
								+ Integer.toString(roll[1]));
						
						if(board.noMoveAvailable(activePlayer)) {
							isTurnOver = board.isTurnOver();
							BackgammonBoardView.printInfo("No Moves Available, Ending Turn");

						}
					}
				} else if (input.equals("PIP")) {
					// "pip" command to report the pip count for both players
					BackgammonBoardView.printPipCounts(board, player1, player2);
				} else if (input.length() == 1 && board.getMoveKeys().contains(input.charAt(0))) {
					// User single alphabetical input to select a move
					BackgammonBoardView.printInfo("Selected Move Option: " + input);
					board.selectMove(input.charAt(0),activePlayer);
					isTurnOver = board.isTurnOver();
					
				} else if (input.equals("HINT")) {
					BackgammonBoardView.printInputOptions();
				} else if (input.matches("DICE[1-6][1-6]")) {
					if (board.isDiceRolled()) {
						BackgammonBoardView.printError("Cannot re-roll dice");
					} else {
						// set roll
						int roll1 = Character.getNumericValue(input.charAt(4));
						int roll2 = Character.getNumericValue(input.charAt(5));
						board.setRolls(roll1, roll2, activePlayer);
						int[] roll = {roll1, roll2};
						BackgammonBoardView.printInfo(activePlayer + " Rolled: " + Integer.toString(roll[0]) + " & "
								+ Integer.toString(roll[1]));
					}
				}
				// can add more else if s for other input options here
				else {
					// input error
					BackgammonBoardView.printError("INVALID INPUT");
					BackgammonBoardView.printInputOptions();
				}
				if (board.isWon(activePlayer)) {
					BackgammonBoardView.printInfo("Game Completed, " + activePlayer.toString() + " is the winner! Thanks for playing!");
					board.endGame();
					isTurnOver = true;
					run = false;
				}
			}
		}

		// print the game one last time
		BackgammonBoardView.printBoard(board, activePlayer);

		// close input
		scan.close();

	}

	private static Player choosePlayerToMoveFirst(Player player1, Player player2) {
		Player playerToMoveFirst;
		
		int[] initialRolls = new int[2];
		do {
			initialRolls[0] = Dice.roll();
			initialRolls[1] = Dice.roll();
			
			BackgammonBoardView.printStart(player1, player2, initialRolls);
			if (initialRolls[0] == initialRolls[1]) {
				BackgammonBoardView.printInfo("Rolls are equal, let's roll again");
			}
		} while (initialRolls[0] == initialRolls[1]);
		
		if (initialRolls[0] > initialRolls[1]) {
			BackgammonBoardView.printInfo(player1.toString()+" has a higher roll, so goes first");
			playerToMoveFirst = player2;
		} else {
			BackgammonBoardView.printInfo(player2.toString()+" has a higher roll, so goes first");
			playerToMoveFirst = player1;
		}
		
		return playerToMoveFirst;
	}
}
