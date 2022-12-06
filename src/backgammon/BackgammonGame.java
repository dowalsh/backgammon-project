package backgammon;

import java.util.Scanner;
import java.io.File;

public class BackgammonGame {

	// user input scanner
	private Scanner scan;
	private Scanner filescan;

	private boolean testMode = false;

	// Players of the match
	private Player player1;
	private Player player2;

	// Alternate the turn player
	private Player activePlayer;
	private Player inactivePlayer;

	private int matchLength;

	private BackgammonMatch match;

	// board for the game
//	private BackgammonBoard board = new BackgammonBoard();
	private BackgammonBoard board = BackgammonBoard.createTestBoard("NEARLY OVER");

	public BackgammonBoard getBoard() {
		return board;
	}

	// Store whether the game is over
	boolean isGameOver = false;

	public BackgammonGame(BackgammonMatch m) {
		this.scan = m.getScanner();
		this.player1 = m.getPlayer1();
		this.player2 = m.getPlayer2();
		this.matchLength = m.getMatchLength();
		this.match = m;
	}

	public void playGame() {

		this.chooseFirstPlayerToMove();

		BackgammonView.pressEnterToContinue(scan);

		this.setDoublingCube();
		
		if(this.isDoublingCubeInPlay()) {
			BackgammonView.printInfo("Doubles in Play");
		} else {
			BackgammonView.printInfo("No Doubles in Play");
		}

		BackgammonView.printInputOptions(activePlayer);
		// Game control loop
		while (!isGameOver) {

			this.switchActivePlayer();
			board.beginTurn();

			// Turn control loop
			boolean isTurnOver = false;
			while (!isTurnOver) {
				BackgammonView.printInfo(activePlayer.toString() + " it is your turn!");
				BackgammonView.printScores(matchLength, player1, player2);
				BackgammonView.printBoard(this, activePlayer);

				String input = "";
				if (testMode && filescan.hasNextLine()) {
					// convert input string to upper case in order to accept lower case inputs
					input = filescan.nextLine().toUpperCase();
				} else {
					input = BackgammonView.promptPlayerForInput(scan);
					testMode = false;
				}

				if (input.equals("QUIT")) {
					// Quit Game
					BackgammonView.printQuitMessage();
					isTurnOver = true;
					isGameOver = true;
				} else if (input.equals("ROLL")) {
					if (board.isDiceRolled()) {
						BackgammonView.printError("Cannot re-roll dice");
					} else {
						// roll the dice
						board.rollDice(activePlayer);
						int[] roll = board.getLatestDiceRoll();
						BackgammonView.printInfo(activePlayer + " Rolled: " + Integer.toString(roll[0]) + " & "
								+ Integer.toString(roll[1]));

						if (board.noMoveAvailable(activePlayer)) {
							isTurnOver = board.isTurnOver();
							BackgammonView.printInfo("No Moves Available, Ending Turn");
						}
					}
				} else if (input.equals("PIP")) {
					// "pip" command to report the pip count for both players
					BackgammonView.printPipCounts(board, player1, player2);
				} else if (input.length() == 1 && board.getMoveKeys().contains(input.charAt(0))) {
					// User single alphabetical input to select a move
					BackgammonView.printInfo("Selected Move Option: " + input);
					board.selectMove(input.charAt(0), activePlayer);
					isTurnOver = board.isTurnOver();

				} else if (input.equals("HINT")) {
					BackgammonView.printInputOptions(activePlayer);
				} else if (input.matches("DICE[1-6][1-6]")) {

					if (board.isDiceRolled()) {
						BackgammonView.printError("Cannot re-roll dice");
					} else {
						// set roll
						int roll1 = Character.getNumericValue(input.charAt(5));
						int roll2 = Character.getNumericValue(input.charAt(7));
						board.setRolls(roll1, roll2, activePlayer);
						int[] roll = { roll1, roll2 };
						BackgammonView.printInfo(activePlayer + " Rolled: " + Integer.toString(roll[0]) + " & "
								+ Integer.toString(roll[1]));
					}
					// TODO should change logic here to use regex - not .contains
				} else if (input.contains("TEST")) {
					try {
						File file = new File(input.split(" ")[1].toLowerCase());
						filescan = new Scanner(file);
						testMode = true;
					} catch (Exception e) {
						BackgammonView.printError(e.getMessage());
					}
				} else if (input.equals("DOUBLE") && activePlayer.canOfferDoubles()) {
					BackgammonView.printDoubleOffer(activePlayer, inactivePlayer);
					boolean validAnswer = false;
					while (!validAnswer) {
						String answer = BackgammonView.promptForDoublesAnswer(scan);
						if (answer.equals("ACCEPT")) {
							board.applyDouble();
							BackgammonView.printInfo("Double Accepted");
							BackgammonView.printInfo("Stake is now: " + board.getDoublingCubeMultiplier());
							activePlayer.setCanOfferDoubles(false);
							inactivePlayer.setCanOfferDoubles(true);
							validAnswer = true;
						} else if (answer.equals("REFUSE")) {
							BackgammonView.printInfo("Double Refused");
							validAnswer = true;
							isTurnOver = true;
							isGameOver = true;
						} else {
							// input error
							BackgammonView.printError("INVALID INPUT");
						}
					}
				} else {
					// input error
					BackgammonView.printError("INVALID INPUT: " + input);
					BackgammonView.printInputOptions(activePlayer);
				}
				if (board.isWon(activePlayer)) {
					isTurnOver = true;
					isGameOver = true;
				}
			}
		}

		// Calculate score addition
		int baseScore = 1; // for a single
		String winTypeString = "Single";
		if (board.isWon(activePlayer)) {
			if (board.isBackgammon(inactivePlayer)) {
				baseScore = 2;
				winTypeString = "Backgammon";
			} else if (board.isGammon(inactivePlayer)) {
				baseScore = 3;
				winTypeString = "Gammon";
			}
		}
		int stake = baseScore * board.getDoublingCubeMultiplier();
		activePlayer.addScore(stake);
		BackgammonView.printInfo(
				"Game Completed, " + activePlayer.toString() + " wins a "+winTypeString+ "! " + stake + " is added to their score");

		// print the game one last time
		BackgammonView.printBoard(this, activePlayer);

	}

