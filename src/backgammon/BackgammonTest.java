package backgammon;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import static org.junit.Assert.*;

import java.io.*;

public class BackgammonTest {

	File testFile;

	
	/*
	 * Temp folder and  files created in it will be deleted after tests are run
	 */
	@Rule
	public TemporaryFolder folder = new TemporaryFolder();

	@Before
	public void setUp() {
		try {
			testFile = folder.newFile("gameTestFile.txt");
		} catch (IOException ioe) {
			System.err.println("error creating temp test file");
		}
	}

	/**
	 * Write to the test files and test Game
	 */
	@Test
	public void testGame() {
		// write out test data to the test file
		try {
			FileWriter fw = new FileWriter(testFile);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("sam\n"
					+ "dylan\n"
					+ "3\n"
					+ "\n"
					+ "\n"
					+ "dice 3 4\na\na\n".repeat(5)
					+ "\n"
					+ "\n"
					+ "\n"
					+ "\n"
					+ "\n");
	
			bw.close();
			
			
			final InputStream original = System.in;
		    final FileInputStream fips = new FileInputStream(testFile);
		    System.setIn(fips);
		    Backgammon.main(null);
		    System.setIn(original);

			
		} catch (IOException ioe) {
			System.err.println("error creating temp test file");
		}

		assertTrue(testFile.exists());

		}
	
	
}