package backgammon;

import java.util.List;
import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BackgammonBoardTest {
	private BackgammonBoard board;
	private Player activePlayer;

	@BeforeEach
	void setUp() throws Exception {
		board = new BackgammonBoard();
		activePlayer = new Player(Colour.BLACK,"Dummy");
	}

	@Test
	void testBearOff() {
		board = BackgammonBoard.createTestBoard("BEAR OFF");
		board.setRolls(6, 2, activePlayer);
		List<Move> testMoves = new ArrayList<Move>(List.of(new Move(2,2,0),new Move(2,3,1),new Move(2,4,2),new Move(6,4,0)));
		Collection<Move> actualMoves = board.getLegalMoves();
		assertTrue(testMoves.size() == actualMoves.size() && testMoves.containsAll(actualMoves) && actualMoves.containsAll(testMoves));
	}
	
	@Test
	void testOnlyLargerRoll() {
		BackgammonBoard testboard = BackgammonBoard.createTestBoard("ONLY LARGER ROLL");
		testboard.setRolls(5, 3, activePlayer);
		List<Move> testMoves = new ArrayList<Move>(List.of(new Move(5,15,10)));
		Collection<Move> actualMoves = testboard.getLegalMoves();
		assertTrue(testMoves.size() == actualMoves.size() && testMoves.containsAll(actualMoves) && actualMoves.containsAll(testMoves));
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
	void testIsTurnOver() {
		board.beginTurn();
		assertEquals(false, board.isTurnOver());
		//TODO endTurn()
	}

	@Test
	void testNoMoveAvailable() {
		board.rollDice(activePlayer);
		assertEquals(false, board.noMoveAvailable(activePlayer));
	}
	
	@Test
	void testDoublingCube() {
//TODO applyDouble
		//getDoublingCubeMultiplier
		}
	
	@Test
	void testSetRolls() {
		
		
//TODO setRolls and getLatestDiceRoll
		//legalMovesToString
	}
	
	@Test
	void testWins() {
		
	}
	
	@Test
	void testHit() {
		// set up hitting board
		// roll 1 1 for player
		//check bar empty
		// selectMove A
		//check bar has exactly one checker of that colour

	}


}
