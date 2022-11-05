package backgammon;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BarTest {
	private Bar BlackBar;
	private Bar WhiteBar;
	private Checker white = new Checker(Colour.WHITE);
	private Checker black = new Checker(Colour.BLACK);

	@BeforeEach
	void setUp() throws Exception {
		BlackBar = new Bar(Colour.BLACK);
		WhiteBar = new Bar(Colour.WHITE);
		BlackBar.addChecker(black);
	}

	@Test
	void testCanPlace() {
		assertEquals(false, BlackBar.canPlace(white));
		assertEquals(false, WhiteBar.canPlace(black));
		assertEquals(true, WhiteBar.canPlace(white));
		assertEquals(true, BlackBar.canPlace(black));
	}

	@Test
	void testCanTake() {
		assertEquals(false, WhiteBar.canTake());
		assertEquals(true, BlackBar.canTake());
	}

}
