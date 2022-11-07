package backgammon;

/**
 * This program is the Player class
 * @author Sam Lynch
 *
 */

/**
 * A {@code Player} represents a player
 */
public class Player {
	private String name;
	private Colour colour;

	/**
	 * Constructor for this class. Sets name and colour of player.
	 * 
	 * @param colour Colour of player.
	 */
	public Player(Colour colour, String name) {
		this.name = name;
		this.colour = colour;
	}

	/**
	 * Overwrites inbuilt toString() method for this class.
	 */
	public String toString() {
		return name + " (" + colour + ")";
	}

	public int getPointIndex(int equivalentWhiteIndex) {

		int index;
		if (colour == Colour.WHITE) {
			index = equivalentWhiteIndex;
		} else {
			index = 25 - equivalentWhiteIndex;
		}
		return index;
	}

	public Colour getColour() {
		return colour;
	}

}
