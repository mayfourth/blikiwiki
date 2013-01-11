package info.bliki.api;

import java.io.IOException;

import junit.framework.TestCase;

/**
 * Tests for a local MediaWiki installation
 *
 */
public class MediaWikiCommTest extends TestCase {
	public final String TEST_CONTENT = "<big>'''MediaWiki has been successfully installed.'''</big>\n" + "\n" + "äöüßÄÖÜ\n" + "\n"
			+ "hello world 4\n" + "\n"
			+ "Consult the [http://meta.wikimedia.org/wiki/Help:Contents User's Guide] for information on using the wiki software.\n"
			+ "\n" + "== Getting started ==\n" + "\n"
			+ "* [http://www.mediawiki.org/wiki/Help:Configuration_settings Configuration settings list]\n"
			+ "* [http://www.mediawiki.org/wiki/Help:FAQ MediaWiki FAQ]\n"
			+ "* [http://mail.wikimedia.org/mailman/listinfo/mediawiki-announce MediaWiki release mailing list]\n" + "\n" + "[[Testing]]";

	public MediaWikiCommTest(String name) {
		super(name);
	}

	public void testCommunication001() {
		String editUrl = "http://localhost/wiki192/index.php";
		// String apiUrl = "http://localhost/wiki192/api.php";
		// User user = new User("Tester", "test1234", apiUrl);
		// user.login();

		String[] cookiePairs = {};
		MediaWikiComm comm = new MediaWikiComm(editUrl, cookiePairs);

		try {
			comm.login("Tester", "test1234", false);
			comm.saveData("Main_Page", TEST_CONTENT);
			comm.logout();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (UnexpectedAnswerException e) {
			e.printStackTrace();
		} catch (MethodException e) {
			e.printStackTrace();
		}
	}

	public void testWrongPasswordLogin() {
		String editUrl = "http://localhost/wiki192/index.php";
		String[] cookiePairs = {};
		MediaWikiComm comm = new MediaWikiComm(editUrl, cookiePairs);

		try {
			comm.login("Tester", "wrongPassword", false);
		} catch (UnexpectedAnswerException e) {
			assertEquals("Probably logout not successful: responseCode == 200: Incorrect password entered. Please try again.", e
					.getMessage());
			return;
		} catch (MethodException e) {
			e.printStackTrace();
		}
		assertFalse(true);
	}
}
