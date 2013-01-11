package info.bliki.wiki.addon.test.filter;

import info.bliki.wiki.model.WikiModel;
import junit.framework.TestCase;

/**
 * Support class for defining JUnit Addon tests.
 * 
 */

public class AddonTestSupport extends TestCase {
	public static final String WINDOWS_NEWLINE = "\r\n";

	public static final String UNIX_NEWLINE = "\n";

	public static final String NEWLINE = WINDOWS_NEWLINE;

	protected WikiModel wikiModel = null;

	public AddonTestSupport(String s) {
		super(s);
	}

	/**
	 * Set up a test model, which contains predefined templates
	 */
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		wikiModel = new AddonTestModel("http://www.bliki.info/wiki/${image}", "http://www.bliki.info/wiki/${title}");
		wikiModel.setUp();
	}

}
