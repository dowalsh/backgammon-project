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
	
	public abstract int getPipValue(Player player);
	
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
	
	/**
	 * Checks if the space is empty or not. 
	 * @return Whether or not the space is empty.
	 */
	public boolean isEmpty() {
		boolean Empty = false;
		if(StackOfCheckers.isEmpty()) {
			Empty = true;
		}
		return Empty;
	}
	
	/**
	 * Gets the full stack in this space.
	 * @return Stack of Checkers in space.
	 */
	public final ArrayList<Checker> getStack(){
		return StackOfCheckers;
	}
	
	/**
	 * Adds a checker to the space.
	 * @param ToAdd Checker to be added to space.
	 */
	public void addChecker(Checker ToAdd) {
		StackOfCheckers.add(ToAdd);
	}
	
	/**
	 * Adds a newly created checker to the space.
	 * @param ToAdd Checker to be added to space.
	 */
	public void addNewCheckers(int number, Colour colour) {
		for(int i = 0; i<number;i++) {
		StackOfCheckers.add(new Checker(colour));
		}
	}
	
	/**
	 * Gets the checker at the top of the space.
	 * @return Checker at top of space.
	 */
	public Checker getTopChecker() {
		Checker Top = null;
		if(!this.isEmpty()) {
			int Size = this.getNumCheckers();
			Top = StackOfCheckers.get(Size-1);
		}
		return Top;
	}
	
	/**
	 * Removes the checker at the top of the space and returns this checker.
	 * @return Checker that was removed.
	 */
	public Checker removeChecker() {
		int Size = this.getNumCheckers();
		Checker ToRemove = this.getTopChecker();
		if(!this.isEmpty()) {
			StackOfCheckers.remove(Size-1);
		}
		return ToRemove;
	}

}
