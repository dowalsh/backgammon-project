package backgammon;

/**
 * This program is the Bar class
 * @author Sam Lynch
 *
 */

/**
 * A {@code Bar} is an extension of the BoardSpace class, representing a bar on
 * the backgammon board
 */

public class Bar extends BoardSpace {

	/**
	 * Constructor for this Class.
	 * 
	 * @param ColourOfBar Colour of the bar.
	 */
	public Bar(Colour colourOfBar) {
		this.setColour(colourOfBar);
	}

	/**
	 * Copy Constructor for this Class.
	 * 
	 */
	public Bar(Bar bar) {
		super(bar);
	}

	@Override
	public boolean canPlace(Checker checkerToBePlaced) {
		boolean place = false;
		if (checkerToBePlaced.getColour().equals(this.getColour())) {
			place = true;
		}
		return place;
	}

	@Override
	public boolean canTake(Colour playerColour) {
		boolean take;
		if (this.isEmpty()) {
			take = false;
		} else if (!playerColour.equals(this.getColour())) {
			take = false;
		} else {
			take = true;
		}
		return take;
	}

	@Override
	public int getPipValue(Colour playerColour) {
		return 25;
	}

	@Override
	public String toString(Colour playerColour) {
		return "Bar";
	}

}
