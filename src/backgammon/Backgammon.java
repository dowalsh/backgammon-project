package backgammon;

import java.util.Scanner;
import java.io.FileNotFoundException;

public class Backgammon {

	public static void main(String[] args) throws FileNotFoundException{

		boolean run = true;
		while(run) {
			BackgammonMatch match = new BackgammonMatch();
			match.playMatch();
			
			// Ask if user wants to play another match and set run accordingly
		}

	}

}
