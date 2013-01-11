package info.bliki.wiki.addon.test.trac;

import java.util.Set;

import junit.framework.Test;
import junit.framework.TestSuite;

public class TracLinkFilterTest extends TracTestSupport {
	public TracLinkFilterTest(String name) {
		super(name);
	}

	public static Test suite() {
		return new TestSuite(TracLinkFilterTest.class);
	}

	public void testLinkHash() {
		assertEquals(
				"\n"
						+ "<ol>\n"
						+ "<li>\n"
						+ "<ol>\n"
						+ "<li><a href=\"http://www.bliki.info/wiki/Using_Eclipse_Wikipedia_Editor:Getting_Started#Features\" id=\"w\">Features</a></li></ol></li></ol>",
				wikiModel.render("##[[Using Eclipse Wikipedia Editor:Getting Started#Features|Features]]"));
	}

	public void testLink() {
		assertEquals(
				"\n"
						+ "<p>You could open the <a href=\"http://www.bliki.info/wiki/Wikipedia:sandbox\" id=\"w\">sandbox</a> in a separate window or tab to be able to see both this text and your tests in the sandbox.</p>",
				wikiModel
						.render("You could open the [[Wikipedia:sandbox|sandbox]] in a separate window or tab to be able to see both this text and your tests in the sandbox."));
	}

	public void testLink0() {
		assertEquals("\n" + "<p>[X]</p>", wikiModel.render("[X]"));
	}

	public void testLink1() {
		assertEquals("\n" + "<p><a href=\"http://en.wikipedia.org/wiki/Test\">Test</a></p>", wikiModel.render("[[en:Test|Test]]"));
	}

	public void testLink2() {
		assertEquals("\n" + "<p><a href=\"http://www.bliki.info/wiki/Test\" id=\"w\">Test</a></p>", wikiModel.render("[[Test|Test]]"));
	}

	public void testLink3() {
		assertEquals("\n" + "<p><a href=\"http://www.bliki.info/wiki/Category:Test_page\" id=\"w\">Category:Test page</a></p>",
				wikiModel.render("[[:Category:Test page]]"));
	}

	/**
	 * Categories are not rendered
	 * 
	 */
	public void testLink4() {
		assertEquals("\n" + "<p />", wikiModel.render("[[Category:Tips and Tricks]]"));
		Set set = wikiModel.getCategories();
		assertTrue(set.contains("Tips and Tricks"));
	}

	public void testLink5() {
		assertEquals("\n" + "<p><a href=\"http://wikitravel.org/en/Test\">wikitravel:test</a></p>", wikiModel
				.render("[[wikitravel:test]]"));
	}

	public void testLink6() {
		assertEquals("\n" + "<p><a href=\"http://www.bliki.info/wiki/Test:hello_world\" id=\"w\">hello world</a></p>", wikiModel
				.render("[[Test:hello world|]]"));
	}

	public void testLink7() {
		assertEquals("\n" + "<p><a href=\"http://www.bliki.info/wiki/Test(hello_world)\" id=\"w\">Test</a></p>", wikiModel
				.render("[[Test(hello world)|]]"));
	}

	public void testLink8() {
		assertEquals("\n" + "<p><a href=\"http://www.bliki.info/wiki/Boston%2C_Massachusetts\" id=\"w\">Boston</a></p>", wikiModel
				.render("[[Boston, Massachusetts|]]"));
	}

	public void testLink9() {
		assertEquals("\n" + "<p>test <a href=\"http://www.bliki.info/wiki/Lets_start%0Aa_2_rows_link\" id=\"w\">lets start\n"
				+ "a 2 rows link</a> test</p>", wikiModel.render("test [[lets start\na 2 rows link]] test"));
	}

	public void testLink10() {
		assertEquals("\n" + "<p>test <a href=\"http://www.bliki.info/wiki/Lets_start\" id=\"w\">\n"
				+ "a 2 rows piped link</a> test</p>", wikiModel.render("test [[lets start|\na 2 rows piped link]] test"));
	}

	public void testLink11() {
		assertEquals("\n" + "<p>test\n" + "</p>\n" + "<ul>\n" + "<li>blabla[[List of cities by country#Morocco|</li></ul>\n"
				+ "<p>Cities in Morocco]]</p>", wikiModel.render("test\n*blabla[[List of cities by country#Morocco|\nCities in Morocco]]"));
	}

