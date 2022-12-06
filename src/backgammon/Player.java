package backgammon;

/**
 * This program is the Player class
 * @author Sam Lynch
 *
 */

/**
 * A {@code Player} represents a player in a match of Backgammon
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
	 * set the players name to a string value
	 * 
	 * @param s the string to set the name of the player to
	 */
	public void setName(String s) {
		this.name = s;
	}
	
	@Override
	public String toString() {
		return name + " (" + colour + ")";
	}
	
	/**
	 * Sets whether the player can offer a double or not.
	 * 
	 * @param canDouble Whether or not the player can offer a double.
	 */
	public void setCanOfferDoubles(boolean canDouble) {
		this.canOfferDoubles = canDouble;
	}
	
	/**
	 * Returns whether or not a player can offer a double.
	 * 
	 * @return whether or not a player can offer a double.
	 */
	public boolean canOfferDoubles() {
		return this.canOfferDoubles;
	}
	
	
	/**
	 * Converts both ways between player perspective indexing and universal indexing.
	 * Players perspective indexing is equivalent to their own pip values
	 * Universal indexing is arbitrarily chosen as white's perspective for use in this program
	 * 
	 * @param index the index to be converted from player perspective to universal perspective OR the universal perspective index to be converted to player's perspective
	 * @return index in the alternate form.
	 */
	public int getAlternateIndex(int index) {
		return this.colour.getAlternateIndex(index);
	}
	
	/**
	 * get the colour of this player
	 * 
	 * @return the colour of this player
	 */
	public Colour getColour() {
		return colour;
	}

	/**
	 * get this player's score
	 * 
	 * @return the player's score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * increment the players score by an integer amount
	 * 
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
