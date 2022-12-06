package backgammon;
/**
 * This program is the Move class.
 * @author Sam Lynch
 */

/**
 * A {@code Move} class represents a move from one space to another in
 * backgammon.
 *
 */
public class Move implements Comparable<Move> {

	private int roll;
	private int sourcePipValue;
	private int destinationPipValue;

	/**
	 * Constructor for the class.
	 * 
	 * @param distance Distance a checker is moving.
	 * @param src      Where checker is moving from.
	 * @param dest     Where checker can move to.
	 */
	public Move(int distance, int srcPipValue, int destPipValue) {
		this.roll = distance;
		this.sourcePipValue = srcPipValue;
		this.destinationPipValue = destPipValue;
	}

	/**
	 * Returns the boardspace the checker is moving from.
	 * 
	 * @param board the board being played on
	 * @param colour the player whose perspective this move is for
	 * @return Source boardspace of checker.
	 */
	public BoardSpace getSource(BackgammonBoard board, Colour colour) {
		return board.getBoardSpaceByPipValue(sourcePipValue, colour);
	}

	/**
	 * Returns the boardspace the checker is moving to.
	 * 
	 * @param board the board being played on
	 * @param board
	 * @param colour the player whose perspective this move is for
	 * @return Destination boardspace of checker.
	 */
	public BoardSpace getDestination(BackgammonBoard board, Colour colour) {
		return board.getBoardSpaceByPipValue(destinationPipValue, colour);
	}

	@Override
	public String toString() {
		String moveString = "";
		if (sourcePipValue == 25) {
			moveString = "[" + roll + "] Bar ->" + destinationPipValue;
		} else if (destinationPipValue == 0) {
			moveString = "[" + roll + "]  " + sourcePipValue + "-> Off";
		} else {
			moveString = "[" + roll + "]  " + sourcePipValue + "->" + destinationPipValue;
		}
		return moveString;
	}

	/**
	 * get the distance that is being moved in this move
	 * 
	 * @return the roll amount
	 */
	public Object getRoll() {
		return this.roll;
	}

	@Override
	public int compareTo(Move o) {
		int comp = roll - o.roll;
		
		if (comp == 0) {
			comp = sourcePipValue - o.sourcePipValue;
		}

		return comp;

	}
	
	@Override
    public boolean equals(Object o) {
		return this.compareTo((Move) o)==0;
	}
	
}
