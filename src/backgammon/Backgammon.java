package backgammon;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This program is the Backgammon class
 *
 */

/**
 * The {@code Backgammon} Class is the main controller for the program
 */
public class Backgammon {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		run(scan,false);
		
	}
	

	public static int run(Scanner scan, boolean testMode) {
			
		int numberOfMatchesPlayed=0;
		
		boolean run = true;
		while (run) {
			BackgammonMatch match = new BackgammonMatch(scan);
			if(testMode) match.testMode();
			match.playMatch();

			if (match.isMatchQuit()) {
				run = false;
			} else {
				numberOfMatchesPlayed+=1;
				// Ask if user wants to play another match and set run accordingly
				boolean validInput = false;
				String input;
				while (!validInput) {
					input = BackgammonView.promptForRematch(scan);
					if(input.equals("NO") || input.equals("QUIT")) {
						validInput = true;
						run = false;
						BackgammonView.printQuitMessage();
					}else if(input.equals("YES")){
						validInput = true;
					}else {
						BackgammonView.printError("INVALID INPUT");
					}
				}
			}
		}
		// close input
		scan.close();
		
		return numberOfMatchesPlayed;
	}
	
	public static int test(File file) {
		Scanner scan;
		int numberOfMatchesPlayed = 0;
		try {
			scan = new Scanner(file);
			numberOfMatchesPlayed = run(scan,true);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return numberOfMatchesPlayed;
	}

}
