package backgammon;
/**
 * This program is the PlayerTest class
 * @author Sam Lynch
 */

/**
 * A {@code PlayerTest} Class is a test for the Player Class
 */

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class PlayerTest {

	private Player player1 = new Player(Colour.WHITE,"Woody");
	private Player player2 =new Player(Colour.BLACK,"Buzz");
	
	
	@Test
	public void testPlayerName(){
		String player1_expected_string = "Woody (WHITE)";
		String player2_expected_string = "Buzz (BLACK)";
		assertEquals(player1_expected_string,player1.toString());
		assertEquals(player2_expected_string,player2.toString());

		player2.setName("Andy");
		player2_expected_string = "Andy (BLACK)";
		assertEquals(player2_expected_string,player2.toString());
	}
	
	@Test
	public void testOfferDoubles(){
		player1.setCanOfferDoubles(true);
		player2.setCanOfferDoubles(false);
		assertEquals(true,player1.canOfferDoubles());
		assertEquals(false,player2.canOfferDoubles());
	}
	
	@Test
	public void testGetAlternateIndex(){
		int index = 15;
		assertEquals(15,player1.getAlternateIndex(index));
		assertEquals(10,player2.getAlternateIndex(index));
	}
	
	@Test
	public void testScore(){
		assertEquals(0,player1.getScore());
		player1.addScore(2);
		assertEquals(2,player1.getScore());
		player1.resetScore();
		assertEquals(0,player1.getScore());
	}
	
	@Test
	public void testGetColour(){
		assertEquals(Colour.WHITE,player1.getColour());
		assertEquals(Colour.BLACK,player2.getColour());
	}
	
	
}
