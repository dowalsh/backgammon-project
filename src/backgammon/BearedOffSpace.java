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

	public BearedOffSpace(Colour ColourOfSpace) {
		this.setColour(ColourOfSpace);
	}

	@Override
	public boolean canPlace(Checker start) {
		boolean Place = false;
		if(start.getColour().equals(this.getColour())) {
			Place = true;
		}
		return Place;
	}

	@Override
	public boolean canTake() {
		return false;
	}
	
	@Override
	public int getPipValue(Player player) {
		return 0;
	}
	
	/**
	 * Checks if the beared off space is full, thus if the player has won.
	 * @return Whether or not the beared off space is full.
	 */
	public boolean isFull() {
		boolean Full = false;
		if(this.getNumCheckers() == 15) {
			Full = true;
		}
		return Full;
	}

}
