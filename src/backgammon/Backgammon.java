package backgammon;

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


		boolean run = true;
		while (run) {
			BackgammonMatch match = new BackgammonMatch(scan);
			match.playMatch();

			if (match.isMatchQuit()) {
				run = false;
			} else {
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
	}

}
