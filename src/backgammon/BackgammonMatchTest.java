package backgammon;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import static org.junit.Assert.*;

import java.io.*;

public class BackgammonMatchTest {

	File matchTestFile1;


	/*
	 * Temp folder and  files created in it will be deleted after tests are run
	 */
	@Rule
	public TemporaryFolder folder = new TemporaryFolder();

	@Before
	public void setUp() {
		try {
			matchTestFile1 = folder.newFile("matchTestFile1.txt");
		} catch (IOException ioe) {
			System.err.println("error creating temp test file");
		}
	}

	/**
	 * Write to the test files and test Match
	 */
	@Test
	public void testGame() {

		// write out data to the test files
		try {
			FileWriter fw1 = new FileWriter(matchTestFile1);

			BufferedWriter bw1 = new BufferedWriter(fw1);

//			bw1.write(getSimpleWinGame());

			bw1.close();

			
		} catch (IOException ioe) {
			System.err.println("error creating temporary test file");
		}

//		assertTrue(matchTestFile1.exists());
//		assertTrue(gameTestFile2.exists());
//
//		
//		BackgammonGame game1 = new BackgammonGame(matchTestFile1);	
//		assertFalse(game1.getBoard().isWon(Colour.WHITE)); //Game not won by either player yet
//		assertFalse(game1.getBoard().isWon(Colour.BLACK)); 
//		game1.playGame();
//		assertTrue(game1.getBoard().isWon(Colour.WHITE)); //Game won by white
//		assertFalse(game1.getBoard().isWon(Colour.BLACK)); 
//
//		
//		assertFalse(game1.getBoard().isGammon(Colour.WHITE)); //Neither player has been gammoned
//		assertFalse(game1.getBoard().isGammon(Colour.WHITE)); 
//		
//		assertFalse(game1.getBoard().isBackgammon(Colour.WHITE));
//		assertTrue(game1.getBoard().isBackgammon(Colour.BLACK));// Black has been backgammoned, not white
//
//		BackgammonGame game2 = new BackgammonGame(gameTestFile2);		
//		game2.playGame();
//		assertFalse(game2.getBoard().isWon(Colour.WHITE)); //Game hasn't been won by either player as it has ended due to double refusal
//		assertFalse(game2.getBoard().isWon(Colour.BLACK)); 

		
		}
	
	

}