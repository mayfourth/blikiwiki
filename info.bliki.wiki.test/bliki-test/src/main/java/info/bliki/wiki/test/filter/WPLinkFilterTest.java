package info.bliki.wiki.test.filter;

import java.util.Map;
import java.util.Set;

import junit.framework.Test;
import junit.framework.TestSuite;

public class WPLinkFilterTest extends FilterTestSupport {
	public WPLinkFilterTest(String name) {
		super(name);
	}

	public static Test suite() {
		return new TestSuite(WPLinkFilterTest.class);
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
	public void testCategory01() {
		assertEquals("", wikiModel.render("[[Category:Tips and Tricks]]"));
		Map<String, String> map = wikiModel.getCategories();
		assertTrue(map.containsKey("Tips and Tricks"));
	}

	public void testCategory02() {
		assertEquals("", wikiModel.render("[[Category:Rock and Roll Hall of Fame inductees|Beatles, The]]"));
		Map<String, String> map = wikiModel.getCategories();
		// assertTrue(map.containsKey("Rock and Roll Hall of Fame inductees"));
		assertTrue(map.containsValue("Beatles, The"));
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
		assertEquals("\n" + "<p>test <a href=\"http://www.bliki.info/wiki/Lets_start\" id=\"w\">a 2 rows piped link</a> test</p>",
				wikiModel.render("test [[lets start|\na 2 rows piped link]] test"));
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
		assertEquals("\n" + "<p>test [[lets start a <a href=\"http://www.bliki.info/wiki/Nested\" id=\"w\">nested</a> link]] test</p>",
				wikiModel.render("test [[lets start a [[nested]] link]] test"));
		Set<String> set = wikiModel.getLinks();
		assertTrue(set.contains("nested"));
	}

	public void testInterwiki1() {
		assertEquals("\n" + "<p><a href=\"http://de.wikipedia.org/wiki/Johann_Wolfgang_von_Goethe\">Goethes</a> Faust</p>", wikiModel
				.render("[[de:Johann Wolfgang von Goethe|Goethe]]s Faust"));
	}

	public void testSectionLink01() {
		assertEquals("\n" + "<p><a href=\"#Section_Link\" id=\"w\">A Section Link</a></p>", wikiModel
				.render("[[#Section Link|A Section Link]]"));
	}

	public void testSectionLink02() {
		assertEquals("\n" + "<p><a href=\"#Section%C3%A4%C3%B6%C3%BC\" id=\"w\" /></p>", wikiModel.render("[[#Sectionäöü]]"));
	}

	public void testRedirect01() {
		assertEquals("", wikiModel.render("#REDIRECT [[Official position]]"));
		assertEquals("Official position", wikiModel.getRedirectLink());
	}

	public void testRedirect02() {
		assertEquals("", wikiModel.render(" \r \n  #REDIRECT[[Official position]] bla \n other blabls"));
		assertEquals("Official position", wikiModel.getRedirectLink());
	}

	// public static void main(String[] args) {
	// String test = Encoder.encode("erreäöü öüä++", ".");
	// System.out.println(test);
	// }
}
