package info.bliki.wiki.addon.test.filter.latex;

import junit.framework.Test;
import junit.framework.TestSuite;

public class JavaTest extends LaTeXTestSupport {
	public JavaTest(String name) {
		super(name);
	}

	String table = "{| border=\"1\" cellpadding=\"2\" cellspacing=\"0\" width=\"100%\"\n" + "|-\n" + "!What it looks like\n"
			+ "!What you type\n" + "|-\n" + "|\n" + "{{alert|content=test content}}\n" + "|<pre>\n"
			+ "{{alert|content=test content}}\n" + "</pre>\n" + "|}\n";

	public static Test suite() {
		return new TestSuite(JavaTest.class);
	}

	public void testJava() {
		String result = wikiModel.renderLaTeX("'''Java Example'''\n" + "<source lang=\"java\">\n" + "public class Test {\n" + "< > \" \' &" + "}\n"
				+ "</source>");

		assertEquals("\n" + 
				"\\bfseries{}Java Example\\mdseries{}\n" + 
				"\n" + 
				"\\begin{verbatim}\n" + 
				"public class Test {\n" + 
				"< > \" \' &}\n" + 
				"\\end{verbatim}", result);
	}
	
	public void testTable() {
		String result = wikiModel.renderLaTeX(table);

		assertEquals("\\begin{center}\n" + 
				"\\begin{tabular}{|l|l|}\n" + 
				"\\hline \n" + 
				"\\textbf{What it looks like}	&	\\textbf{What you type}\\\\ \\hline \n" + 
				"\n" + 
				"$\\{$$\\{$alert$\\}$$\\}$	&	\n" + 
				"\n" + 
				"\\texttt{\\lines{\\\\\n" + 
				"$\\{$$\\{$alert$\\}$$\\}$\\\\\n" + 
				"}}\n" + 
				"\\\\ \\hline \n" + 
				"\\end{tabular}\n" + 
				"\\end{center}\n" + 
				"\n" + 
				"\n" + 
				"", result);
	}
}
 