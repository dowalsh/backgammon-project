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
			addNewCheckers(2,Colour.WHITE);
			break;
		case 13:
			addNewCheckers(5,Colour.WHITE);
			break;
		case 8:
			addNewCheckers(3,Colour.WHITE);
			break;
		case 6:
			addNewCheckers(5,Colour.WHITE);
			break;	
		case 25-24:
			addNewCheckers(2,Colour.BLACK);
			break;
		case 25-13:
			addNewCheckers(5,Colour.BLACK);
			break;
		case 25-8:
			addNewCheckers(3,Colour.BLACK);
			break;
		case 25-6:
			addNewCheckers(5,Colour.BLACK);
			break;	
		}
	}

	@Override
	public boolean canPlace(Checker start) {
		boolean Place = false;
		if (this.isEmpty()) {
			Place = true;
		} else if (start.getColour().equals(getTopChecker().getColour())) {
			Place = true;
		} else if (this.getNumCheckers() == 1) {
			Place = true;
		}
		return Place;
	}

	@Override
	public boolean canTake() {
		boolean Take = true;
		if (this.isEmpty()) {
			Take = false;
		}
		return Take;
	}

	@Override
	public int getPipValue(Player player) {
		return player.getPointIndex(whiteIndex);
	}
	
	/**
	 * Checks if moving a checker onto this point will result in a hit.
	 * 
	 * @param start Checker to move onto point.
	 * @return Whether or not a hit occurs.
	 */
	public boolean isAHit(Checker start) {
		boolean Hit = false;
		if (this.getNumCheckers() == 1 && !start.getColour().equals(getTopChecker().getColour())) {
			Hit = true;
		}
		return Hit;
	}

	/**
	 * Returns a string of the point from the active players perspective.
	 * @param player Active player.
	 * @return String of point.
	 */
	public String toString(Player player) {
		int index = this.getPipValue(player);
		return "Point " + index;
	}

}
