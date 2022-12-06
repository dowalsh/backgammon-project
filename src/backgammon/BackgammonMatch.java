package backgammon;

import java.util.Scanner;

public class BackgammonMatch {

	// user input scanner
	private Scanner scan;

	// Players of the match
	private Player player1 = new Player(Colour.WHITE, "Player 1");
	private Player player2 = new Player(Colour.BLACK, "Player 2");;

	// length of match - the score that a player must get to win
	private int matchLength;

	private boolean hasCrawfordHappened = false;

	public BackgammonMatch() {

	}

	public void playMatch() {

		scan = new Scanner(System.in);

		setPlayerNames();

		matchLength = BackgammonView.getIntegerFromUser("Please Enter The Desired Match Length", scan);

		boolean isMatchOver = false;
		while (!isMatchOver) {
			BackgammonGame game = new BackgammonGame(this);
			BackgammonView.printInfo("Starting Game");
			game.playGame();
			BackgammonView.pressEnterToContinue();
		}

		// close input
		scan.close();
	}

	private void setPlayerNames() {
		// Create players and get names
		BackgammonView.promptForPlayerName(1);
		player1.setName(scan.next());
		BackgammonView.promptForPlayerName(2);
		player2.setName(scan.next());
	}

	public boolean hasCrawfordHappened() {
		return this.hasCrawfordHappened;
	}

	public void setHasCrawfordHappened(boolean crawford) {
		this.hasCrawfordHappened = crawford;
	}

	public int getMatchLength() {
		return this.matchLength;
	}

	public Player getPlayer1() {
		return this.player1;
	}

	public Player getPlayer2() {
		return this.player2;
	}

	public Scanner getScanner() {
		return this.scan;
	}

}
