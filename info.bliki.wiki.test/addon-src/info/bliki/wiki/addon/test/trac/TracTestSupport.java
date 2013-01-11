package info.bliki.wiki.addon.test.trac;

import info.bliki.htmlcleaner.TagNode;
import info.bliki.wiki.addon.trac.TracModel;
import junit.framework.TestCase;


/**
 * Support class for defining JUnit FilterTests.
 * 
 */  
public class TracTestSupport extends TestCase {
	public static final String WINDOWS_NEWLINE = "\r\n";

	public static final String UNIX_NEWLINE = "\n";

	public static final String NEWLINE = WINDOWS_NEWLINE;

	protected TracModel wikiModel = null;

	public TracTestSupport(String s) {
		super(s);
	}

	/**
	 * Set up a test model, which contains predefined templates
	 */
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		TagNode.addAllowedAttribute("style");
		wikiModel = new TracTestModel("http://www.bliki.info/wiki/${image}", "http://www.bliki.info/wiki/${title}");
		wikiModel.setUp();
	}

	/**
	 * simple example
	 */
	public static void main(String[] args) {
		TracModel wikiModel = new TracTestModel("http://www.bliki.info/wiki/${image}", "http://www.bliki.info/wiki/${title}");
		String htmlStr = wikiModel.render("This is a simple [[Hello World]] wiki tag");
		System.out.print(htmlStr);
	}
}
