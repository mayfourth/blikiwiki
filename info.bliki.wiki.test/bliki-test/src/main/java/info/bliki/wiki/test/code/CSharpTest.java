package info.bliki.wiki.test.code;

import info.bliki.wiki.test.filter.FilterTestSupport;
import junit.framework.Test;
import junit.framework.TestSuite;


public class CSharpTest extends FilterTestSupport
{
	public CSharpTest(String name)
	{
		super(name);
	}

	public static Test suite()
	{
		return new TestSuite(CSharpTest.class);
	}

	public void testCSharp()
	{
		String result = wikiModel.render("'''C# Example'''\n" + "<source lang=csharp>\n" + "public class Test {\n" + "< > \" \' &" + "}\n"
				+ "</source>");

		assertEquals("\n" + 
				"<p><b>C# Example</b>\n" + 
				"</p><pre class=\"code\">\n" + 
				"<b><font color=\"#7F0055\">public</font></b> <b><font color=\"#7F0055\">class</font></b> Test {\n" + 
				"&#60; &#62; <font color=\"#2A00FF\">&#34; &#39; &#38;}\n" + 
				"</font></pre>", result);
	}
}
