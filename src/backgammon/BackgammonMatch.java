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

		matchLength = BackgammonView.getIntegerFromUser("Please Enter The Desired Match Length", scan);

		boolean isMatchOver = false;
		while (!isMatchOver) {
			BackgammonGame game = new BackgammonGame(scan, player1, player2, matchLength);
			BackgammonView.printInfo(
					"Starting Game");
			game.playGame();
			BackgammonView.pressEnterToContinue();
		}

		// close input
		scan.close();
	}

	private void createPlayers() {
		// Create players and get names
		BackgammonView.promptForPlayerName(1);
		player1 = new Player(Colour.WHITE, scan.next());
		BackgammonView.promptForPlayerName(2);
		player2 = new Player(Colour.BLACK, scan.next());
	}
}
