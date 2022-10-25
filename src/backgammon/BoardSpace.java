package backgammon;
import java.util.ArrayList;

/**
 * This program is the BoardSpace class
 * @author Sam Lynch
 *
 */

/**
 * A {@code BoardSpace} represents a space on the Backgammon Board.
 */

public abstract class BoardSpace {
	private Colour ColourOfSpace;
	private ArrayList<Checker> StackOfCheckers = new ArrayList<Checker>();
	
	/**
	 * Constructor for the class
	 */
	public BoardSpace() {
		
	}
	
	/**
	 * Checks if checker can be placed on the space. 
	 * @param start checker to be moved.
	 * @return Whether or not a checker can be placed on this space.
	 */
	public abstract boolean canPlace(Checker start);
	
	/**
	 * Checks if a checker can be taken from the stack.
	 * @return Whether or not a checker can be taken from the stack.
	 */
	public abstract boolean canTake();
	
	/**
	 * Returns the amount of checkers in the space.
	 * @return Number of checkers in space.
	 */
	public int getNumCheckers() {
		int Size = StackOfCheckers.size();
		return Size;
	}
	
	/**
	 * Sets the colour of the space.
	 * @param colour Colour of space.
	 */
	public void setColour(Colour colour) {
		ColourOfSpace = colour;
	}
	
	/**
	 * Returns the colour of the space.
	 * @return Colour of space.
	 */
	public Colour getColour() {
		return ColourOfSpace;
	}
	
}
