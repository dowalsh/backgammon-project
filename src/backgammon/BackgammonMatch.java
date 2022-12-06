package backgammon;

import java.util.Scanner;
import java.io.FileNotFoundException;

public class BackgammonMatch {

	// user input scanner
	private Scanner scan;

	// Players of the match
	private Player player1;
	private Player player2;

	// length of match - the score that a player must get to win
	private int matchLength;
	
	private boolean hasCrawfordHappened = false;

	public BackgammonMatch() {

	}

	public void playMatch() throws FileNotFoundException{

		scan = new Scanner(System.in);

		createPlayers();

		matchLength = BackgammonBoardView.getIntegerFromUser("Please Enter The Desired Match Length", scan);

		boolean isMatchOver = false;
		while (!isMatchOver) {
			BackgammonGame game = new BackgammonGame(this);
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
