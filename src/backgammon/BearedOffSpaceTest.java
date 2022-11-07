package backgammon;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BearedOffSpaceTest {
	private BearedOffSpace WhiteBearedOffSpace;
	private BearedOffSpace BlackBearedOffSpace;
	private Checker white = new Checker(Colour.WHITE);
	private Checker black = new Checker(Colour.BLACK);

	@BeforeEach
	void setUp() throws Exception {
		WhiteBearedOffSpace = new BearedOffSpace(Colour.WHITE);
		BlackBearedOffSpace = new BearedOffSpace(Colour.BLACK);
	}

	@Test
	void testCanPlace() {
		assertEquals(true, WhiteBearedOffSpace.canPlace(white));
		assertEquals(false, WhiteBearedOffSpace.canPlace(black));
		assertEquals(true, BlackBearedOffSpace.canPlace(black));
		assertEquals(false, BlackBearedOffSpace.canPlace(white));
	}

	@Test
	void testCanTake() {
		WhiteBearedOffSpace.addChecker(white);
		assertEquals(false, WhiteBearedOffSpace.canTake());
		assertEquals(false, BlackBearedOffSpace.canTake());
	}

	@Test
	void testIsFull() {
		BlackBearedOffSpace.addNewCheckers(15, Colour.BLACK);
		WhiteBearedOffSpace.addNewCheckers(8, Colour.WHITE);
		assertEquals(true, BlackBearedOffSpace.isFull());
		assertEquals(false, WhiteBearedOffSpace.isFull());
	}

}
