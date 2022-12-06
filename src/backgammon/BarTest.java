package backgammon;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BarTest {
	private Bar blackBar;
	private Bar whiteBar;
	private Checker white = new Checker(Colour.WHITE);
	private Checker black = new Checker(Colour.BLACK);

	@BeforeEach
	void setUp() throws Exception {
		blackBar = new Bar(Colour.BLACK);
		whiteBar = new Bar(Colour.WHITE);
		blackBar.addChecker(black);
	}

	@Test
	void testCanPlace() {
		assertEquals(false, blackBar.canPlace(white));
		assertEquals(false, whiteBar.canPlace(black));
		assertEquals(true, whiteBar.canPlace(white));
		assertEquals(true, blackBar.canPlace(black));
	}

	@Test
	void testCanTake() {
		//cannot take from empty
		assertEquals(false, whiteBar.canTake(Colour.WHITE));
		
		//can only take if checker same colour as player
		assertEquals(true, blackBar.canTake(Colour.BLACK));
		assertEquals(false, blackBar.canTake(Colour.WHITE));
	}
	
	@Test
	void testGetPipValue() {
		assertEquals(25, whiteBar.getPipValue(Colour.WHITE));
		assertEquals(25, blackBar.getPipValue(Colour.BLACK));
	}

}
