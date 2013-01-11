package info.bliki.wiki.test.filter;

import junit.framework.Test;
import junit.framework.TestSuite;

public class DefinitionListFilterTest extends FilterTestSupport {
	public DefinitionListFilterTest(String name) {
		super(name);
	}

	public static Test suite() {
		return new TestSuite(DefinitionListFilterTest.class);
	}

	public void testDefinitionList0() {
		assertEquals(
				"<dl><dd><i>There is also an <a href=\"http://www.bliki.info/wiki/Asteroid\" id=\"w\">asteroid</a> <a href=\"http://www.bliki.info/wiki/9969_Braille\" id=\"w\">9969 Braille</a></i></dd></dl>",
				wikiModel.render(":''There is also an [[asteroid]] [[9969 Braille]]''"));
	}

	public void testDefinitionList1() {
		assertEquals("<dl><dt>name</dt><dd>Definition</dd></dl>", wikiModel.render(";name:Definition"));
	}

	public void testDefinitionList2() {
		assertEquals("<dl><dt>name</dt><dd>Definition</dd></dl>", wikiModel.render("; name : Definition"));
	}

	public void testDefinitionList10() {
		assertEquals("<dl><dd>a simple test\n" + "  x+y\n" + "  </dd></dl>\n" + "<p>test test</p>", wikiModel
				.render(":a simple test<nowiki>\n" + "  x+y\n" + "  </nowiki>\n" + "test test"));
	}

	public void testDefinitionList11() {
		assertEquals("<dl><dd>a simple test<span class=\"math\">ein text</span></dd></dl>\n" + "<pre>\n x+y\n" + "  \n</pre>\n"
				+ "<p>test test</p>", wikiModel.render(":a simple test<math>ein text\n" + "  x+y\n" + "  \n" + "test test"));
	}
	
}