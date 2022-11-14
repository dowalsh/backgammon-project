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
	public Move(int distance, BoardSpace src, BoardSpace dest, Player activePlayer) {
		this.roll = distance;
		this.sourcePipValue = src.getPipValue(activePlayer);
		this.destinationPipValue = dest.getPipValue(activePlayer);
	}

	/**
	 * Returns where checker is moving from.
	 * 
	 * @return Source of checker.
	 */
	public BoardSpace getSource(BackgammonBoard board, Player activePlayer) {
		// TODO logic to return the boardSpace
		return board.getBoardSpaceByPipValue(sourcePipValue, activePlayer);
	}

	/**
	 * Returns where checker is moving to.
	 * 
	 * @param activePlayer
	 * @param board
	 * 
	 * @return Destination of checker.
	 */
	public BoardSpace getDestination(BackgammonBoard board, Player activePlayer) {
		return board.getBoardSpaceByPipValue(destinationPipValue, activePlayer);
	}

	/**
	 * Returns a string of the move.
	 * 
	 * @param player Active Player.
	 * @return String of the move.
	 */
	public String toString(Player player) {
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

	public Object getRoll() {

		return this.roll;
	}

	@Override
	public int compareTo(Move o) {
//		return toString().compareTo(o.toString());

		int comp;
		int rollDiff = roll - o.roll;
		if (rollDiff != 0) {
			comp = rollDiff;
		} else {
			int sourceDiff = o.sourcePipValue - sourcePipValue;
			if (sourceDiff != 0) {
				comp = sourceDiff;
			} else {
				int destDiff = o.destinationPipValue - destinationPipValue;
				comp = destDiff;

			}
		}

		return comp;

	}
}
