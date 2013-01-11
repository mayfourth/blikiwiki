package info.bliki.wiki.test.code;

import info.bliki.wiki.test.filter.FilterTestSupport;
import junit.framework.Test;
import junit.framework.TestSuite;


public class ABAPTest extends FilterTestSupport
{
	public ABAPTest(String name)
	{
		super(name);
	}

	public static Test suite()
	{
		return new TestSuite(ABAPTest.class);
	} 

	public void testABAP()
	{
		String result = wikiModel.render("'''ABAP Example'''\n" + "<source lang=abap>\n" + "*--- line comment\n"
				+ "WRITE: / '''Hello World''' \"test comment\n" + "< > \" \' &" + "}\n" + "</source>");

		assertEquals(
				"\n" + 
				"<p><b>ABAP Example</b>\n" + 
				"</p><pre class=\"code\"><font color=\"#3F7F5F\">\n" + 
				"*--- line comment\n" + 
				"</font><b><font color=\"#7F0055\">WRITE</font></b>: / <font color=\"#2A00FF\">&#39;&#39;&#39;Hello World&#39;&#39;&#39;</font> <font color=\"#3F7F5F\">&#34;test comment\n" + 
				"</font>&#60; &#62; <font color=\"#3F7F5F\">&#34; &#39; &#38;}\n" + 
				"</font></pre>", result);
	}

}
