package backgammon;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PointTest {
	private Point PopulatedWhitePoint;
	private Point EmptyPoint;
	private Point OneBlackCheckerPoint;
	private Checker white = new Checker(Colour.WHITE);
	private Checker black = new Checker(Colour.BLACK);

	@BeforeEach
	void setUp() throws Exception {
		PopulatedWhitePoint = new Point(13);
		EmptyPoint = new Point(2);
		OneBlackCheckerPoint = new Point(3);
		Checker added = new Checker(Colour.BLACK);
		OneBlackCheckerPoint.addChecker(added);
	}

	@Test
	void testCanPlace() {
		assertEquals(true, PopulatedWhitePoint.canPlace(white));
		assertEquals(false, PopulatedWhitePoint.canPlace(black));
		assertEquals(true, EmptyPoint.canPlace(black));
	}

	@Test
	void testCanTake() {
		assertEquals(false, EmptyPoint.canTake());
		assertEquals(true, PopulatedWhitePoint.canTake());
	}

	@Test
	void testIsAHit() {
		assertEquals(true, OneBlackCheckerPoint.isAHit(white));
		assertEquals(false, OneBlackCheckerPoint.isAHit(black));
		assertEquals(false, EmptyPoint.isAHit(black));
		assertEquals(false, PopulatedWhitePoint.isAHit(black));
		assertEquals(false, PopulatedWhitePoint.isAHit(white));
	}

	@Test
	void testGetNumCheckers() {
		assertEquals(5, PopulatedWhitePoint.getNumCheckers());
		assertEquals(1, OneBlackCheckerPoint.getNumCheckers());
		assertEquals(0, EmptyPoint.getNumCheckers());
	}

	@Test
	void testGetColour() {
		assertEquals(Colour.WHITE, PopulatedWhitePoint.getColour());
		assertEquals(Colour.BLACK, EmptyPoint.getColour());
		assertEquals(Colour.WHITE, OneBlackCheckerPoint.getColour());
	}

	@Test
	void testIsEmpty() {
		assertEquals(false, OneBlackCheckerPoint.isEmpty());
		assertEquals(false, PopulatedWhitePoint.isEmpty());
		assertEquals(true, EmptyPoint.isEmpty());
	}

	@Test
	void testAddChecker() {
		EmptyPoint.addChecker(white);
		assertEquals(white, EmptyPoint.getTopChecker());
	}

	@Test
	void testAddNewCheckers() {
		EmptyPoint.addNewCheckers(2, Colour.BLACK);
		assertEquals(2,EmptyPoint.getNumCheckers());
	}

	@Test
	void testGetTopChecker() {
		assertEquals(null,EmptyPoint.getTopChecker());
		EmptyPoint.addChecker(white);
		assertEquals(white, EmptyPoint.getTopChecker());
	}

	@Test
	void testRemoveChecker() {
		assertEquals(null,EmptyPoint.removeChecker());
		Checker removed = PopulatedWhitePoint.removeChecker();
		assertEquals(4,PopulatedWhitePoint.getNumCheckers());
	}

}
