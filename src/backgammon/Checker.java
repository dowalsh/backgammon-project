package backgammon;

/**
 * This program is the Checker class
 * @author dylan
 *
 */

/**
 * A {@code Checker} object represents a single checker in the game of
 * backgammon
 */
public class Checker {

	private Colour colour;

	/**
	 * Constructor for class
	 * 
	 * @param colour the colour of this checker
	 */
	public Checker(Colour colour) {
		this.colour = colour;
	}

	/**
	 * @return the colour of this checker
	 */
	public Colour getColour() {
		return colour;
	}

	@Override	
	public String toString() {
		String checkerString = new String();
		
		if(this.colour == Colour.BLACK) {
			checkerString = "*";
		}else {
			checkerString = "o";
		}
				
		return checkerString;
	}
}
