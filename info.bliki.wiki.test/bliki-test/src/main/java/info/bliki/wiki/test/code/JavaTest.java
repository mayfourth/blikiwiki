package info.bliki.wiki.test.code;

import info.bliki.wiki.test.filter.FilterTestSupport;
import junit.framework.Test;
import junit.framework.TestSuite;

public class JavaTest extends FilterTestSupport {
	public JavaTest(String name) {
		super(name);
	}

	public static Test suite() {
		return new TestSuite(JavaTest.class);
	}

	public void testJava001() {
		String result = wikiModel.render("'''Java Example'''\n" + "<source lang=java>\n" + "public class Test {\n" + "< > \" \' &"
				+ "}\n" + "</source>");
		assertEquals("\n" + 
				"<p><b>Java Example</b>\n" + 
				"</p><pre class=\"code\">\n" + 
				"<b><font color=\"#7F0055\">public</font></b> <b><font color=\"#7F0055\">class</font></b> Test {\n" + 
				"&#60; &#62; <font color=\"#2A00FF\">&#34; &#39; &#38;}\n" + 
				"</font></pre>", result);
	}

	public void testJava002() {
		String result = wikiModel.render("'''Java Example'''\n" + "<source lang=java>"
				+ "Util util = new Util(\"c:\\\\temp\\\\\");\n" +"util.doIt();"+ "</source>");
		
		assertEquals("\n" + 
				"<p><b>Java Example</b>\n" + 
				"</p><pre class=\"code\">Util util = <b><font color=\"#7F0055\">new</font></b> Util(<font color=\"#2A00FF\">&#34;c:\\\\temp\\\\&#34;</font>);\n" + 
				"util.doIt();</pre>", result);
	}
}
