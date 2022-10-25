package backgammon;

/**
 * This program is the Point class
 * @author Sam Lynch
 *
 */

/**
 * A {@code Point} is an extension of the BoardSpace class, representing a point on the backgammon board
 */
public class Point extends BoardSpace{
	
	/**
	 * Constructor for this class
	 */
	public Point(int number) {
		// unsure if this is correct
		// basically  using the odd/even rule to colour them
		if(number%2 == 0) {
			this.setColour(Colour.BLACK);
		}else {
			this.setColour(Colour.WHITE);
		}
	}
	
	public boolean canPlace(Checker start) {
		//Needs to be updated
		return true;
	}
	
	public boolean canTake() {
		//Needs to be updated
		return true;
	}

}
