package backgammon;

import java.io.IOException;
import java.util.Scanner;

/**
 * This program is the BackgammonBoardView class
 * @author dylan
 *
 */

/**
 * The BackgammonBoardView Class is used to display a game of Backgammon in the
 * console
 */
public class BackgammonBoardView {

	public final static int DISPLAY_WIDTH = 51;
	public final static int MAX_DISPLAY_CHECKERS = 5;
	public final static int HEADER_FOOTER_SIZE = 2;
	public final static int LEFT_INDENT = 1;
	public final static int RIGHT_INDENT = 1;
	public final static int QUADRANT_WIDTH = 6;
	public final static int BAR_WIDTH = 3;
	public final static int DOUBLING_CUBE_COLUMN_INDEX = 0;

	/**
	 * Formats and prints the state of the game of backgammon to the console
	 * 
	 * @param board  the BackgammonBoard object
	 * @param player the player to print the board perspective for
	 */
	public static void printBoard(BackgammonGame game, Player player) {

		BackgammonBoard board = game.getBoard();
		BoardSpace[] bs = board.getBoardSpaces();

		// 2D array to display game

		// 2 rows for the top, 2 for the bottom, 1 for space and N for each side of
		// checkers
		int num_rows = 2 * HEADER_FOOTER_SIZE + 1 + MAX_DISPLAY_CHECKERS * 2;
		// LEFT_INDENT = 1 for the doubling cube, 1 col for each checker stack, 1 for
		// each edge, BAR_WIDTH = 3 for the bar, and RIGHT_INDENT = 1 for the beared off
		// spaces
		int num_columns = LEFT_INDENT + 2 * QUADRANT_WIDTH + 2 + BAR_WIDTH + RIGHT_INDENT;

		String[][] table = new String[num_rows][num_columns];

		fillTableWithEmptyStrings(table);

		int top_checkers_row_index = table.length - HEADER_FOOTER_SIZE - 1;
		int bottom_checkers_row_index = HEADER_FOOTER_SIZE;
		int centre_row_index = num_rows / 2; // Integer division to round down

		// Doubling Cube
		if (game.isDoublingCubeInPlay()) {
			int displayIntegerValue = board.getDoublingCubeMultiplier();
			;
			if (displayIntegerValue == 1) {
				displayIntegerValue = 64;
			}
			String displayString = Integer.toString(displayIntegerValue);

			switch (game.getDoublingCubePosition()) {
			case 1:
				// Player 1 (WHITE) has the cube
				table[top_checkers_row_index][DOUBLING_CUBE_COLUMN_INDEX] = displayString;
				break;
			case 2:
				// Player 2 (BLACK) has the cube
				table[bottom_checkers_row_index][DOUBLING_CUBE_COLUMN_INDEX] = displayString;
				break;
			default:
				table[centre_row_index][DOUBLING_CUBE_COLUMN_INDEX] = displayString;

			}
		}

		// Left Edge
		int col_line = LEFT_INDENT;
		fillVerticalLine(table, col_line);

		// Bar
		col_line += QUADRANT_WIDTH + 1;
		fillVerticalLine(table, col_line);
		col_line++;
		table[0][col_line] = "B";
		table[table.length - 1][col_line] = "B";
		fillCheckers(table, bottom_checkers_row_index, col_line, true, bs[24]); // White bar
		fillCheckers(table, top_checkers_row_index, col_line, false, bs[25]); // Black bar
		col_line++;
		fillVerticalLine(table, col_line);

		// Right Edge
		col_line += QUADRANT_WIDTH + 1;
		fillVerticalLine(table, col_line);

		// Beared Off
		col_line++;
		fillCheckers(table, top_checkers_row_index, col_line, false, bs[26]); // White bear off
		fillCheckers(table, bottom_checkers_row_index, col_line, true, bs[27]); // Black bear off

		// Top and bottom lines
		int row_line = HEADER_FOOTER_SIZE - 1;
		for (int col = 0; col < table[row_line].length; col++) {
			table[row_line][col] = "===";
		}
		row_line = table.length - HEADER_FOOTER_SIZE;
		for (int col = 0; col < table[row_line].length; col++) {
			table[row_line][col] = "===";
		}

		// points from whites perspective
		// Starting in the bottom right of the board
		int point_col = table[0].length - RIGHT_INDENT - 2;
		for (int i = 1; i <= 6; i++) {
			fillCheckers(table, top_checkers_row_index, point_col, false, bs[i - 1]);
			table[top_checkers_row_index + 2][point_col] = Integer.toString(player.getAlternateIndex(i));
			point_col--;
		}
		// Move to bottom left of the board
		point_col -= BAR_WIDTH;
		for (int i = 7; i <= 12; i++) {
			fillCheckers(table, top_checkers_row_index, point_col, false, bs[i - 1]);
			table[top_checkers_row_index + 2][point_col] = Integer.toString(player.getAlternateIndex(i));
			point_col--;
		}
		// Move to top left of the board
		point_col = LEFT_INDENT + 1;
		for (int i = 13; i <= 18; i++) {
			fillCheckers(table, bottom_checkers_row_index, point_col, true, bs[i - 1]);
			table[bottom_checkers_row_index - 2][point_col] = Integer.toString(player.getAlternateIndex(i));
			point_col++;
		}
		// Move to top right of the board
		point_col += BAR_WIDTH;
		for (int i = 19; i <= 24; i++) {
			fillCheckers(table, bottom_checkers_row_index, point_col, true, bs[i - 1]);
			table[bottom_checkers_row_index - 2][point_col] = Integer.toString(player.getAlternateIndex(i));
			point_col++;
		}

		// Create output format String & print
		final StringBuilder formatString = new StringBuilder("");

		if (!game.isGameOver()) {
			if (board.isDiceRolled()) {
				int[] diceRoll = board.getLatestDiceRoll();
				formatString.append(board.legalMovesToString(player));
				formatString.append(Dice.toString(diceRoll));

			} else {
				formatString.append("To roll the dice enter command 'roll'\n");
			}
		}

		// Header
		formatString.append("\n");
		// Main Board
		for (int row = 0; row < table.length; row++) {
			for (int c = 0; c < table[row].length; c++) {
				formatString.append(String.format("%3s", table[row][c]));
			}
			formatString.append("\n");
		}

		print(formatString.toString());

	}

