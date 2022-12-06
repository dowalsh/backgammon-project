package backgammon;

/**
 * This program is the Dice class
 * @author Sam Lynch
 *
 */

/**
 * A {@code Dice} represents a dice
 */
public final class Dice {
	private final static double SIDES_ON_DICE = 6.0;

	/**
	 * Rolls a dice
	 * 
	 * @return The result of the roll
	 */
	public final static int roll() {
		double roll = (Math.random() * SIDES_ON_DICE) + 1.0;
		return (int) roll;
	}

	/**
	 * Returns a string representation of 2 Dice
	 * 
	 * @param roll1 the value of the first die
	 * @param roll2 the value of the second die
	 * @return String representing the two dice
	 */
	public final static String getDiceAsString(int roll1, int roll2) {

		String indent = " ".repeat(15);
		String gap = " ".repeat(5);

		String[][] diceFace1 = getDiceFace(roll1);
		String[][] diceFace2 = getDiceFace(roll2);

		final StringBuilder formatString = new StringBuilder("");
		// header line
		formatString.append("\n");
		formatString.append(indent);
		formatString.append("+-------+" + gap + "+-------+\n");
		for (int i = 0; i < 3; i++) {
			formatString.append(indent);
			formatString.append("| ");
			for (int j = 0; j < 3; j++) {
				formatString.append(diceFace1[i][j]);
				formatString.append(" ");
			}
			formatString.append("|" + gap + "| ");
			for (int j = 0; j < 3; j++) {
				formatString.append(diceFace2[i][j]);
				formatString.append(" ");
			}
			formatString.append("|\n");
		}
		formatString.append(indent);
		formatString.append("+-------+" + gap + "+-------+\n");

		return formatString.toString();
	}

	/**
	 * Returns a 2D String array representation representation of the face of a die,
	 * where circles represent the dots of the face of a die
	 * 
	 * @param n the value of the die
	 * @return 2D String array representation the face of the die
	 */
	public final static String[][] getDiceFace(int n) {

		String[][] diceFace = { { " ", " ", " " }, { " ", " ", " " }, { " ", " ", " " } };

		String dot = "o";

		switch (n) {
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
