package backgammon;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/**
 * This program is the PointTest class
 * @author Sam Lynch
 */

/**
 * A {@code PointTest} Class is a test for the Point Class
 */
class PointTest {
	private Point populatedWhitePoint;
	private Point emptyPoint;
	private Point oneBlackCheckerPoint;
	private Checker white = new Checker(Colour.WHITE);
	private Checker black = new Checker(Colour.BLACK);

	@BeforeEach
	void setUp() throws Exception {
		populatedWhitePoint = new Point(13);
		populatedWhitePoint.addInitialCheckers();
		emptyPoint = new Point(2);

		oneBlackCheckerPoint = new Point(3);
		Checker added = new Checker(Colour.BLACK);
		oneBlackCheckerPoint.addChecker(added);
	}

	@Test
	void testInitialCheckersTotal() {
		int sumBlack = 0;
		int sumWhite = 0;
		for (int i = 1; i <= 24; i++) {
			Point newPoint = new Point(i);
			newPoint.addInitialCheckers();
			if (!newPoint.isEmpty()) {
				if (newPoint.getTopChecker().getColour() == Colour.BLACK) {
					sumBlack += newPoint.getNumCheckers();
				} else {
					sumWhite += newPoint.getNumCheckers();
				}
			}
		}
	
		assertEquals(15, sumBlack);
		assertEquals(15, sumWhite);
	}

	@Test
	void testCanPlace() {
		assertEquals(true, populatedWhitePoint.canPlace(white));
		assertEquals(false, populatedWhitePoint.canPlace(black));
		assertEquals(true, oneBlackCheckerPoint.canPlace(white));
		assertEquals(true, emptyPoint.canPlace(black));
	}

	@Test
	void testCanTake() {
		// cannot take from empty
		assertEquals(false, emptyPoint.canTake(Colour.WHITE));

		// can only take if checker same colour as player
		assertEquals(true, oneBlackCheckerPoint.canTake(Colour.BLACK));
		assertEquals(false, oneBlackCheckerPoint.canTake(Colour.WHITE));
	}

	@Test
	void testIsHittable() {
		assertEquals(true, oneBlackCheckerPoint.isHittable(white));
		assertEquals(false, oneBlackCheckerPoint.isHittable(black));
		assertEquals(false, emptyPoint.isHittable(black));
		assertEquals(false, populatedWhitePoint.isHittable(black));
		assertEquals(false, populatedWhitePoint.isHittable(white));
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
		assertEquals(2, emptyPoint.getNumCheckers());
	}

	@Test
	void testGetTopChecker() {
		assertEquals(null, emptyPoint.getTopChecker());
		emptyPoint.addChecker(white);
		assertEquals(white, emptyPoint.getTopChecker());
	}

	@Test
	void testRemoveChecker() {
		assertEquals(null, emptyPoint.removeChecker());
		Checker removed = populatedWhitePoint.removeChecker();
		assertEquals(4, populatedWhitePoint.getNumCheckers());
		assertEquals(4, populatedWhitePoint.getNumCheckers());
		assertNotNull(removed);
	}

	@Test
	void testHasColour() {
		assertEquals(false, emptyPoint.hasCheckerOfColour(Colour.BLACK));
		assertEquals(true, populatedWhitePoint.hasCheckerOfColour(Colour.WHITE));
		assertEquals(false, populatedWhitePoint.hasCheckerOfColour(Colour.BLACK));
	}

	@Test
	void testCopy() {
		Point copiedPoint = new Point(populatedWhitePoint);
		assertEquals(copiedPoint.getColour(), populatedWhitePoint.getColour());
		assertEquals(copiedPoint.getNumCheckers(), populatedWhitePoint.getNumCheckers());
		assertEquals(copiedPoint.getTopChecker().getColour(), populatedWhitePoint.getTopChecker().getColour());
	}

	@Test
	void testToString() {
		assertEquals("13", populatedWhitePoint.toString(Colour.WHITE));
		assertEquals("12", populatedWhitePoint.toString(Colour.BLACK));
	}

}