	//
	public void testLink12() {
		assertEquals(
				"\n"
						+ "<p>kellereien wie <a href=\"http://www.bliki.info/wiki/Henkell_%26amp%3B_S%C3%B6hnlein\" id=\"w\">Henkell</a>, <a href=\"http://www.bliki.info/wiki/S%C3%B6hnlein\" id=\"w\">Söhnlein</a></p>",
				wikiModel.render("kellereien wie [[Henkell & Söhnlein|Henkell]], [[Söhnlein]]"));
		Set set = wikiModel.getLinks();
		assertTrue(set.contains("Söhnlein"));
		assertTrue(set.contains("Henkell &amp; Söhnlein"));
	}

	public void testLink13() {
		assertEquals(
				"\n"
						+ "<p>test <a href=\"http://www.bliki.info/wiki/Lets_start_a_%5B%5Bnested%5D%5D_link\" id=\"w\">lets start a [[nested]] link</a> test</p>",
				wikiModel.render("test [[lets start a [[nested]] link]] test"));
		Set set = wikiModel.getLinks();
		assertTrue(set.contains("lets start a [[nested]] link"));
	}

	public void testInterwiki1() {
		assertEquals("\n" + "<p><a href=\"http://de.wikipedia.org/wiki/Johann_Wolfgang_von_Goethe\">Goethes</a> Faust</p>", wikiModel
				.render("[[de:Johann Wolfgang von Goethe|Goethe]]s Faust"));
	}

	public void testTracLink01() {
		assertEquals("\n" + "<p><a href=\"http://www.bliki.info/wiki/Test\" id=\"w\">Test Text</a></p>", wikiModel
				.render("[wiki:Test Test Text]"));
	}

	public void testTracLink02() {
		assertEquals("\n" + 
				"<p><a href=\"http://www.bliki.info/wiki/A_Test\" id=\"w\">Test Text</a></p>", wikiModel
				.render("[wiki:\"A Test\" Test Text]"));
	}
	
	public void testTracLink03() {
		assertEquals("\n" + 
				"<p><a href=\"http://www.bliki.info/wiki/Space_Matters\" id=\"w\">Space Matters</a></p>", wikiModel
				.render("[wiki:\"Space Matters\"]"));
	}
	
	public void testTracLink04() {
		assertEquals("\n" + 
				"<p><a href=\"http://www.bliki.info/wiki/Wiki_Page\" id=\"w\">Wiki Page</a></p>", wikiModel
				.render("[\"Wiki Page\"]"));
	}
	
//	public void testTracLink05() {
//		assertEquals("\n" + 
//				"<ul>\n" + 
//				"<li><a href=\"http://www.bliki.info/wiki/Wiki_page\" id=\"w\">Wiki_page</a>, <a href=\"http://www.bliki.info/wiki/ISO9000\" id=\"w\">ISO9000</a></li>\n" + 
//				"<li><a href=\"http://www.bliki.info/wiki/Space_Matters\" id=\"w\">Space Matters</a> that page name embeds space characters</li>\n" + 
//				"<li>or simply: <a href=\"http://www.bliki.info/wiki/WikiPageName\" id=\"w\">WikiPageName</a>s (!MoinMoin&#39;s internal free links style)</li></ul>", wikiModel
//				.render(" * [wiki:Wiki_page], [wiki:ISO9000]\n" + 
//						" * [wiki:\"Space Matters\"] that page name embeds space characters\n" + 
//						" * or simply: [\"WikiPageName\"]s (!MoinMoin\'s internal free links style)"));
//	}
	
	public void testTracBadLink01() {
		assertEquals("\n" + 
				"<p>[Wiki Page]</p>", wikiModel
				.render("[Wiki Page]"));
	}
	
	public void testTracCamelCase01() {
		assertEquals("\n" + 
				"<p>This is a <a href=\"http://www.bliki.info/wiki/TestText\" id=\"w\">TestText</a> wiki link.</p>", wikiModel
				.render("This is a TestText wiki link."));
	}
	
	public void testTracCamelCase02() {
		assertEquals("\n" + 
				"<p>This is a <a href=\"http://www.bliki.info/wiki/Test/Text\" id=\"w\">Test/Text</a> wiki link.</p>", wikiModel
				.render("This is a Test/Text wiki link."));
	}
	
	public void testBadTracCamelCase01() {
		assertEquals("\n" + 
				"<p>This is a ATest wiki link.</p>", wikiModel
				.render("This is a ATest wiki link."));
	}
}
