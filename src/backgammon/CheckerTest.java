package backgammon;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CheckerTest {
	private Checker checker1, checker2;

	@BeforeEach
	void setUp() throws Exception {
		checker1 = new Checker(Colour.BLACK);
	    checker2 = new Checker(Colour.WHITE);
	}

	@Test
	void testGetColour() {
		assertEquals(Colour.BLACK,checker1.getColour());
		assertEquals(Colour.WHITE,checker2.getColour());
	}

	@Test
	void testToString() {
		assertEquals("*",checker1.toString());
		assertEquals("o",checker2.toString());
	}

}
