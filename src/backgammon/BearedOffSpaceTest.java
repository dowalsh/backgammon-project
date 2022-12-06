package backgammon;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BearedOffSpaceTest {
	private BearedOffSpace whiteBearedOffSpace;
	private BearedOffSpace blackBearedOffSpace;
	private Checker white = new Checker(Colour.WHITE);
	private Checker black = new Checker(Colour.BLACK);

	@BeforeEach
	void setUp() throws Exception {
		whiteBearedOffSpace = new BearedOffSpace(Colour.WHITE);
		blackBearedOffSpace = new BearedOffSpace(Colour.BLACK);
	}

	@Test
	void testCanPlace() {
		assertEquals(true, whiteBearedOffSpace.canPlace(white));
		assertEquals(false, whiteBearedOffSpace.canPlace(black));
		assertEquals(true, blackBearedOffSpace.canPlace(black));
		assertEquals(false, blackBearedOffSpace.canPlace(white));
	}

	@Test
	void testCanTake() {
		blackBearedOffSpace.addChecker(black);
		//cannot take from empty
		assertEquals(false, whiteBearedOffSpace.canTake(Colour.WHITE));
		
		//can only take if checker same colour as player
		assertEquals(true, blackBearedOffSpace.canTake(Colour.BLACK));
		assertEquals(false, blackBearedOffSpace.canTake(Colour.WHITE));
	}	


	@Test
	void testIsFull() {
		blackBearedOffSpace.addNewCheckers(15, Colour.BLACK);
		whiteBearedOffSpace.addNewCheckers(8, Colour.WHITE);
		assertEquals(true, blackBearedOffSpace.isFull());
		assertEquals(false, whiteBearedOffSpace.isFull());
	}
	
	void testGetPipValue() {
		assertEquals(0, whiteBearedOffSpace.getPipValue(Colour.WHITE));
		assertEquals(0, blackBearedOffSpace.getPipValue(Colour.BLACK));
	}

}
