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
	private Player whitePlayer;

	@BeforeEach
	void setUp() throws Exception {
		board = new BackgammonBoard();
		blackPlayer = new Player(Colour.BLACK,"Dummy");
		whitePlayer = new Player(Colour.WHITE,"Dummy");
	}

	@Test
	void testBearOff() {
		board = BackgammonBoard.createTestBoard("BEAR OFF");
		board.setRolls(6, 2, blackPlayer);
		List<Move> testMoves = new ArrayList<Move>(List.of(new Move(2,2,0),new Move(2,3,1),new Move(2,4,2),new Move(6,4,0)));
		Collection<Move> actualMoves = board.getLegalMoves();
		assertTrue(testMoves.size() == actualMoves.size() && testMoves.containsAll(actualMoves) && actualMoves.containsAll(testMoves));
	}
	
	@Test
	void testOnlyLargerRoll() {
		board = BackgammonBoard.createTestBoard("ONLY LARGER ROLL");
		board.setRolls(5, 3, blackPlayer);
		List<Move> testMoves = new ArrayList<Move>(List.of(new Move(5,15,10)));
		Collection<Move> actualMoves = board.getLegalMoves();
		assertTrue(testMoves.size() == actualMoves.size() && testMoves.containsAll(actualMoves) && actualMoves.containsAll(testMoves));
	}
	
	@Test
	void testOnlyDoubleMove() {
		board = BackgammonBoard.createTestBoard("ONLY DOUBLE ROLL MOVES");
		board.setRolls(5, 3, blackPlayer);
		List<Move> testMoves = new ArrayList<Move>(List.of(new Move(5,10,5),new Move(3,15,12)));
		Collection<Move> actualMoves = board.getLegalMoves();
		assertTrue(testMoves.size() == actualMoves.size() && testMoves.containsAll(actualMoves) && actualMoves.containsAll(testMoves));
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
		//TODO endTurn()
	}

	@Test
	void testNoMoveAvailable() {
		board.rollDice(blackPlayer);
		assertEquals(false, board.noMoveAvailable(blackPlayer));
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
	void testWinSingle() {
		board = BackgammonBoard.createTestBoard("SINGLE WIN");
		board.setRolls(1, 1, whitePlayer);
		board.selectMove('A', whitePlayer);
		assertEquals(true, board.isWon(whitePlayer));
		assertEquals(false, board.isGammon(blackPlayer));
		assertEquals(false, board.isBackgammon(blackPlayer));
	}
	
	@Test
	void testWinGammon() {
		board = BackgammonBoard.createTestBoard("GAMMON WIN");
		board.setRolls(1, 1, whitePlayer);
		board.selectMove('A', whitePlayer);
		assertEquals(true, board.isWon(whitePlayer));
		assertEquals(true, board.isGammon(blackPlayer));
		assertEquals(false, board.isBackgammon(blackPlayer));
	}
	
	@Test
	void testWinBackgammon() {
		board = BackgammonBoard.createTestBoard("BACKGAMMON WIN");
		board.setRolls(1, 1, whitePlayer);
		board.selectMove('A', whitePlayer);
		assertEquals(true, board.isWon(whitePlayer));
		assertEquals(true, board.isGammon(blackPlayer));
		assertEquals(true, board.isBackgammon(blackPlayer));
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