	private static void fillVerticalLine(String[][] table, int col_line) {
		for (int row = HEADER_FOOTER_SIZE; row < table.length - HEADER_FOOTER_SIZE; row++) {
			table[row][col_line] = "|";
		}
	}

	private static void fillTableWithEmptyStrings(String[][] table) {
		// Initialise array with empty strings
		for (int row = 0; row < table.length; row++) {
			for (int col = 0; col < table[row].length; col++) {
				table[row][col] = "";
			}
		}
	}

	public static void printStart(Player player1, Player player2, int[] roll) {

		String[][] diceFace1 = Dice.getDiceFace(roll[0]);
		String[][] diceFace2 = Dice.getDiceFace(roll[1]);

		String indent = " ".repeat(20);
		String indent1 = " ".repeat(15);

		final StringBuilder formatString = new StringBuilder("");
		formatString.append("\n");
		formatString.append(indent1);
		formatString.append(player1.toString() + "'s roll\n");
		formatString.append("\n");
		formatString.append(indent);
		formatString.append("+-------+\n");
		for (int i = 0; i < 3; i++) {
			formatString.append(indent);
			formatString.append("| ");
			for (int j = 0; j < 3; j++) {
				formatString.append(diceFace1[i][j]);
				formatString.append(" ");
			}
			formatString.append("|\n");
		}
		formatString.append(indent);
		formatString.append("+-------+\n");
		formatString.append("\n");
		formatString.append(indent1);
		formatString.append(player2.toString() + "'s roll\n");
		formatString.append("\n");
		formatString.append(indent);
		formatString.append("+-------+\n");
		for (int i = 0; i < 3; i++) {
			formatString.append(indent);
			formatString.append("| ");
			for (int j = 0; j < 3; j++) {
				formatString.append(diceFace2[i][j]);
				formatString.append(" ");
			}
			formatString.append("|\n");
		}
		formatString.append(indent);
		formatString.append("+-------+\n");

		print(formatString.toString());
	}

