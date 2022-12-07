package backgammon;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import static org.junit.Assert.*;

import java.io.*;

public class BackgammonGameTest {

	File gameTestFile1;
	File gameTestFile2;


	/*
	 * Temp folder and  files created in it will be deleted after tests are run
	 */
	@Rule
	public TemporaryFolder folder = new TemporaryFolder();

	@Before
	public void setUp() {
		try {
			gameTestFile1 = folder.newFile("gameTestFile1.txt");
			gameTestFile2 = folder.newFile("gameTestFile2.txt");
		} catch (IOException ioe) {
			System.err.println("error creating temp test file");
		}
	}

	/**
	 * Write to the test files and test Game
	 */
	@Test
	public void testGame() {

		// write out data to the test files
		try {
			FileWriter fw1 = new FileWriter(gameTestFile1);
			FileWriter fw2 = new FileWriter(gameTestFile2);

			BufferedWriter bw1 = new BufferedWriter(fw1);
			BufferedWriter bw2 = new BufferedWriter(fw2);

			bw1.write(getSimpleWinGame());
			bw2.write(getDoublingGame());

			bw1.close();
			bw2.close();

			
		} catch (IOException ioe) {
			System.err.println("error creating temporary test file");
		}

		assertTrue(gameTestFile1.exists());
		assertTrue(gameTestFile2.exists());

		
		BackgammonGame game1 = new BackgammonGame(gameTestFile1);	
		assertFalse(game1.getBoard().isWon(Colour.WHITE)); //Game not won by either player yet
		assertFalse(game1.getBoard().isWon(Colour.BLACK)); 
		game1.playGame();
		assertTrue(game1.getBoard().isWon(Colour.WHITE)); //Game won by white
		assertFalse(game1.getBoard().isWon(Colour.BLACK)); 

		
		assertFalse(game1.getBoard().isGammon(Colour.WHITE)); //Neither player has been gammoned
		assertFalse(game1.getBoard().isGammon(Colour.WHITE)); 
		
		assertFalse(game1.getBoard().isBackgammon(Colour.WHITE));
		assertTrue(game1.getBoard().isBackgammon(Colour.BLACK));// Black has been backgammoned, not white

		BackgammonGame game2 = new BackgammonGame(gameTestFile2);		
		game2.playGame();
		assertFalse(game2.getBoard().isWon(Colour.WHITE)); //Game hasn't been won by either player as it has ended due to double refusal
		assertFalse(game2.getBoard().isWon(Colour.BLACK)); 

		
		}
	
	public static String getSimpleWinGame() {
		return "\n\n\ndice 6 3\n"
				+ "c\n"
				+ "d\n"
				+ "dice 3 5\n"
				+ "a\n"
				+ "b\n"
				+ "dice 6 6\n"
				+ "d\n"
				+ "c\n"
				+ "c\n"
				+ "b\n"
				+ "dice 5 5\n"
				+ "c\n"
				+ "c\n"
				+ "c\n"
				+ "b\n"
				+ "dice 6 6\n"
				+ "b\n"
				+ "b\n"
				+ "a\n"
				+ "a\n"
				+ "dice 5 5\n"
				+ "c\n"
				+ "b\n"
				+ "a\n"
				+ "a\n"
				+ "dice 6 6\n"
				+ "b\n"
				+ "c\n"
				+ "b\n"
				+ "a\n"
				+ "dice 2 3\n"
				+ "a\n"
				+ "e\n"
				+ "dice 6 6\n"
				+ "a\n"
				+ "a\n"
				+ "a\n"
				+ "a\n"
				+ "dice 3 3\n"
				+ "a\n"
				+ "b\n"
				+ "c\n"
				+ "c\n"
				+ "dice 6 6\n"
				+ "a\n"
				+ "a\n"
				+ "a\n"
				+ "a\n"
				+ "dice 2 3\n"
				+ "e\n"
				+ "d\n"
				+ "dice 6 6\n"
				+ "a\n"
				+ "a\n"
				+ "a\n"
				+ "a\n"
				+ "dice 6 5\n"
				+ "c\n"
				+ "b\n"
				+ "dice 5 5\n"
				+ "a\n"
				+ "a\n"
				+ "a\n"
				+ "a\n"
				+ "dice 4 3\n"
				+ "h\n"
				+ "e\n"
				+ "dice 1 1\n"
				+ "a\n"
				+ "a\n"
				+ "a\n"
				+ "a\n";
	}

	public static String getDoublingGame() {
		return "\n\n"
				+ "\n"
				+ "\n"
				+ "dice 1 2\n"
				+ "a\n"
				+ "a\n"
				+ "double\n"
				+ "accept\n"
				+ "dice 3 4\n"
				+ "a\n"
				+ "a\n"
				+ "double\n"
				+ "refuse\n"
				+ "\n"
				+ "\n"
				+ "\n";
	}


}