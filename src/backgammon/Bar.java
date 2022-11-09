package backgammon;

/**
 * This program is the Bar class
 * @author Sam Lynch
 *
 */

/**
 * A {@code Bar} is an extension of the BoardSpace class, representing a bar on the backgammon board
 */

public class Bar extends BoardSpace {
	
	/**
	 * Constructor for this Class.
	 * @param ColourOfBar Colour of the bar.
	 */
	public Bar(Colour colourOfBar) {
		this.setColour(colourOfBar);
	}
	
	@Override
	public boolean canPlace(Checker start) {
		boolean place = false;
		if(start.getColour().equals(this.getColour())) {
			place = true;
		}
		return place;
	}
	
	@Override
	public boolean canTake() {
		boolean take = true;
		if(this.isEmpty()) {
			take = false;
		}
		return take;
	}
	
	@Override
	public int getPipValue(Player player) {
		return 25;
	}

	@Override
	public String toString(Player player) {
		return this.getColour() + " Bar";
	}
	
}