	private static void fillCheckers(String[][] table, int starting_row_index, int col_index, boolean fillDownwards,
			BoardSpace b) {
		// table is the 2D array
		// index is where to begin filling from
		// fillDownwards indicates if the checkers should be filled starting at the top
		// this can be used to fill the bars as well
		int numCheckers = b.getNumCheckers();
		if (numCheckers > 0) {
			String checkerString = b.getTopChecker().toString(); // this can be updated to depend on colour
			int numOverflowCheckers = 0;

			if (numCheckers > MAX_DISPLAY_CHECKERS) {
				// plus 1 because the number replacement takes up a checker space
				numOverflowCheckers = numCheckers - MAX_DISPLAY_CHECKERS + 1;
			}

			int row_index = starting_row_index;

			if (numOverflowCheckers > 0) {
				table[starting_row_index][col_index] = "+" + Integer.toString(numOverflowCheckers);
				numCheckers -= numOverflowCheckers;
				if (fillDownwards)
					row_index++;
				else
					row_index--;
			}

			while (numCheckers > 0) {
				table[row_index][col_index] = checkerString;
				numCheckers--;
				if (fillDownwards)
					row_index++;
				else
					row_index--;
			}

		}

	}

	public static void promptPlayerForInput() {
		print("\nPlease Enter Input: ");
	}

	/**
	 * Formats and prints an error message to the console
	 * 
	 * @param errorMessage String explanation of the error
	 */
	public static void printError(String errorMessage) {
		printBanner("ERROR: " + errorMessage, "*");
	}

	/**
	 * Formats and prints an informational message to the console
	 * 
	 * @param message String informational message
	 */
	public static void printInfo(String message) {
		printBanner(message, "-");
	}

	/**
	 * Formats and prints a message to the console
	 * 
	 * @param message String message
	 * @param symbol  String symbol to print at front and end of formatted message
	 */
	private static void printBanner(String message, String symbol) {
		int numSymbols = (DISPLAY_WIDTH - message.length() - 1) / 2;
		if (numSymbols <= 0)
			numSymbols = 3;
		String symbols = symbol.repeat(numSymbols);
		String messageLine = symbols + " " + message + " " + symbols;
		String banner = symbol.repeat(messageLine.length());
		print("\n" + banner + "\n" + messageLine + "\n" + banner);
	}

	/**
	 * Print string to console
	 * 
	 * @param message String message
	 */
	private static void print(String message) {
		System.out.println(message);

	}

	public static void printInputOptions(Player activePlayer) {
		printBanner("INPUT OPTIONS", "=");
		print("quit = Quit");
		print("roll = Roll the Dice");
		print("pip = Display Pip Counts");
		print("Enter a letter A,B,C etc to make a move");
		print("hint = Display all command options");
		print("dice<int><int> = Enter your desired roll");
		if (activePlayer.canOfferDoubles()) {
			print("double = Offer a double to the other player");
		}

	}

	public static void promptForPlayerName(int i) {
		Colour colour;
		if (i == 1)
			colour = Colour.WHITE;
		else
			colour = Colour.BLACK;
		print("Enter the name for player " + i + " (" + colour + "): ");
	}

	public static void printPipCounts(BackgammonBoard board, Player player1, Player player2) {
		printInfo("Pip Counts");
		print(player1 + ": " + board.getPipCount(player1.getColour()));
		print(player2 + ": " + board.getPipCount(player2.getColour()));
	}

	public static void printDoubleOffer(Player activePlayer, Player inactivePlayer) {
		printBanner("DOUBLE OFFER", "!");
		print("\n" + inactivePlayer.toString() + ", " + activePlayer.toString()
				+ " has offered you a double.\nWould you like to accept this offer and the stakes will be doubled?\nOr would you like to refuse and concede this game?");
	}

	public static void printDoubleOptions() {
		printBanner("INPUT OPTIONS", "=");
		print("accept = Accept the offer and stakes will be doubled");
		print("refuse = Refuse the offer and concede the game");
	}

	public static int getIntegerFromUser(String prompt, Scanner inputScanner) {
		int inputInteger = 0;
		boolean isValidInteger = false;
		do {
			print("\n" + prompt + ": ");
			String inputString = inputScanner.next();
			try {
				inputInteger = Integer.parseInt(inputString);
				isValidInteger = true;
			} catch (NumberFormatException e) {
				BackgammonBoardView.printError("Please Enter an Integer Value");
			}
		} while (isValidInteger == false);

		return inputInteger;
	}

	public static void pressEnterToContinue() {
		print("\n> Press Enter To Continue: ");
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void printScores(int matchLength, Player player1, Player player2) {
		print("\n~~~ SCORES ~~~\n" + player1.toString() + ": " + player1.getScore() + "\n" + player2.toString() + ": "
				+ player2.getScore() + "\nMatch Length: " + matchLength + "\n");
	}

}