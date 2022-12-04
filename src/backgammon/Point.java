package backgammon;

/**
 * This program is the Point class
 * @author Sam Lynch
 */

/**
 * A {@code Point} is a subclass of the BoardSpace class, representing 
 * a single point on the backgammon board
 */
public class Point extends BoardSpace {

	/**
	 * The value/index of this point according to the white player's perspective
	 */
	private int whiteIndex;

	/**
	 * Constructor
	 * @param n the value/index of this point according to the white players perspective
	 */
	public Point(int n) {

		this.whiteIndex = n;
	
		if (n % 2 == 0) {
			this.setColour(Colour.BLACK);
		} else {
			this.setColour(Colour.WHITE);
		}
	}
	
	/**
	 * Add the appropriate checkers (if any) to this point for the initial state of backgammon board
	 */
	public void addInitialCheckers() {
		int n = whiteIndex;

		switch (n) {
		case 24:
			addNewCheckers(2, Colour.WHITE);
			break;
		case 13:
			addNewCheckers(5, Colour.WHITE);
			break;
		case 8:
			addNewCheckers(3, Colour.WHITE);
			break;
		case 6:
			addNewCheckers(5, Colour.WHITE);
			break;
		case 25 - 24:
			addNewCheckers(2, Colour.BLACK);
			break;
		case 25 - 13:
			addNewCheckers(5, Colour.BLACK);
			break;
		case 25 - 8:
			addNewCheckers(3, Colour.BLACK);
			break;
		case 25 - 6:
			addNewCheckers(5, Colour.BLACK);
			break;
		}
		
	}

	/**
	 * copy constructor
	 * @param point Point to copy
	 */
	public Point(Point point) {
		super(point);
		this.whiteIndex = point.getPipValue(Colour.WHITE);
	}

	@Override
	public boolean canPlace(Checker start) {
		boolean place = false;
		if (this.isEmpty()) {
			place = true;
		} else if (start.getColour().equals(getTopChecker().getColour())) {
			place = true;
		} else if (this.getNumCheckers() == 1) {
			place = true;
		}
		return place;
	}


	@Override
	public boolean canTake(Player player) {
		boolean take;
		if (this.isEmpty()) {
			take = false;
		} else if (!player.getColour().equals(this.getTopChecker().getColour())) {
			take = false;
		} else {
			take = true;
		}
		return take;
	}

	@Override
	public int getPipValue(Colour colour) {
		return colour.getAlternateIndex(whiteIndex);
	}

	/**
	 * Check if moving a checker onto this point will result in a hit.
	 * 
	 * @param checker Checker to move onto point.
	 * @return true if this move would result in a hit
	 */
	public boolean isHittable(Checker checker) {
		boolean hit = false;
		if (this.getNumCheckers() == 1 && !checker.getColour().equals(getTopChecker().getColour())) {
			hit = true;
		}
		return hit;
	}

	@Override
	public String toString(Colour colour) {
		return Integer.toString(getPipValue(colour));
	}
	
	/**
	 * Check if this point has a checker of a certain colour on it
	 * @param colourOfPlayer
	 * @return
	 */
	public boolean hasCheckerOfColour(Colour colour) {
		boolean hasCheckerOfColour = false;
		if (this.isEmpty()) {
			hasCheckerOfColour = false;
		} else if (colour.equals(this.getTopChecker().getColour())) {
			hasCheckerOfColour = true;
		}
		return hasCheckerOfColour;
	}

}
