package backgammon;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PointTest {
	private Point populatedWhitePoint;
	private Point emptyPoint;
	private Point oneBlackCheckerPoint;
	private Checker white = new Checker(Colour.WHITE);
	private Checker black = new Checker(Colour.BLACK);

	@BeforeEach
	void setUp() throws Exception {
		populatedWhitePoint = new Point(13);
		emptyPoint = new Point(2);
		oneBlackCheckerPoint = new Point(3);
		Checker added = new Checker(Colour.BLACK);
		oneBlackCheckerPoint.addChecker(added);
	}

	@Test
	void testCanPlace() {
		assertEquals(true, populatedWhitePoint.canPlace(white));
		assertEquals(false, populatedWhitePoint.canPlace(black));
		assertEquals(true, emptyPoint.canPlace(black));
	}

	@Test
	void testCanTake() {
		//cannot take from empty
		assertEquals(false, emptyPoint.canTake(new Player(Colour.WHITE, "Dummy")));
		//can only take if checker same colour as player
		assertEquals(true, oneBlackCheckerPoint.canTake(new Player(Colour.BLACK, "Dummy")));
		assertEquals(false, oneBlackCheckerPoint.canTake(new Player(Colour.WHITE, "Dummy")));
	}

	@Test
	void testIsAHit() {
		assertEquals(true, oneBlackCheckerPoint.isAHit(white));
		assertEquals(false, oneBlackCheckerPoint.isAHit(black));
		assertEquals(false, emptyPoint.isAHit(black));
		assertEquals(false, populatedWhitePoint.isAHit(black));
		assertEquals(false, populatedWhitePoint.isAHit(white));
	}

	@Test
	void testGetNumCheckers() {
		assertEquals(5, populatedWhitePoint.getNumCheckers());
		assertEquals(1, oneBlackCheckerPoint.getNumCheckers());
		assertEquals(0, emptyPoint.getNumCheckers());
	}

	@Test
	void testGetColour() {
		assertEquals(Colour.WHITE, populatedWhitePoint.getColour());
		assertEquals(Colour.BLACK, emptyPoint.getColour());
		assertEquals(Colour.WHITE, oneBlackCheckerPoint.getColour());
	}

	@Test
	void testIsEmpty() {
		assertEquals(false, oneBlackCheckerPoint.isEmpty());
		assertEquals(false, populatedWhitePoint.isEmpty());
		assertEquals(true, emptyPoint.isEmpty());
	}

	@Test
	void testAddChecker() {
		emptyPoint.addChecker(white);
		assertEquals(white, emptyPoint.getTopChecker());
	}

	@Test
	void testAddNewCheckers() {
		emptyPoint.addNewCheckers(2, Colour.BLACK);
		assertEquals(2,emptyPoint.getNumCheckers());
	}

	@Test
	void testGetTopChecker() {
		assertEquals(null,emptyPoint.getTopChecker());
		emptyPoint.addChecker(white);
		assertEquals(white, emptyPoint.getTopChecker());
	}

	@Test
	void testRemoveChecker() {
		assertEquals(null,emptyPoint.removeChecker());
		Checker removed = populatedWhitePoint.removeChecker();
		assertEquals(4,populatedWhitePoint.getNumCheckers());
		assertEquals(4,populatedWhitePoint.getNumCheckers());
		assertNotNull(removed);
	}
	
	void testHasColour() {
		assertEquals(false,emptyPoint.hasColour(Colour.BLACK));
		assertEquals(true,populatedWhitePoint.hasColour(Colour.WHITE));
		assertEquals(false,populatedWhitePoint.hasColour(Colour.BLACK));
	}

}
