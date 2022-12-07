package backgammon;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import static org.junit.Assert.*;

import java.io.*;

public class BackgammonTest {

	File testFile1;

	/*
	 * Temp folder and files created in it will be deleted after tests are run
	 */
	@Rule
	public TemporaryFolder folder = new TemporaryFolder();

	@Before
	public void setUp() {
		try {
			testFile1 = folder.newFile("testFile1.txt");
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
			FileWriter fw1 = new FileWriter(testFile1);

			BufferedWriter bw1 = new BufferedWriter(fw1);

			bw1.write(BackgammonMatchTest.getMatchString()
					+ "\nyes\n"
					+ BackgammonMatchTest.getMatchString()
					+ "\nyes\n"
					+ BackgammonMatchTest.getMatchString()
					+ "\nno\n");
			
	

			bw1.close();

			int numberOfMatchesPlayed = Backgammon.test(testFile1);
			assertEquals(3, numberOfMatchesPlayed);

		} catch (IOException ioe) {
			System.err.println("error creating temporary test file");
		}

	}


}