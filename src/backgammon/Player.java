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
	private boolean canOfferDoubles;
	private int score = 0;

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
	
	/**
	 * Sets whether the player can offer a double or not.
	 * @param canDouble Whether or not the player can offer a double.
	 */
	public void setCanOfferDoubles(boolean canDouble) {
		this.canOfferDoubles = canDouble;
	}
	
	/**
	 * Returns whether or not a player can offer a double.
	 * @return whether or not a player can offer a double.
	 */
	public boolean canOfferDoubles() {
		return this.canOfferDoubles;
	}
	

	public int getAlternateIndex(int equivalentWhiteIndex) {
		return this.colour.getAlternateIndex(equivalentWhiteIndex);
	}

	public Colour getColour() {
		return colour;
	}

	/**
	 * @return the player's score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * @param scoreToAdd the score to add to the player's score
	 */
	public void addScore(int scoreToAdd) {
		this.score = this.score + scoreToAdd;
	}
	
	/**
	 * reset the players score to zero
	 */
	public void resetScore() {
		 this.score=0;
	}

}
