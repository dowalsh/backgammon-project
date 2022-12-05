package backgammon;

import java.util.Scanner;

public class BackgammonMatch {

	// user input scanner
	private Scanner scan;

	// Players of the match
	private Player player1;
	private Player player2;

	// length of match - the score that a player must get to win
	private int matchLength;

	public BackgammonMatch() {

	}

	public void playMatch() {

		scan = new Scanner(System.in);

		createPlayers();

		matchLength = BackgammonBoardView.getIntegerFromUser("Please Enter The Desired Match Length", scan);

		boolean isMatchOver = false;
		while (!isMatchOver) {
			BackgammonGame game = new BackgammonGame(scan, player1, player2, matchLength);
			BackgammonBoardView.printInfo(
					"Starting Game");
			game.playGame();
			BackgammonBoardView.pressEnterToContinue();
		}

		// close input
		scan.close();
	}

	private void createPlayers() {
		// Create players and get names
		BackgammonBoardView.promptForPlayerName(1);
		player1 = new Player(Colour.WHITE, scan.next());
		BackgammonBoardView.promptForPlayerName(2);
		player2 = new Player(Colour.BLACK, scan.next());
	}
}
