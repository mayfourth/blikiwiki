package info.bliki.wiki.test.code;

import info.bliki.wiki.test.filter.FilterTestSupport;
import junit.framework.Test;
import junit.framework.TestSuite;

public class GroovyTest extends FilterTestSupport {
	public GroovyTest(String name) {
		super(name);
	}

	public static Test suite() {
		return new TestSuite(GroovyTest.class);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}

	public void testGroovy() {
		String result = wikiModel.render("'''Groovy Example'''\n" + "<source lang=\"groovy\">\n" + "public class Test {\n" + "< > \" \' &" + "}\n"
				+ "</source>");

		assertEquals("\n" + 
				"<p><b>Groovy Example</b>\n" + 
				"</p><pre class=\"code\">\n" + 
				"<b><font color=\"#7F0055\">public</font></b> <b><font color=\"#7F0055\">class</font></b> Test {\n" + 
				"&#60; &#62; <font color=\"#2A00FF\">&#34; &#39; &#38;}\n" + 
				"</font></pre>", result);
	}

}
