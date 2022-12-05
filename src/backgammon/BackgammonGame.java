package backgammon;

import java.util.Scanner;

public class BackgammonGame {

	// user input scanner
	private Scanner scan;

	// Players of the match
	private Player player1;
	private Player player2;

	// Alternate the turn player
	private Player activePlayer;
	private Player inactivePlayer;

	private int matchLength;

	// board for the game
	private BackgammonBoard board = new BackgammonBoard();
//	private BackgammonBoard board = BackgammonBoard.createTestBoard("NEARLY OVER");

	public BackgammonBoard getBoard() {
		return board;
	}

	// Store whether the game is over
	boolean isGameOver = false;

	public BackgammonGame(Scanner s, Player p1, Player p2, int m) {
		this.scan = s;
		this.player1 = p1;
		this.player2 = p2;
		this.matchLength = m;
	}

	public void playGame() {

		this.chooseFirstPlayerToMove();
		BackgammonBoardView.pressEnterToContinue();

		this.resetDoublingCube();

		BackgammonBoardView.printInputOptions(activePlayer);

		// Game control loop
		while (!isGameOver) {

			this.switchActivePlayer();
			board.beginTurn();

			// Turn control loop
			boolean isTurnOver = false;
			while (!isTurnOver) {
				BackgammonBoardView.printInfo(activePlayer.toString() + " it is your turn!");
				BackgammonBoardView.printScores(matchLength, player1, player2);
				BackgammonBoardView.printBoard(this, activePlayer);

				BackgammonBoardView.promptPlayerForInput();

				// convert input string to upper case in order to accept lower case inputs
				String input = scan.next().toUpperCase();

				if (input.equals("QUIT")) {
					// Quit Game
					BackgammonBoardView.printInfo("Game terminated, thanks for playing!");
					isTurnOver = true;
					isGameOver = false;
				} else if (input.equals("ROLL")) {
					if (board.isDiceRolled()) {
						BackgammonBoardView.printError("Cannot re-roll dice");
					} else {
						// roll the dice
						board.rollDice(activePlayer);
						int[] roll = board.getLatestDiceRoll();
						BackgammonBoardView.printInfo(activePlayer + " Rolled: " + Integer.toString(roll[0]) + " & "
								+ Integer.toString(roll[1]));

						if (board.noMoveAvailable(activePlayer)) {
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
					board.selectMove(input.charAt(0), activePlayer);
					isTurnOver = board.isTurnOver();

				} else if (input.equals("HINT")) {
					BackgammonBoardView.printInputOptions(activePlayer);
				} else if (input.matches("DICE[1-6][1-6]")) {
					if (board.isDiceRolled()) {
						BackgammonBoardView.printError("Cannot re-roll dice");
					} else {
						// set roll
						int roll1 = Character.getNumericValue(input.charAt(4));
						int roll2 = Character.getNumericValue(input.charAt(5));
						board.setRolls(roll1, roll2, activePlayer);
						int[] roll = { roll1, roll2 };
						BackgammonBoardView.printInfo(activePlayer + " Rolled: " + Integer.toString(roll[0]) + " & "
								+ Integer.toString(roll[1]));
					}
				} else if (input.equals("DOUBLE") && activePlayer.canOfferDoubles()) {
					BackgammonBoardView.printDoubleOffer(activePlayer, inactivePlayer);
					BackgammonBoardView.printDoubleOptions();
					boolean validAnswer = false;
					while (!validAnswer) {
						String answer = scan.next().toUpperCase();
						if (answer.equals("ACCEPT")) {
							board.applyDouble();
							BackgammonBoardView.printInfo("Double Accepted");
							BackgammonBoardView.printInfo("Stake is now: " + board.getDoublingCubeMultiplier());
							activePlayer.setCanOfferDoubles(false);
							inactivePlayer.setCanOfferDoubles(true);
							validAnswer = true;
						} else if (answer.equals("REFUSE")) {
							BackgammonBoardView.printInfo("Double Refused");
							BackgammonBoardView
									.printInfo("Game Completed, " + activePlayer.toString() + " is the winner!");
							validAnswer = true;
							isTurnOver = true;
							isGameOver = false;
						} else {
							// input error
							BackgammonBoardView.printError("INVALID INPUT");
							BackgammonBoardView.printDoubleOptions();
						}
					}
				} else {
					// input error
					BackgammonBoardView.printError("INVALID INPUT");
					BackgammonBoardView.printInputOptions(activePlayer);
				}
				if (board.isWon(activePlayer)) {
					BackgammonBoardView.printInfo(
							"Game Completed, " + activePlayer.toString() + " is the winner! Thanks for playing!");
					isTurnOver = true;
					isGameOver = true;
				}
			}
		}
		// print the game one last time
		BackgammonBoardView.printBoard(this, activePlayer);

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

	private void resetDoublingCube() {
		player1.setCanOfferDoubles(true);
		player2.setCanOfferDoubles(true);
	}

	private void chooseFirstPlayerToMove() {
		BackgammonBoardView.printInfo("Rolling Dice to see who goes first");

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
			BackgammonBoardView.printInfo(player1.toString() + " has a higher roll, so goes first");
			activePlayer = player2;
			inactivePlayer = player1;
		} else {
			BackgammonBoardView.printInfo(player2.toString() + " has a higher roll, so goes first");
			activePlayer = player1;
			inactivePlayer = player2;
		}

	}

	public boolean isGameOver() {
		return isGameOver;
	}

	public boolean isDoublingCubeInPlay() {
		// TODO Auto-generated method stub
		return true;
	}

	public int getDoublingCubePosition() {
		int position;
		if (player1.canOfferDoubles() && player2.canOfferDoubles()) {
			position = 0;
		}else if(player1.canOfferDoubles()) {
			position = 1;
		}else if(player2.canOfferDoubles()) {
			position = 2;
		}else {
			//error? //TODO 
			position = 0;
		}
		// TODO Auto-generated method stub
		return position;
	}

}
