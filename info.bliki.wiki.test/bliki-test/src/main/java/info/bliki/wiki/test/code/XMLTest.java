package info.bliki.wiki.test.code;

import info.bliki.wiki.test.filter.FilterTestSupport;
import junit.framework.Test;
import junit.framework.TestSuite;


public class XMLTest extends FilterTestSupport
{
	public XMLTest(String name)
	{
		super(name);
	}

	public static Test suite()
	{
		return new TestSuite(XMLTest.class);
	}

	public void testXml()
	{
		String result = wikiModel.render("\'\'\'XML example:\'\'\'\n" + "<source lang=xml>\n" + "  <extension\n"
				+ "           point=\"org.eclipse.help.toc\">\n" + "        <toc\n" + "              file=\"phphelp.xml\"\n"
				+ "              primary=\"true\">\n" + "     <!-- simple comment -->                      \n" + "        </toc>\n"
				+ "  </extension>\n" + "</source>");

		assertEquals(
				"\n" + 
				"<p><b>XML example:</b>\n" + 
				"</p><pre class=\"code\">\n" + 
				"  <b><font color=\"#7F0055\">&#60;extension</font></b>\n" + 
				"           point=<font color=\"#2A00FF\">&#34;org.eclipse.help.toc&#34;</font><font color=\"#7F0055\">&#62;</font>\n" + 
				"        <b><font color=\"#7F0055\">&#60;toc</font></b>\n" + 
				"              file=<font color=\"#2A00FF\">&#34;phphelp.xml&#34;</font>\n" + 
				"              primary=<font color=\"#2A00FF\">&#34;true&#34;</font><font color=\"#7F0055\">&#62;</font>\n" + 
				"     <font color=\"#3F7F5F\">&#60;-- &#60;simple comment --&#62;</font>                      \n" + 
				"        <b><font color=\"#7F0055\">&#60;/toc&#62;</font></b>\n" + 
				"  <b><font color=\"#7F0055\">&#60;/extension&#62;</font></b>\n" + 
				"</pre>", result);
	}

}