	private void switchActivePlayer() {
		if (activePlayer == player1) {
			activePlayer = player2;
			inactivePlayer = player1;
		} else {
			activePlayer = player1;
			inactivePlayer = player2;
		}
	}

	private void setDoublingCube() {
		if (this.isDoublingCubeInPlay()) {
			player1.setCanOfferDoubles(true);
			player2.setCanOfferDoubles(true);
		} else {
			player1.setCanOfferDoubles(false);
			player2.setCanOfferDoubles(false);
		}
	}

	private void chooseFirstPlayerToMove() {
		BackgammonView.printInfo("Rolling Dice to see who goes first");

		int[] initialRolls = new int[2];
		do {
			initialRolls[0] = Dice.roll();
			initialRolls[1] = Dice.roll();

			BackgammonView.printStart(player1, player2, initialRolls);
			if (initialRolls[0] == initialRolls[1]) {
				BackgammonView.printInfo("Rolls are equal, let's roll again");
			}
		} while (initialRolls[0] == initialRolls[1]);

		if (initialRolls[0] > initialRolls[1]) {
			BackgammonView.printInfo(player1.toString() + " has a higher roll, so goes first");
			activePlayer = player2;
			inactivePlayer = player1;
		} else {
			BackgammonView.printInfo(player2.toString() + " has a higher roll, so goes first");
			activePlayer = player1;
			inactivePlayer = player2;
		}

	}

	public boolean isGameOver() {
		return isGameOver;
	}

	public boolean isDoublingCubeInPlay() {
		boolean inPlay = true;
		System.out.println(player1.getScore());
		System.out.println(player2.getScore());
		System.out.println(matchLength);
		System.out.println(match.hasCrawfordHappened());
		if (!match.hasCrawfordHappened() && ((matchLength - player1.getScore() == 1) || (matchLength - player2.getScore() == 1))) {
			inPlay = false;
		}
		return inPlay;
	}

	public int getDoublingCubePosition() {
		int position;
		if (player1.canOfferDoubles() && player2.canOfferDoubles()) {
			position = 0;
		} else if (player1.canOfferDoubles()) {
			position = 1;
		} else if (player2.canOfferDoubles()) {
			position = 2;
		} else {
			// error? //TODO
			position = 0;
		}
		// TODO Auto-generated method stub
		return position;
	}

}
