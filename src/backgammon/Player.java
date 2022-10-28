package backgammon;

import java.util.Scanner;

public class Player {
	private static Scanner in = new Scanner(System.in);
	private String Name;
	private Colour PlayersColour;

	public Player(Colour colour) {
		this.Name = setName();
		this.PlayersColour = colour;
	}
	
	public String setName() {
		System.out.print("Enter the player name: ");
		String name = in.nextLine();
		return name;
	}

	public String toString() {
		return "Name: "+Name+"\nColour: "+PlayersColour;
	}
}
