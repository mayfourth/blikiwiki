package info.bliki.wiki.addon.test.filter;

import info.bliki.wiki.model.SemanticRelation;

import java.util.List;

import junit.framework.Test;
import junit.framework.TestSuite;

public class YacasTest extends AddonTestSupport {
	public YacasTest(String name) {
		super(name);
	}

	public static Test suite() {
		return new TestSuite(YacasTest.class);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}

	public void testYacas01() {

		assertEquals(
				"\n" + 
				"<p>test \n" + 
				"<applet name=\"datahub\" codebase=\"../static/lib\" archive=\"yacas.jar\" code=\"net.sf.yacas.DatahubApplet\" width=\"1\" height=\"1\">\n" + 
				"</applet>\n" + 
				"<applet name=\"console\" codebase=\"../static/lib\" archive=\"yacas.jar\" code=\"net.sf.yacas.ConsoleApplet\" width=\"640\" height=\"240\">\n" + 
				"\n" + 
				"  <param name=\"bkred\" value=\"224\">\n" + 
				"  <param name=\"bkgrn\" value=\"224\">\n" + 
				"  <param name=\"bkblu\" value=\"224\">\n" + 
				"\n" + 
				"  <param name=\"programMode\" value=\"console\">\n" + 
				"\n" + 
				"  <param name=\"progressbar\" value=\"true\">\n" + 
				"  <param name=\"boxmessage\" value=\"Loading Yacas...\">\n" + 
				"\n" + 
				"  <param name=\"init1\" value=\"Load(\'\'yacasinit.ys\'\')\">\n" + 
				"  <param name=\"init2\" value=\"TeXWrite(x):=WriteString(TeXForm(x));\">\n" + 
				"  <param name=\"init3\" value=\"PrettyPrinter\'Set(\'\'TeXWrite\'\')\">\n" + 
				"  <param name=\"history1\" value=\"Write(\'\'$plot2d:pencolor 255 128 128 pensize 3.0 lines2d 4 1.0 1.0 2.0 2.0 3.0 5.0 4 4$\'\')\">\n" + 
				"\n" + 
				"Please <a href=\\\"http://java.sun.com/getjava\\\">install Java 1.5</a> (or later) to use this wiki.\n" + 
				"</applet> tag.</p>", wikiModel.render("test <yacas /> tag."));
		List<SemanticRelation> list = wikiModel.getSemanticRelations();
		assertTrue(list == null);
	} 
}
