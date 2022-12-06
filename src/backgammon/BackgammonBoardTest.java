package backgammon;

import java.util.List;
import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BackgammonBoardTest {
	private BackgammonBoard board;
	private Player blackPlayer;

	@BeforeEach
	void setUp() throws Exception {
		board = new BackgammonBoard();
		blackPlayer = new Player(Colour.BLACK, "Dummy");
	}

	@Test
	void testBearOff() {
		board = BackgammonBoard.createTestBoard("BEAR OFF");
		board.setRolls(6, 2, blackPlayer);
		List<Move> testMoves = new ArrayList<Move>(
				List.of(new Move(2, 2, 0), new Move(2, 3, 1), new Move(2, 4, 2), new Move(6, 4, 0)));
		Collection<Move> actualMoves = board.getLegalMoves();
		assertTrue(testMoves.size() == actualMoves.size() && testMoves.containsAll(actualMoves)
				&& actualMoves.containsAll(testMoves));
	}

	@Test
	void testOnlyLargerRoll() {
		BackgammonBoard testboard = BackgammonBoard.createTestBoard("ONLY LARGER ROLL");
		testboard.setRolls(5, 3, blackPlayer);
		List<Move> testMoves = new ArrayList<Move>(List.of(new Move(5, 15, 10)));
		Collection<Move> actualMoves = testboard.getLegalMoves();
		assertEquals(testMoves.size(), actualMoves.size());
		assertTrue(testMoves.containsAll(actualMoves) && actualMoves.containsAll(testMoves));
	}

	@Test
	void testIsDiceRolled() {
		assertEquals(false, board.isDiceRolled());
		board.rollDice(blackPlayer);
		assertEquals(true, board.isDiceRolled());
	}

	@Test
	void testGetPipCount() {
		assertEquals(167, board.getPipCount(blackPlayer.getColour()));
	}

	@Test
	void testIsTurnOver() {
		board.beginTurn();
		assertEquals(false, board.isTurnOver());
		board.setRolls(1, 1, blackPlayer);
		for (int i = 1; i <= 4; i++) {
			board.selectMove('A', blackPlayer);
		}
		assertEquals(true, board.isTurnOver());
	}

	@Test
	void testNoMoveAvailable() {
		board.setRolls(6, 6, blackPlayer);
		assertEquals(false, board.noMoveAvailable(blackPlayer));
		for (int i = 1; i <= 4; i++) {
			board.selectMove('A', blackPlayer);
		}
		assertEquals(true, board.noMoveAvailable(blackPlayer));
	}

	@Test
	void testDoublingCube() {
		assertEquals(1, board.getDoublingCubeMultiplier());
		board.applyDouble();
		assertEquals(2, board.getDoublingCubeMultiplier());
		board.applyDouble();
		assertEquals(4, board.getDoublingCubeMultiplier());
	}

	@Test
	void testSetRolls() {
		board.setRolls(3, 4, blackPlayer);
		assertArrayEquals(new int[] { 3, 4 }, board.getLatestDiceRoll());
	}
	
	@Test
	void testLegalMovesToString() {
		board.setRolls(3, 4, blackPlayer);
		String expectedString = 
				"Remaining Rolls: [3, 4]\n"
				+ "MOVE OPTIONS: \n" 
				+ "A) [3]  6->3\n"
				+ "B) [3]  8->5\n"
				+ "C) [3]  13->10\n" 
				+ "D) [3]  24->21\n" 
				+ "E) [4]  6->2\n" 
				+ "F) [4]  8->4\n"
				+ "G) [4]  13->9\n"
				+ "H) [4]  24->20\n";
		assertEquals(expectedString, board.legalMovesToString(blackPlayer));
	}
	
	@Test
	void testWins() {

	}

	@Test
	void testHit() {
		// set up hitting board
		board = BackgammonBoard.createTestBoard("HIT");
		// roll 1 1 for player
		board.setRolls(1, 1, blackPlayer);
		// get number of white checkers on the white bar
		int num_before = board.getBoardSpaceByPipValue(25, Colour.WHITE).getNumCheckers();
		// selectMove A (only move available)
		board.selectMove('A', blackPlayer);
		int num_after = board.getBoardSpaceByPipValue(25, Colour.WHITE).getNumCheckers();
		// check bar has exactly one more white checker
		assertEquals(num_before+1,num_after);
	}

}
