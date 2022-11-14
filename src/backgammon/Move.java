package backgammon;
/**
 * This program is the Move class.
 * @author Sam Lynch
 */

/**
 * A {@code Move} class represents a move from one space to another in backgammon.
 *
 */
public class Move {

	private int roll;
	private BoardSpace source;
	private BoardSpace destination;
	
	/**
	 * Constructor for the class.
	 * @param distance Distance a checker is moving.
	 * @param src Where checker is moving from.
	 * @param dest Where checker can move to.
	 */
	public Move(int distance, BoardSpace src, BoardSpace dest) {
		this.roll = distance;
		this.source = src;
		this.destination = dest;
	}
	


	/**
	 * Returns where checker is moving from.
	 * @return Source of checker.
	 */
	public BoardSpace getSource() {
		return this.source;
	}
	
	/**
	 * Returns where checker is moving to.
	 * @return Destination of checker.
	 */
	public BoardSpace getDestination() {
		return this.destination;
	}
	
	/**
	 * Returns a string of the move.
	 * @param player Active Player.
	 * @return String of the move.
	 */
	public String toString(Player player) {
		return  "["+roll+"]  " + source.toString(player) + "->" + destination.toString(player);
	}

	public Object getRoll() {
		return this.roll;
	}
}
