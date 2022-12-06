package backgammon;


/**
 * This program is the Colour enum
 * @author dylan
 *
 */

/**
 * the {@code Colour} enum represents the two colours in backgammon
 */
public enum Colour {
	BLACK,
	WHITE;
	
	/**
	 * Converts both ways between player perspective indexing and universal indexing.
	 * Players perspective indexing is equivalent to their own pip values
	 * Universal indexing is arbitrarily chosen as white's perspective for use in this program
	 * 
	 * @param index the index to be converted from player perspective to universal perspective OR the universal perspective index to be converted to player's perspective
	 * @return index in the alternate form.
	 */
	public int getAlternateIndex(int index) {
		int alternateIndex;
		if (this == Colour.WHITE) {
			alternateIndex = index;
		} else {
			alternateIndex = 25 - index;
		}
		return alternateIndex;
	}
}
