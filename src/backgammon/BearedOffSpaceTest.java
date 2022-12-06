package backgammon;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BearedOffSpaceTest {
	private BearedOffSpace emptyWhiteBearedOffSpace;
	private BearedOffSpace populatedBlackBearedOffSpace;
	private Checker white = new Checker(Colour.WHITE);
	private Checker black = new Checker(Colour.BLACK);
	
	

	@BeforeEach
	void setUp() throws Exception {
		emptyWhiteBearedOffSpace = new BearedOffSpace(Colour.WHITE);
		populatedBlackBearedOffSpace = new BearedOffSpace(Colour.BLACK);
		populatedBlackBearedOffSpace.addChecker(black);

	}

	@Test
	void testCanPlace() {
		assertEquals(true, emptyWhiteBearedOffSpace.canPlace(white));
		assertEquals(false, emptyWhiteBearedOffSpace.canPlace(black));
		assertEquals(true, populatedBlackBearedOffSpace.canPlace(black));
		assertEquals(false, populatedBlackBearedOffSpace.canPlace(white));
	}

	@Test
	void testCanTake() {
		
		//cannot take from empty
		assertEquals(false, emptyWhiteBearedOffSpace.canTake(Colour.WHITE));
		//cannot take from bearedOffSpace even with checkers
		assertEquals(false, populatedBlackBearedOffSpace.canTake(Colour.BLACK));
	}	


	@Test
	void testIsFull() {
		populatedBlackBearedOffSpace.addNewCheckers(14, Colour.BLACK);
		emptyWhiteBearedOffSpace.addNewCheckers(8, Colour.WHITE);
		assertEquals(true, populatedBlackBearedOffSpace.isFull());
		assertEquals(false, emptyWhiteBearedOffSpace.isFull());
	}
	
	@Test
	void testGetPipValue() {
		assertEquals(0, emptyWhiteBearedOffSpace.getPipValue(Colour.WHITE));
		assertEquals(0, populatedBlackBearedOffSpace.getPipValue(Colour.BLACK));
	}

	
	@Test
	void testCopy() {
		
		BearedOffSpace copiedBearedOffSpace = new BearedOffSpace(populatedBlackBearedOffSpace);
		assertEquals(copiedBearedOffSpace.getColour(),populatedBlackBearedOffSpace.getColour());
		assertEquals(copiedBearedOffSpace.getNumCheckers(),populatedBlackBearedOffSpace.getNumCheckers());
		assertEquals(copiedBearedOffSpace.getTopChecker().getColour(),populatedBlackBearedOffSpace.getTopChecker().getColour());
	}
}
