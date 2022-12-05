package backgammon;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BackgammonBoardTest {
	private BackgammonBoard board;
	private Player activePlayer;

	@BeforeEach
	void setUp() throws Exception {
		board = new BackgammonBoard();
		activePlayer = new Player(Colour.WHITE, "Dummy");
	}


	@Test
	void testCanBearOff() {
		assertEquals(false, board.canBearOff(activePlayer));
	}

	@Test
	void testIsWon() {
		assertEquals(false, board.isWon(activePlayer));
	}

	@Test
	void testIsDiceRolled() {
		assertEquals(false, board.isDiceRolled());
		board.rollDice(activePlayer);
		assertEquals(true, board.isDiceRolled());
	}

	@Test
	void testGetPipCount() {
		assertEquals(167, board.getPipCount(activePlayer.getColour()));
	}

	@Test
	void testIsGameOver() {
		assertEquals(false, board.isGameOver());
	}

//	@Test
//	void testGetMoveKeys() {
//		fail("Not yet implemented");
//	}

	@Test
	void testIsTurnOver() {
		board.beginTurn();
		assertEquals(false, board.isTurnOver());
	}

	@Test
	void testNoMoveAvailable() {
		board.rollDice(activePlayer);
		assertEquals(false, board.noMoveAvailable(activePlayer));
	}

}
