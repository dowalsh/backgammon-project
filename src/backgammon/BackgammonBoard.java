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

	private int[] latestDiceRoll = new int[2];

	private boolean isDiceRolled = false;
	/*
	 * Might be a good option architecture-wise to store these separately? Yes I
	 * think so(Sam) private Bar blackBar; private Bar whiteBar; private
	 * bearedOffSpace blackBearedOffSpace; private bearedOffSpace
	 * whiteBearedOffSpace;
	 */

	public BoardSpace[] getBoardSpaces() {
		return boardSpaces;
	}

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

		// create and add bars
		boardSpaces[index] = new Bar(Colour.BLACK);
		index++;
		boardSpaces[index] = new Bar(Colour.WHITE);
		index++;

		// Create and add bearedOffSpaces
		boardSpaces[index] = new BearedOffSpace(Colour.BLACK);
		index++;
		boardSpaces[index] = new BearedOffSpace(Colour.WHITE);

	}

	public boolean isWon() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * @return the latest Dice Roll
	 */
	public int[] getLatestDiceRoll() {
		return latestDiceRoll;
	}

	/**
	 * rolls two dice and saves the rolls as the latest roll
	 */
	public void rollDice() {
		this.latestDiceRoll[0] = Dice.roll();
		this.latestDiceRoll[1] = Dice.roll();
		isDiceRolled = true;

		// TODO set available Dice Roll List here

	}

	public void endTurn() {
		this.latestDiceRoll[0] = 0;
		this.latestDiceRoll[1] = 0;
		isDiceRolled = false;
	}

	public boolean getIsDiceRolled() {
		return isDiceRolled;
	}

	/**
	 * Gets the pip count for a player
	 * 
	 * @param player the player to calculate the pip count for
	 */
	public int getPipCount(Player player) {
		int pipCount = 0;

		// For Every Boardspace
		for (BoardSpace b : boardSpaces) {
			int numCheckers = b.getNumCheckers();
			// Get number of player's checkers on that space
			if (numCheckers > 0) {
				if(b.getTopChecker().getColour()== player.getColour()) {
					// multiply this number this space's pip value
					// and add to running total
					pipCount += numCheckers * b.getPipValue(player);
				}
			}

		}

		return pipCount;
	}
}