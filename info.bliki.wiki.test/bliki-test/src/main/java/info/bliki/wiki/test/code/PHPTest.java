package info.bliki.wiki.test.code;

import info.bliki.wiki.test.filter.FilterTestSupport;
import junit.framework.Test;
import junit.framework.TestSuite;


public class PHPTest extends FilterTestSupport
{
	public PHPTest(String name)
	{
		super(name);
	}

	public static Test suite()
	{
		return new TestSuite(PHPTest.class);
	}

	public void testPHP()
	{
		String result = wikiModel.render("<source lang=php>\n" + "<?php\n" + "/* A simple php script */\n" + " \n" + "$choice = $_GET[\'foo\'];\n"
				+ "if ( $choice == 1 )\n" + "{\n" + "  echo \"Hello world\";\n" + "}\n" + "# end of script\n" + "?>\n" + "</source>");

		assertEquals( 
				"<pre class=\"code\">\n" + 
				"<font color=\"#7F0055\">&#60;?php</font>\n" + 
				"<font color=\"#3F7F5F\">/* A simple php script */</font>\n" + 
				" \n" + 
				"$choice = $_GET[<font color=\"#2A00FF\">&#39;foo&#39;</font>];\n" + 
				"<b><font color=\"#7F0055\">if</font></b> ( $choice == 1 )\n" + 
				"{\n" + 
				"  <b><font color=\"#7F0055\">echo</font></b> <font color=\"#2A00FF\">&#34;Hello world&#34;</font>;\n" + 
				"}\n" + 
				"# end of script\n" + 
				"<font color=\"#7F0055\">?&#62;</font>\n" + 
				"</pre>", result);
	}
}
