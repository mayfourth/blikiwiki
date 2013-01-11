package info.bliki.api;

import info.bliki.api.creator.DocumentCreator;
import info.bliki.api.creator.WikiDB;
import info.bliki.wiki.filter.Encoder;
import info.bliki.wiki.impl.APIWikiModel;

/**
 * Test to load a page from en.wikipedia.org and render it to a PDF file
 * 
 */
public class PDFCreatorTest {

	public PDFCreatorTest() {
		super();
	}

	private static void testWikipediaENAPI(String title) {
		String[] listOfTitleStrings = {
			title
		};
		String titleURL = Encoder.encodeTitleLocalUrl(title);
		User user = new User("", "", "http://en.wikipedia.org/w/api.php");
		user.login();
		WikiDB db = null;
		String mainDirectory = "c:/temp/";
		// the following subdirectory should not exist if you would like to create a
		// new database
		String databaseSubdirectory = "WikiDB";
		// the following directory must exist for image downloads
		String imageDirectory = "c:/temp/WikiImages";
		try {
			db = new WikiDB(mainDirectory, databaseSubdirectory);
			APIWikiModel myWikiModel = new APIWikiModel(user, db, "${image}", "file:///c:/temp/${title}", imageDirectory);
			DocumentCreator creator = new DocumentCreator(myWikiModel, user, listOfTitleStrings);

			creator.renderPDFToFile(mainDirectory, titleURL + ".pdf", HTMLConstants.CSS_PRINTER_STYLE);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (db != null) {
				try {
					db.tearDown();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void testPDF001() {
		testWikipediaENAPI("Tom Hanks");
	}

	public static void testPDF002() {
		testWikipediaENAPI("Political party strength in California");
	}

	public static void testPDF003() {
		testWikipediaENAPI("Chris Capuano");
	}

	public static void main(String[] args) {
		testPDF001();
		testPDF002();
		testPDF003();
	}
}
