package backgammon;

/**
 * This program is the BearedOffSpace class
 * @author Sam Lynch
 *
 */

/**
 * A {@code BearedOffSpace} is an extension of the BoardSpace class, representing a beared off space on the backgammon board
 */

public class BearedOffSpace extends BoardSpace {
	
	/**
	 * Constructor for this class.
	 * @param ColourOfSpace Colour of the space.
	 */

	public BearedOffSpace(Colour colourOfSpace) {
		this.setColour(colourOfSpace);
	}

	//Copy Constructor
	public BearedOffSpace(BearedOffSpace b) {
		super(b);
	}

	@Override
	public boolean canPlace(Checker checkerToBePlaced) {
		boolean place = false;
		if(checkerToBePlaced.getColour().equals(this.getColour())) {
			place = true;
		}
		return place;
	}
	
	@Override
	public boolean canTake(Colour playerColour) {
		return false;
	}
	
	@Override
	public int getPipValue(Colour playerColour) {
		return 0;
	}
	
	/**
	 * Checks if the beared off space is full, thus if the player has won.
	 * @return Whether or not the beared off space is full.
	 */
	public boolean isFull() {
		boolean full = false;
		if(this.getNumCheckers() == 15) {
			full = true;
		}
		return full;
	}

}
