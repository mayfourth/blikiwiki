package info.bliki.wiki.addon.test.trac;

import junit.framework.Test;
import junit.framework.TestSuite;

public class BasicFilterTest extends TracTestSupport {
	public BasicFilterTest(String name) {
		super(name);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}

	public static Test suite() {
		return new TestSuite(BasicFilterTest.class);
	}

	public void testTT() {
		assertEquals("\n" + "<p><b>hosted by:</b><br/></p>", wikiModel.render("'''hosted by:'''<br>"));
	}

	public void testBlankInput() {
		assertEquals("", wikiModel.render(""));
	}

	public void testNullInput() {
		assertEquals("", wikiModel.render(null));
	}

	public void testCharInput() {
		assertEquals("\n" + "<p>[</p>", wikiModel.render("["));
	}

	public void testStrikeInput() {
		assertEquals("\n" + "<p><del>Hello <b>World</b></del></p>", wikiModel.render("~~Hello '''World'''~~"));
	}

	public void testUnderlineInput() {
		assertEquals("\n" + "<p><u>Hello <b>World</b></u></p>", wikiModel.render("__Hello '''World'''__"));
	}

	public void testSubscriptInput() {
		assertEquals("\n" + "<p><sub>Hello <b>World</b></sub></p>", wikiModel.render(",,Hello '''World''',,"));
	}

	public void testSuperscriptInput() {
		assertEquals("\n" + "<p><sup>Hello <b>World</b></sup></p>", wikiModel.render("^Hello '''World'''^"));
	}

	public void testMonospaceInput01() {
		assertEquals("\n" + "<p><tt>Hello &#39;&#39;&#39;World&#39;&#39;&#39;</tt></p>", wikiModel.render("`Hello '''World'''`"));
	}

	public void testMonospaceInput02() {
		assertEquals("\n" + "<p><tt>\n" + "Hello &#39;&#39;&#39;World&#39;&#39;&#39;</tt></p>", wikiModel
				.render("{{{\nHello '''World'''}}}"));
	}

	public void testPreInput01() {
		assertEquals("\n" + "<p>\n<pre>\n" + "Hello &#39;&#39;&#39;World&#39;&#39;&#39;\n</pre></p>", wikiModel
				.render("{{{\nHello '''World'''\n}}}"));
	}

	public void testParagraph1() {
		assertEquals("\n" + "<p>This is a simple paragraph.</p>", wikiModel.render("This is a simple paragraph."));
	}

	public void testParagraph2() {
		assertEquals("\n" + "<p>This is a simple paragraph.</p>\n" + "<p>A second paragraph.</p>", wikiModel
				.render("This is a simple paragraph.\n\nA second paragraph."));
	}

	public void testParagraph3() {
		assertEquals("\n" + "<p>This is a simple paragraph.</p>\n" + "<p>A second paragraph.</p>", wikiModel
				.render("This is a simple paragraph.\n\nA second paragraph."));
	}

	public void testNowiki01() {
		assertEquals("\n" + "<p>\n" + "* This is not an unordered list item.</p>", wikiModel
				.render("<nowiki>\n* This is not an unordered list item.</nowiki>"));
	}

	public void testNowiki02() {
		assertEquals("\n" + "<p>\n" + "* This is not an unordered list item.</p>", wikiModel
				.render("<noWiki>\n* This is not an unordered list item.</nowiKi>"));
	}

	public void testSimpleList() {
		assertEquals("\n" + 
				"<ul>\n" + 
				"<li>Item 1\n" + 
				"<ul>\n" + 
				"<li>Item 2</li></ul></li>\n" + 
				"<li>Item 3</li></ul>", wikiModel.render("* Item 1\n" + "** Item 2\n"+ "* Item 3"));
	}

	public void testbq1() {
		assertEquals(
				"\n" + "<p /><blockquote style=\"background: white; border: 1px solid rgb(153, 153, 153); padding: 1em;\">\n"
						+ "<p><b>Hello World</b></p></blockquote>",
				wikiModel
						.render("<blockquote style=\"background: white; border: 1px solid rgb(153, 153, 153); padding: 1em;\">\n'''Hello World'''</blockquote>"));
	}

	public void testbq2() {
		assertEquals("\n" + "<p /><blockquote>\n" + "<p>The <b>blockquote</b> command formats block \n"
				+ "quotations, typically by surrounding them \n" + "with whitespace and a slightly different font.\n"
				+ "</p></blockquote>\n" + "", wikiModel.render("<blockquote>\n" + "The \'\'\'blockquote\'\'\' command formats block \n"
				+ "quotations, typically by surrounding them \n" + "with whitespace and a slightly different font.\n" + "</blockquote>\n"));
	}

