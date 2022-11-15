package backgammon;

/**
 * This program is the Point class
 * @author Sam Lynch
 *
 */

/**
 * A {@code Point} is an extension of the BoardSpace class, representing a point
 * on the backgammon board
 */
public class Point extends BoardSpace {

	private int whiteIndex;

	/**
	 * Constructor for this class
	 */
	public Point(int n) {

		this.whiteIndex = n;

		if (n % 2 == 0) {
			this.setColour(Colour.BLACK);
		} else {
			this.setColour(Colour.WHITE);
		}

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

	// copy constructor
	public Point(int n, Point point) {
		super(point);
		this.whiteIndex = n;
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
	public int getPipValue(Player player) {
		return player.getAlternateIndex(whiteIndex);
	}

	/**
	 * Checks if moving a checker onto this point will result in a hit.
	 * 
	 * @param start Checker to move onto point.
	 * @return Whether or not a hit occurs.
	 */
	public boolean isAHit(Checker start) {
		boolean hit = false;
		if (this.getNumCheckers() == 1 && !start.getColour().equals(getTopChecker().getColour())) {
			hit = true;
		}
		return hit;
	}

	/**
	 * Returns a string of the point from the active players perspective.
	 * 
	 * @param player Active player.
	 * @return String of point.
	 */
	public String toString(Player player) {
		return Integer.toString(getPipValue(player));
	}
	
	public boolean hasColour(Colour colourOfPlayer) {
		boolean sameColour = false;
		if (this.isEmpty()) {
			sameColour = false;
		} else if (colourOfPlayer.equals(this.getTopChecker().getColour())) {
			sameColour = true;
		}
		return sameColour;
	}

}
