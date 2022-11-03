package backgammon;

/**
 * This program is the Dice class
 * @author Sam Lynch
 *
 */

/**
 * A {@code Dice} represents a dice
 */
public class Dice {
	private final static double SIDES_ON_DICE = 6.0;
	
	/**
	 * Constructor for this class.
	 */
	public Dice() {
		
	}
	
	/**
	 * Rolls the dice.
	 * @return The result of the roll
	 */
	public final static int roll () {
		double roll = Math.random()*SIDES_ON_DICE+1.0;
		return (int) roll;
	}
	
	public final static String toString(int results[]) {
		
		String indent = " ".repeat(17);
		String gap = " ".repeat(5);

		String[][] diceFace1 = getDiceFace(results[0]);
		String[][] diceFace2 = getDiceFace(results[1]);

		final StringBuilder formatString = new StringBuilder("");
		//header line
		formatString.append(indent);
		formatString.append("_______"+ gap+ "_______\n");
		for(int i = 0; i<3;i++) {
			formatString.append(indent);
			formatString.append("|");
			for(int j = 0; j<3;j++) {
				formatString.append(diceFace1[i][j]);
				if(j<2) formatString.append(" ");
			}
			formatString.append("|"+gap+"|");
			for(int j = 0; j<3;j++) {
				formatString.append(diceFace2[i][j]);
				if(j<2) formatString.append(" ");
			}
			formatString.append("|\n");
		}
		formatString.append(indent);
		formatString.append("¯¯¯¯¯¯¯"+gap+"¯¯¯¯¯¯¯");

		
		return formatString.toString();
	}
	
	private final static String[][] getDiceFace(int n) {
		
		String[][] diceFace = { {" "," "," "},{" "," "," "},{" "," "," "} };
		
		String dot = "o";
		
		switch(n) {
		case 1:
			diceFace[1][1] = dot;
			break;
		case 2:
			diceFace[0][0] = dot;
			diceFace[2][2] = dot;
			break;
		case 3:
			diceFace[0][0] = dot;
			diceFace[1][1] = dot;
			diceFace[2][2] = dot;
			break;
		case 4:
			diceFace[0][0] = dot;
			diceFace[0][2] = dot;
			diceFace[2][0] = dot;
			diceFace[2][2] = dot;
			break;
		case 5:
			diceFace[0][0] = dot;
			diceFace[0][2] = dot;
			diceFace[1][1] = dot;
			diceFace[2][0] = dot;
			diceFace[2][2] = dot;
			break;
		case 6:
			diceFace[0][0] = dot;
			diceFace[1][0] = dot;
			diceFace[2][0] = dot;
			diceFace[0][2] = dot;
			diceFace[1][2] = dot;
			diceFace[2][2] = dot;
			break;
		}
		
		return diceFace;
	}
}

