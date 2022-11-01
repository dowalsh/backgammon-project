package backgammon;

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

	static int MAX_DISPLAY_CHECKERS = 5;
	static int HEADER_FOOTER_SIZE = 2;


	/**
	 * Formats and prints the state of the game of backgammon to the console
	 * 
	 * @param board  the BackgammonBoard object
	 * @param player the player to print the board perspective for
	 */
	public static void print(BackgammonBoard board, Player player) {

		// 2D array to display game

		//2 rows for the top, 2 for the bottom, 1 for space and N for each side of checkers
		int rows = 2*HEADER_FOOTER_SIZE +1 + MAX_DISPLAY_CHECKERS*2; 
		System.out.println(rows);
		// 1 col for each checker stack, 1 for each edge, 3 for the bar
		int columns = 12 + 2 + 3;
		String[][] table = new String[rows][columns];

		// Initialise array with empty strings
		for (int row = 0; row < table.length; row++) {
			for (int col = 0; col < table[row].length; col++) {
				table[row][col] = "";
			}
		}

		// Edges and Bar
		int col_line = 0;
		for (int row = HEADER_FOOTER_SIZE; row < table.length-HEADER_FOOTER_SIZE; row++) {
			table[row][col_line] = "|";
		}
		col_line += 7;
		for (int row = HEADER_FOOTER_SIZE; row < table.length-HEADER_FOOTER_SIZE; row++) {
			table[row][col_line] = "|";
		}
		col_line ++;
			table[0][col_line] = "B";
			table[table.length-1][col_line] = "B";

		col_line ++;
		for (int row = HEADER_FOOTER_SIZE; row < table.length-HEADER_FOOTER_SIZE; row++) {
			table[row][col_line] = "|";
		}
		col_line += 7;
		for (int row = HEADER_FOOTER_SIZE; row < table.length-HEADER_FOOTER_SIZE; row++) {
			table[row][col_line] = "|";
		}

		// Top and bottom
		int row_line = HEADER_FOOTER_SIZE-1;
		for (int col = 0; col < table[row_line].length; col++) {
			table[row_line][col] = "===";
		}
		row_line = table.length - HEADER_FOOTER_SIZE;
		for (int col = 0; col < table[row_line].length; col++) {
			table[row_line][col] = "===";
		}

		
		BoardSpace[] bs = board.getBoardSpaces();
		// points from whites perspective
		int point_row = table.length - HEADER_FOOTER_SIZE -1;
		int point_col = table[0].length - 2;
		for (int i = 1; i <= 6; i++) {
			fillCheckers(table, point_row, point_col, false, bs[i - 1]);
			table[point_row+2][point_col] = Integer.toString(player.getPointIndex(i));
			point_col--;
		}
		point_col -= 3;
		for (int i = 7; i <= 12; i++) {
			fillCheckers(table, point_row, point_col, false, bs[i - 1]);
			table[point_row+2][point_col] = Integer.toString(player.getPointIndex(i));
			point_col--;
		}
		point_col = 1;
		point_row = HEADER_FOOTER_SIZE;
		for (int i = 13; i <= 18; i++) {
			fillCheckers(table, point_row, point_col, true, bs[i - 1]);
			table[point_row-2][point_col] = Integer.toString(player.getPointIndex(i));
			point_col++;
		}
		point_col += 3;
		for (int i = 19; i <= 24; i++) {
			fillCheckers(table, point_row, point_col, true, bs[i - 1]);
			table[point_row-2][point_col] = Integer.toString(player.getPointIndex(i));
			point_col++;
		}

		// Create output format String & print
		final StringBuilder formatString = new StringBuilder("");
		// Header
		formatString.append("\n");
		// Main Board
		for (int row = 0; row < table.length; row++) {
			for (int c = 0; c < table[row].length; c++) {
				formatString.append(String.format("%3s", table[row][c]));
			}
			formatString.append("\n");
		}
		System.out.println(formatString.toString());

	}

	private static void fillCheckers(String[][] table, int starting_row_index, int col_index, boolean fillDownwards,
			BoardSpace b) {
		// table is the 2D array
		// index is where to begin filling from
		// fillDownwards indicates if the checkers should be filled starting at the top
		//this can be used to fill the bars as well
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

	
}