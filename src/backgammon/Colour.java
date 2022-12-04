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

	int getAlternateIndex(int index) {
		int alternateIndex;
		if (this == Colour.WHITE) {
			alternateIndex = index;
		} else {
			alternateIndex = 25 - index;
		}
		return alternateIndex;
	}
}
