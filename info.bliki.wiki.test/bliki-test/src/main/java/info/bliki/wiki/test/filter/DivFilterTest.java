package info.bliki.wiki.test.filter;

import junit.framework.Test;
import junit.framework.TestSuite;

public class DivFilterTest extends FilterTestSupport {
	public DivFilterTest(String name) {
		super(name);
	}

	public static Test suite() {
		return new TestSuite(DivFilterTest.class);
	}

	public void testDivTag1() {
		assertEquals(
				"\n" + 
				"<div class=\"TabelleFeldListe\">\n" + 
				"<div class=\"TabelleFeldListeHeader\">AuftragsKopf Felder</div>\n" + 
				"\n" + 
				"<ul>\n" + 
				"<li><a href=\"http://www.bliki.info/wiki/Item1\" id=\"w\">item1</a></li>\n" + 
				"<li><a href=\"http://www.bliki.info/wiki/Item2\" id=\"w\">item2</a></li>\n" + 
				"<li>...</li></ul></div>",
				wikiModel
						.render("<div class=\"TabelleFeldListe\">\n"
								+ "<div class=\"TabelleFeldListeHeader\">AuftragsKopf Felder</div>\n"
								+ "*[[item1]]\n" + "*[[item2]]\n" + "*...\n"
								+ "</div>"));
	}
}