	public void testPreBlock() {
		assertEquals("\n" + 
				"<p> * Lists are easy to do:\n" + 
				" ** start every line\n" + 
				" * with a star\n" + 
				" ** more stars mean\n" + 
				" *** deeper levels</p>", wikiModel.render(" * Lists are easy to do:\n" + " ** start every line\n" + " * with a star\n"
				+ " ** more stars mean\n" + " *** deeper levels"));
	}

	public void testPBlock() {
		assertEquals(
				"\n"
						+ "<p />\n"
						+ "<p style=\"padding: 1em; border: 1px dashed #2f6fab; color: Black; background-color: #f9f9f9; line-height: 1.1em;\"> <tt>\n"
						+ "&#60;p style=&#34;padding: 1em; border: 1px dashed #2f6fab; color: Black; background-color: #f9f9f9; line-height: 1.1em;&#34;&#62; &#60;tt&#62; <br/>\n"
						+ "&#38;#123;&#38;#124; border=&#34;5&#34; cellspacing=&#34;5&#34; cellpadding=&#34;2&#34; &#60;br&#160;/&#62; <br/>\n"
						+ "&#38;#124; style=&#34;text-align: center;&#34; &#38;#124; &#38;#91;&#38;#91;Image:gnome-system.png]] &#60;br&#160;/&#62; <br/>\n"
						+ "&#38;#124;- &#60;br&#160;/&#62; <br/>\n"
						+ "&#38;#33; Computer &#60;br&#160;/&#62; <br/>\n"
						+ "&#38;#124;- &#60;br&#160;/&#62; <br/>\n"
						+ "<b>&#38;#124; style=&#34;color: yellow; background-color: green;&#34; &#38;#124; Processor Speed: &#38;#60;span style=&#34;color: red;&#34;&#62; 1.8 GHz &#38;#60;/span&#62; &#60;br&#160;/&#62;</b> <br/>\n"
						+ "&#38;#124;&#38;#125; &#60;br&#160;/&#62; <br/>\n" + "&#60;/tt&#62; &#60;/p&#62;\n" + "</tt> </p>",
				wikiModel
						.render("<p style=\"padding: 1em; border: 1px dashed #2f6fab; color: Black; background-color: #f9f9f9; line-height: 1.1em;\"> <tt>\n"
								+ "&#60;p style=\"padding: 1em; border: 1px dashed #2f6fab; color: Black; background-color: #f9f9f9; line-height: 1.1em;\"> &#60;tt> <br />\n"
								+ "&amp;#123;&amp;#124; border=\"5\" cellspacing=\"5\" cellpadding=\"2\" &#60;br&nbsp;&#47;> <br />\n"
								+ "&amp;#124; style=\"text-align: center;\" &amp;#124; &amp;#91;&amp;#91;Image:gnome-system.png]] &#60;br&nbsp;&#47;> <br />\n"
								+ "&amp;#124;- &#60;br&nbsp;&#47;> <br />\n"
								+ "&amp;#33; Computer &#60;br&nbsp;&#47;> <br />\n"
								+ "&amp;#124;- &#60;br&nbsp;&#47;> <br />\n"
								+ "\'\'\'&amp;#124; style=\"color: yellow; background-color: green;\" &amp;#124; Processor Speed: &amp;#60;span style=\"color: red;\"> 1.8 GHz &amp;#60;/span> &#60;br&nbsp;&#47;>\'\'\' <br />\n"
								+ "&amp;#124;&amp;#125; &#60;br&nbsp;&#47;> <br />\n" + "&#60;/tt> &#60;/p>\n" + "</tt> </p>"));
	}

	public void testSimpleTable01() {
		assertEquals("\n" + "<p>hello world\n" + "</p>\n" + "<div style=\"page-break-inside:	avoid;\">\n" + "<table>\n" + "<tr>\n"
				+ "<td>Cell 1</td>\n" + "<td>Cell 2</td>\n" + "<td>Cell 3</td></tr>\n" + "<tr>\n" + "<td>Cell 4</td>\n"
				+ "<td>Cell 5</td>\n" + "<td>Cell 6</td></tr></table></div>\n" + "<p>hello</p>", wikiModel
				.render("hello world\n||Cell 1||Cell 2||Cell 3||\n" + "||Cell 4||Cell 5||Cell 6||\n\nhello"));
	}

	public void testSimpleTable02() {
		assertEquals("\n" + 
				"<div style=\"page-break-inside:	avoid;\">\n" + 
				"<table>\n" + 
				"<tr>\n" + 
				"<td><a href=\"http://www.bliki.info/wiki/CamelCaseCell\" id=\"w\">CamelCaseCell</a> 1</td>\n" + 
				"<td>Cell 2</td>\n" + 
				"<td>Cell 3</td></tr>\n" + 
				"<tr>\n" + 
				"<td>Cell 4</td>\n" + 
				"<td>Cell 5</td>\n" + 
				"<td>Cell 6</td></tr></table></div>", wikiModel.render("||CamelCaseCell 1||Cell 2||Cell 3||\n" + "||Cell 4||Cell 5||Cell 6"));
	}
}