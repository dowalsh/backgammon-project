package backgammon;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class BackgammonViewTest {

	@Test
	public void printBoardTest() throws Exception {

		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		// Now System.out.println() statements will come to outContent stream

		BackgammonMatch match = new BackgammonMatch(null);
		BackgammonGame game = new BackgammonGame(match);
		BackgammonView.printBoard(game, new Player(Colour.WHITE, "Dummy"));

		String expectedOutput = "To roll the dice enter command 'roll'\n"+
				"\n       13 14 15 16 17 18     B    19 20 21 22 23 24      \n"
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
				+ "       12 11 10  9  8  7     B     6  5  4  3  2  1      \n\n";

		// Do the actual assertion.
		assertEquals(expectedOutput, outContent.toString());

	}

}
