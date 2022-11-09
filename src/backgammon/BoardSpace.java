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
	private Colour colourOfSpace;
	private ArrayList<Checker>stackOfCheckers = new ArrayList<Checker>();
	
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
	 * Returns a string of the space.
	 * @param player Active player.
	 * @return String of space.
	 */
	public abstract String toString(Player player);
	
	/**
	 * Gets how far the space is away from the beared off space for the active player.
	 * @param player Active player.
	 * @return Value of how far the space is from beared off space.
	 */
	public abstract int getPipValue(Player player);
	
	/**
	 * Returns the amount of checkers in the space.
	 * @return Number of checkers in space.
	 */
	public int getNumCheckers() {
		int size = stackOfCheckers.size();
		return size;
	}
	
	/**
	 * Sets the colour of the space.
	 * @param colour Colour of space.
	 */
	public void setColour(Colour colour) {
		colourOfSpace = colour;
	}
	
	/**
	 * Returns the colour of the space.
	 * @return Colour of space.
	 */
	public Colour getColour() {
		return colourOfSpace;
	}
	
	/**
	 * Checks if the space is empty or not. 
	 * @return Whether or not the space is empty.
	 */
	public boolean isEmpty() {
		boolean empty = false;
		if(stackOfCheckers.isEmpty()) {
			empty = true;
		}
		return empty;
	}
	
	/**
	 * Gets the full stack in this space.
	 * @return Stack of Checkers in space.
	 */
	public final ArrayList<Checker> getStack(){
		return stackOfCheckers;
	}
	
	/**
	 * Adds a checker to the space.
	 * @param ToAdd Checker to be added to space.
	 */
	public void addChecker(Checker toAdd) {
		stackOfCheckers.add(toAdd);
	}
	
	/**
	 * Adds a newly created checker to the space.
	 * @param ToAdd Checker to be added to space.
	 */
	public void addNewCheckers(int number, Colour colour) {
		for(int i = 0; i<number;i++) {
		stackOfCheckers.add(new Checker(colour));
		}
	}
	
	/**
	 * Gets the checker at the top of the space.
	 * @return Checker at top of space.
	 */
	public Checker getTopChecker() {
		Checker top = null;
		if(!this.isEmpty()) {
			int size = this.getNumCheckers();
			top = stackOfCheckers.get(size-1);
		}
		return top;
	}
	
	/**
	 * Removes the checker at the top of the space and returns this checker.
	 * @return Checker that was removed.
	 */
	public Checker removeChecker() {
		int size = this.getNumCheckers();
		Checker toRemove = this.getTopChecker();
		if(!this.isEmpty()) {
			stackOfCheckers.remove(size-1);
		}
		return toRemove;
	}

}
