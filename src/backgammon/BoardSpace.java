package backgammon;
import java.util.ArrayList;

/**
 * This program is the Space class
 * @author Sam Lynch
 *
 */

/**
 * A {@code Space} represents a space on the Backgammon Board.
 */

public abstract class BoardSpace {
	private colour ColourOfSpace;
	private ArrayList<Checker> StackOfCheckers = new ArrayList<Checker>();
	
	/**
	 * Constructor for the class
	 */
	public BoardSpace() {
		
	}
	
	/**
	 * Checks if checker can be placed on the space. 
	 * @param start checker to be moved
	 * @return whether or not a checker can be placed on this space
	 */
	public abstract boolean canPlace(Checker start);
	
	/**
	 * Checks if a checker can be taken from this stack.
	 * @return Whether or not a
	 */
	public abstract boolean canTake();
	
	public int getNumCheckers() {
		int Size = StackOfCheckers.getSize();
		return Size;
	}
	
	public void setColour(colour Colour) {
		ColourOfSpace = Colour;
	}
	
	public colour getColour() {
		return ColourOfSpace;
	}
	
}
