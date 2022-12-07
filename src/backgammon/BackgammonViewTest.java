package backgammon;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
/**
 * This program is the BackgammonViewTest class
 * @author Sam Lynch
 */

/**
 * A {@code BackgammonViewTest} Class is a test for BackgammonView class
 */
public class BackgammonViewTest {

	@Test
	public void testGetBoardString(){

		BackgammonMatch match = new BackgammonMatch(null);
		BackgammonGame game = new BackgammonGame(match);
		String actualOutput = BackgammonView.getBoardString(game, new Player(Colour.WHITE, "Dummy"));

		String expectedOutput = "To roll the dice enter command 'roll'\n"
				+ "\n"
				+ "       13 14 15 16 17 18     B    19 20 21 22 23 24      \n"
				+ "=========================================================\n"
				+ "     |  o           .     |     |  .              o  |   \n"
				+ "     |  o           .     |     |  .              o  |   \n"
				+ "     |  o           .     |     |  .                 |   \n"
				+ "     |  o                 |     |  .                 |   \n"
				+ "     |  o                 |     |  .                 |   \n"
				+ " 64  |                    |     |                    |   \n"
				+ "     |  .                 |     |  o                 |   \n"
				+ "     |  .                 |     |  o                 |   \n"
				+ "     |  .           o     |     |  o                 |   \n"
				+ "     |  .           o     |     |  o              .  |   \n"
				+ "     |  .           o     |     |  o              .  |   \n"
				+ "=========================================================\n"
				+ "       12 11 10  9  8  7     B     6  5  4  3  2  1      \n";

		assertEquals(expectedOutput, actualOutput);
	}

}
