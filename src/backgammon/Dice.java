package backgammon;

/**
 * This program is the Dice class
 * @author Sam Lynch
 *
 */

/**
 * A {@code Dice} represents a dice
 */
public class Dice {
	private final static double SIDES_ON_DICE = 6.0;
	
	/**
	 * Constructor for this class.
	 */
	public Dice() {
		
	}
	
	/**
	 * Rolls the dice.
	 * @return The result of the roll
	 */
	public final static int Roll () {
		double roll = Math.random()*SIDES_ON_DICE+1.0;
		return (int) roll;
	}
}

