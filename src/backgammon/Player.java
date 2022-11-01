package backgammon;
import java.util.Scanner;

/**
 * This program is the Player class
 * @author Sam Lynch
 *
 */

/**
 * A {@code Player} represents a player
 */
public class Player {
	private static Scanner in = new Scanner(System.in);
	private String Name;
	private Colour PlayersColour;

	/**
	 * Constructor for this class. Sets name and colour of player.
	 * @param colour Colour of player.
	 */
	public Player(Colour colour) {
		setName();
		this.PlayersColour = colour;
	}
	
	/**
	 * Prompts the player to enter their name and returns the name entered.
	 */
	public void setName() {
		System.out.print("Enter the player name: ");
		String name = in.nextLine();
		this.Name = name;
	}

	/**
	 * Overwrites inbuilt toString() method for this class. 
	 */
	public String toString() {
		return "Name: "+Name+"\nColour: "+PlayersColour;
	}
	
	public int getPointIndex(int equivalentWhiteIndex) {
		
		int index;
		if(PlayersColour == Colour.WHITE) {
			index = equivalentWhiteIndex;
		}else {
			index = 25 - equivalentWhiteIndex;
		}
		return index;
	}
}
