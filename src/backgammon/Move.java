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
	 * Returns where checker is moving from.
	 * 
	 * @return Source of checker.
	 */
	public BoardSpace getSource(BackgammonBoard board, Colour colour) {
		// TODO logic to return the boardSpace
		return board.getBoardSpaceByPipValue(sourcePipValue, colour);
	}

	/**
	 * Returns where checker is moving to.
	 * 
	 * @param colour
	 * @param board
	 * 
	 * @return Destination of checker.
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

	public Object getRoll() {

		return this.roll;
	}

	@Override
	public int compareTo(Move o) {
		int comp = roll - o.roll;
		
		if (comp == 0) {
			comp = sourcePipValue - o.sourcePipValue;
			if(comp == 0) {
				comp = destinationPipValue - o.destinationPipValue;
			}
		}

		return comp;

	}
	
	@Override
    public boolean equals(Object o) {
		return this.compareTo((Move) o)==0;
	}
	
}
