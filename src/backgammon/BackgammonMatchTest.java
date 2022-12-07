package backgammon;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import static org.junit.Assert.*;

import java.io.*;
import java.util.Scanner;


/**
 * This program is the BackgammonMatchTest class
 * @author Dylan Walsh
 */

/**
 * A {@code BackgammonMatchTest} Class is an integration test for BackgammonMatch class
 */
public class BackgammonMatchTest {

	File matchTestFile1;

	/*
	 * Temp folder and files created in it will be deleted after tests are run
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
	public void testMatch() {

		// write out data to the test files
		try {
			FileWriter fw1 = new FileWriter(matchTestFile1);

			BufferedWriter bw1 = new BufferedWriter(fw1);

			bw1.write(getMatchString());

			bw1.close();

			Scanner filescan = new Scanner(matchTestFile1);

			BackgammonMatch match1 = new BackgammonMatch(filescan);
			match1.testMode();
			assertTrue(matchTestFile1.exists());
			match1.playMatch();
			assertEquals(0, match1.getPlayer2().getScore());
			assertEquals(4, match1.getPlayer1().getScore());

		} catch (IOException ioe) {
			System.err.println("error creating temporary test file");
		}

	}

	public static String getMatchString() {
		return "mario\n" + "luigi\n" + "3\n" + "\n" + "\n" + BackgammonGameTest.getDoublingGame() + "\n"
				+ BackgammonGameTest.getSimpleWinGame() + "\n";
	}

}