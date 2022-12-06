
package backgammon;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class DiceTest {

	@Test
	public void testGetDiceFace(){
		String[][] expectedDiceFace1 = 
		  { { " ", " ", " " },
		    { " ", "o", " " }, 
		    { " ", " ", " " } };
		String[][] expectedDiceFace2 = 
			  { { "o", " ", " " },
			    { " ", " ", " " }, 
			    { " ", " ", "o" } };
		String[][] expectedDiceFace3 = 
			  { { "o", " ", " " },
			    { " ", "o", " " }, 
			    { " ", " ", "o" } };
		String[][] expectedDiceFace4 = 
			  { { "o", " ", "o" },
			    { " ", " ", " " }, 
			    { "o", " ", "o" } };
		String[][] expectedDiceFace5 = 
			  { { "o", " ", "o" },
			    { " ", "o", " " }, 
			    { "o", " ", "o" } };
		String[][] expectedDiceFace6 = 
			  { { "o", " ", "o" },
			    { "o", " ", "o" }, 
			    { "o", " ", "o" } };
		
		assertArrayEquals(expectedDiceFace1, Dice.getDiceFace(1));
		assertArrayEquals(expectedDiceFace2, Dice.getDiceFace(2));
		assertArrayEquals(expectedDiceFace3, Dice.getDiceFace(3));
		assertArrayEquals(expectedDiceFace4, Dice.getDiceFace(4));
		assertArrayEquals(expectedDiceFace5, Dice.getDiceFace(5));
		assertArrayEquals(expectedDiceFace6, Dice.getDiceFace(6));

	}
	
	@Test
	public void testGetDiceAsString(){
		
		String indent = " ".repeat(15);
		String expectedResult ="\n"+
				 indent+ "+-------+     +-------+\n"
				+indent+ "|       |     | o   o |\n"
				+indent+ "|   o   |     |   o   |\n"
				+indent+ "|       |     | o   o |\n"
				+indent+ "+-------+     +-------+\n";
		int diceValues[] = {1,5};
		assertEquals(expectedResult, Dice.getDiceAsString(diceValues));
	}


}
/*



+-------+     +-------+
|       |     | o   o |
|   o   |     |   o   |
|       |     | o   o |
+-------+     +-------+


*/