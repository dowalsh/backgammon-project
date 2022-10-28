package backgammon;

/**
 * This program is the Point class
 * @author Sam Lynch
 *
 */

/**
 * A {@code Point} is an extension of the BoardSpace class, representing a point on the backgammon board
 */
public class Point extends BoardSpace{
	
	/**
	 * Constructor for this class
	 */
	public Point(int number) {
		// unsure if this is correct (Looks Good to me(Sam))
		// basically  using the odd/even rule to colour them
		// Do we want to add starting checkers in here?(2 white in 1, 5 black in 6, 3 black in 8, 5 white in 12, 
		// 5 black in 13, 3 white in 17, 5 white in 19, 2 black in 24) Maybe separate constructor? Maybe in Board class?
		if(number%2 == 0) {
			this.setColour(Colour.BLACK);
		}else {
			this.setColour(Colour.WHITE);
		}
	}
	
	@Override
	public boolean canPlace(Checker start) {
		boolean Place = false;
		if(this.isEmpty()) {
			Place = true;
		}
		else if(start.getColour().equals(getTopChecker().getColour())) {
			Place = true;
		}
		else if(this.getNumCheckers() == 1) {
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
	
	/**
	 * Checks if moving a checker onto this point will result in a hit.
	 * @param start Checker to move onto point.
	 * @return Whether or not a hit occurs.
	 */
	public boolean isAHit(Checker start) {
		boolean Hit = false;
		if(this.getNumCheckers()==1 && !start.getColour().equals(getTopChecker().getColour())) {
			Hit = true;
		}
		return Hit;
	}

}
