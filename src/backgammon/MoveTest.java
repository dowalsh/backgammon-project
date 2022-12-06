package backgammon;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class MoveTest {
	private Move move_1_between_13_12 = new Move(1, 13, 12);
	private BackgammonBoard board = new BackgammonBoard();
	
	@Test
	void testEquals() {
		Move move_1_between_13_12_DUPLICATE = new Move(1, 13, 12);
		assertEquals(true, move_1_between_13_12.equals(move_1_between_13_12_DUPLICATE));
		assertEquals(false, move_1_between_13_12.equals(new Move(3,15,12)));
	}
	
	@Test
	void testCompareTo() {
		//test ordering by roll distance
		assertEquals(true, move_1_between_13_12.compareTo(new Move(2,13,11) ) < 0 );
		assertEquals(true, new Move(2,13,11).compareTo(move_1_between_13_12) >0);
		
		//test ordering by source
		assertEquals(true, move_1_between_13_12.compareTo(new Move(1,14,13) ) < 0 );
		assertEquals(true, new Move(1,14,13).compareTo(move_1_between_13_12 ) > 0 );
		
		//test that same moves are equal
		assertEquals(true, move_1_between_13_12.compareTo(new Move(1,13,12) ) == 0 );
	}

	@Test
	void testGetSource() {
		assertEquals(13, move_1_between_13_12.getSource(board, Colour.WHITE).getPipValue(Colour.WHITE));
		assertEquals(12, move_1_between_13_12.getSource(board, Colour.BLACK).getPipValue(Colour.WHITE));
	}
	
	@Test
	void testGetDestination() {
		assertEquals(12, move_1_between_13_12.getDestination(board, Colour.WHITE).getPipValue(Colour.WHITE));
		assertEquals(13, move_1_between_13_12.getDestination(board, Colour.BLACK).getPipValue(Colour.WHITE));
	}
	
	@Test
	void testToString() {
		String expectedStringA = "[1]  13->12";
		assertEquals(expectedStringA, move_1_between_13_12.toString());
		
		String expectedStringB = "[2] Bar ->23";
		assertEquals(expectedStringB, new Move(2,25,23).toString());
		
		String expectedStringC = "[3]  3-> Off";
		assertEquals(expectedStringC, new Move(3,3,0).toString());
	}
	
	
	@Test
	void testGetRoll() {
		assertEquals(1, move_1_between_13_12.getRoll());
	
	}
}
