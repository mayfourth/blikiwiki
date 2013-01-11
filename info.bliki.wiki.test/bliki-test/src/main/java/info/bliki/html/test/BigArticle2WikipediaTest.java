package info.bliki.html.test;

import info.bliki.html.HTML2WikiConverter;
import info.bliki.html.wikipedia.ToWikipedia;
import junit.framework.TestCase;

public class BigArticle2WikipediaTest extends TestCase {
	private static final String BIG_HTML = "";

	public BigArticle2WikipediaTest(String name) {
		super(name);
	}

	public void testBigHTML() {
		HTML2WikiConverter conv = new HTML2WikiConverter();
		conv.setInputHTML(BIG_HTML);
		String result = conv.toWiki(new ToWikipedia());
		assertEquals(result, "");
	}

}
