package backgammon;
/**
 * This program is the MoveSetTest class
 * @author Sam Lynch
 */

/**
 * A {@code MoveSetTest} Class is a test for the MoveSet Class
 */
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class MoveSetTest {

	private MoveSet one_move = new MoveSet(new Move(1, 13, 12));
	private MoveSet two_moves = new MoveSet(new Move(1, 13, 12), new Move(3, 3, 0));

	@Test
	void testUsesRoll() {
		assertTrue(one_move.usesRoll(1));
		assertFalse(one_move.usesRoll(6));

		assertTrue(two_moves.usesRoll(1));
		assertTrue(two_moves.usesRoll(3));
		assertFalse(one_move.usesRoll(4));

	}

	@Test
	void testSize() {
		assertEquals(1, one_move.size());
		assertEquals(2, two_moves.size());
	}
	
	@Test
	void testGetFirstMove() {
		assertEquals(new Move(1, 13, 12), two_moves.getFirstMove());
	}
}
