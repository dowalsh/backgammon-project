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

	private boolean isMatchQuit = false;

	/**
	 * @param s
	 */
	public BackgammonMatch(Scanner s) {
		this.scan = s;
	}

	/**
	 * 
	 */
	public void playMatch() {

		setPlayerNames();

		matchLength = BackgammonView.getIntegerFromUser("Please Enter The Desired Match Length", scan);

		while (!isMatchOver() && !isMatchQuit()) {
			BackgammonView.pressEnterToContinue(scan);
			BackgammonGame game = new BackgammonGame(this);
			BackgammonView.printInfo("Starting Game");
			game.playGame();
		}

		if (!isMatchQuit) {
			BackgammonView.printInfo("Match Over. " + getWinner().toString() + " is the winner!");
			BackgammonView.printScores(matchLength, player1, player2);
		}

	}

	/**
	 * @return
	 */
	private Player getWinner() {
		Player winner;
		if (player1.getScore() > player2.getScore())
			winner = player1;
		else
			winner = player2;
		return winner;
	}

	/**
	 * @return
	 */
	private boolean isMatchOver() {
		return (player1.getScore() >= matchLength || player2.getScore() >= matchLength);
	}

	/**
	 * 
	 */
	private void setPlayerNames() {
		// Create players and get names
		player1.setName(BackgammonView.promptForPlayerName(1, scan));
		player2.setName(BackgammonView.promptForPlayerName(2, scan));
	}

	/**
	 * @return
	 */
	public boolean hasCrawfordHappened() {
		return this.hasCrawfordHappened;
	}

	/**
	 * @param crawford
	 */
	public void setHasCrawfordHappened(boolean crawford) {
		this.hasCrawfordHappened = crawford;
	}

	/**
	 * @return
	 */
	public int getMatchLength() {
		return this.matchLength;
	}

	/**
	 * @return
	 */
	public Player getPlayer1() {
		return this.player1;
	}

	/**
	 * @return
	 */
	public Player getPlayer2() {
		return this.player2;
	}

	/**
	 * @return
	 */
	public Scanner getScanner() {
		return this.scan;
	}

	/**
	 * @return
	 */
	public boolean isMatchQuit() {
		return isMatchQuit;
	}

	/**
	 * 
	 */
	public void quit() {
		isMatchQuit = true;
	}

}
