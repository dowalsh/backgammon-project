package backgammon;

/**
 * This program is the BackgammonBoard class
 * @author dylan
 *
 */

/**
 * A {@code BackgammonBoard} object represents a backgammon board
 */
public class BackgammonBoard {

	private BoardSpace[] boardSpaces = new BoardSpace[24 + 2 + 2];
	
	/*
	 * Might be a good option architecture-wise to store these separately? Yes I think so(Sam)
	private Bar blackBar;
	private Bar whiteBar;
	private bearedOffSpace blackBearedOffSpace;
	private bearedOffSpace whiteBearedOffSpace;
	*/


	/**
	 * Constructor for the class
	 */
	public BackgammonBoard() {

		int index = 0;

		// create and add points
		while (index < 24) {
			boardSpaces[index] = new Point(index + 1);
			index++;
		}

//		// create and add bars
//		boardSpaces[index] = new Bar(Colour.BLACK);
//		index++;
//		boardSpaces[index] = new Bar(Colour.WHITE);
//		index++;
//
//		// Create and add bearedOffSpaces
//		boardSpaces[index] = new bearedOffSpace(Colour.BLACK);
//		index++;
//		boardSpaces[index] = new bearedOffSpace(Colour.WHITE);

	}
}