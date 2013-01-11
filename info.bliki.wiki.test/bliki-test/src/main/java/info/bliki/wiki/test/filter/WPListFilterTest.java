package info.bliki.wiki.test.filter;

import junit.framework.Test;
import junit.framework.TestSuite;

public class WPListFilterTest extends FilterTestSupport {
	final public static String LIST0 = "*Mixed list\n" + "*# with numbers\n" + "** and bullets\n" + "*# and numbers\n"
			+ "*bullets again\n" + "**bullet level 2\n" + "***bullet level 3\n" + "***#Number on level 4\n" + "**bullet level 2\n"
			+ "**#Number on level 3\n" + "**#Number [[Level:1|one]]s level 3\n" + "*#number level 2\n" + "*Level 1";

	final public static String LIST1 = "*#*";

	final public static String LIST2 = "# first\n##second";

	final public static String LIST3 = "# test 1\n" + "# test 2\n" + "## test 3\n" + "hello\n" + "## test 4";

	final public static String LIST4 = "# first\n  <!-- stupid comment-->  \n#second";

	final public static String LIST4A = "# first\n<!-- stupid comment-->#second";

	final public static String LIST4B = "# first<!-- stupid comment-->\n#second";

	final public static String LIST4C = "# first\n  <!-- stupid comment-->  \n";

	public WPListFilterTest(String name) {
		super(name);
	}

	public static Test suite() {
		return new TestSuite(WPListFilterTest.class);
	}

	public void testList2() {
		assertEquals("\n" + "<ol>\n" + "<li>first\n" + "<ol>\n" + "<li>second</li></ol></li></ol>", wikiModel.render(LIST2));
	}

	public void testList0() {
		assertEquals("\n" + "<ul>\n" + "<li>Mixed list\n" + "<ol>\n" + "<li>with numbers</li></ol>\n" + "<ul>\n"
				+ "<li>and bullets</li></ul>\n" + "<ol>\n" + "<li>and numbers</li></ol></li>\n" + "<li>bullets again\n" + "<ul>\n"
				+ "<li>bullet level 2\n" + "<ul>\n" + "<li>bullet level 3\n" + "<ol>\n"
				+ "<li>Number on level 4</li></ol></li></ul></li>\n" + "<li>bullet level 2\n" + "<ol>\n" + "<li>Number on level 3</li>\n"
				+ "<li>Number <a href=\"http://www.bliki.info/wiki/Level:1\" id=\"w\">ones</a> level 3</li></ol></li></ul>\n" + "<ol>\n"
				+ "<li>number level 2</li></ol></li>\n" + "<li>Level 1</li></ul>", wikiModel.render(LIST0));
	}

	public void testList1() {
		assertEquals("\n" + "<p>*#*</p>", wikiModel.render(LIST1));
	}

	public void testList3() {
		assertEquals("\n" + "<ol>\n" + "<li>test 1</li>\n" + "<li>test 2\n" + "<ol>\n" + "<li>test 3</li></ol></li></ol>\n"
				+ "<p>hello\n" + "</p>\n" + "<ol>\n" + "<li>\n" + "<ol>\n" + "<li>test 4</li></ol></li></ol>", wikiModel.render(LIST3));
	}

	public void testList4() {
		assertEquals("\n" + "<ol>\n" + "<li>first</li>\n" + "<li>second</li></ol>", wikiModel.render(LIST4));
	}

	public void testList4A() {
		assertEquals("\n" + "<ol>\n" + "<li>first</li>\n" + "<li>second</li></ol>", wikiModel.render(LIST4A));
	}

	public void testList4B() {
		assertEquals("\n" + "<ol>\n" + "<li>first</li>\n" + "<li>second</li></ol>", wikiModel.render(LIST4B));
	}

	public void testList4C() {
		assertEquals("\n" + "<ol>\n" + "<li>first</li></ol>", wikiModel.render(LIST4C));
	}

	public void testList10() {
		assertEquals("\n" + "<ul>\n" + "<li>a simple test\n" + "x+y\n" + "</li></ul>\n" + "<p>test test</p>", wikiModel
				.render("*a simple test<nowiki>\n" + "x+y\n" + "</nowiki>\n" + "test test"));
	}

	public void testList11() {
		assertEquals("\n" + "<ul>\n" + "<li>a simple test blabla</li></ul>\n" + "<p>x+y\n" + "test test</p>", wikiModel
				.render("*a simple test <nowiki>blabla\n" + "x+y\n" + "test test"));
	}

	public void testList12() {
		assertEquals("\n" + "<ul>\n" + "<li>*</li></ul>", wikiModel.render("* *"));
		assertEquals("\n" + "<ul>\n" + "<li>#</li></ul>", wikiModel.render("* #"));
		// TODO solve this wrong JUnit test
		// assertEquals("", wikiModel.render("* :*"));
	}
}