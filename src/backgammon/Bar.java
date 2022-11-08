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
	public Bar(Colour ColourOfBar) {
		this.setColour(ColourOfBar);
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
		boolean Take = true;
		if(this.isEmpty()) {
			Take = false;
		}
		return Take;
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
