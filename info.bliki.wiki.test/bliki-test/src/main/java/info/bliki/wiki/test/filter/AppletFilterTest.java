package info.bliki.wiki.test.filter;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AppletFilterTest extends FilterTestSupport {
	public AppletFilterTest(String name) {
		super(name);
	}

	public static Test suite() {
		return new TestSuite(AppletFilterTest.class);
	}

	public void testApplet001() {
		assertEquals("\n", wikiModel.render("<yacas init1=\"init1\" history1=\"history\" init2=\"init2\" init3=\"init3\"><yacas/>"));
	}
	public void testApplet002() {
		assertEquals("\n", wikiModel.render("<yacaseval expr=\"D(x)Sin(x)\" />"));
	}
	public void testApplet003() {
		assertEquals("\n", wikiModel.render("<yacaseval expr=\"a < b\" />"));
	}
	public void testApplet004() {
		assertEquals(
				"\n"
						+ "<p />\n"
						+ "<applet archive=\"webcompmath.jar\" code=\"net.sourceforge.webcompmath.applets.SecantTangent\" codebase=\"./assets\" height=\"400\" width=\"480\">\n"
						+ "<param\n" + "name=\"UseRestoreButton\" value=\"yes\" />\n" + "<param name=\"UseSaveButton\" value=\"yes\" />\n"
						+ "<param name=\"UseComputeButton\" value=\"no\" />\n" + "<param name=\"UseZoomButtons\" value=\"yes\" />\n"
						+ "<param name=\"UseEqualizeButton\" value=\"yes\" />\n" + "<param name=\"OptionalStars\" value=\"yes\" />\n"
						+ "<param name=\"Factorials\" value=\"yes\" />\n" + "<param name=\"OptionalFunctions\" value=\"yes\" />\n"
						+ "<param name=\"RationalExponents\" value=\"yes\" />\n" + "<param name=\"UseLoadButton\" value=\"no\" />\n"
						+ "<param name=\"Example1\" value=\"A Jump Discontinuity;x&lt;1?x:x+1;-1,3,-1,3,1,2;open,1,1;closed,1,2\" />\n"
						+ "<param name=\"Example2\" value=\"A Displaced Point;x&lt;&gt;1?x:2;-1,3,-1,3,1,2;open,1,1;closed,1,2\" />\n"
						+ "<param name=\"Example3\" value=\"A Missing Point;x&lt;&gt;1?x;-1,3,-1,3,1,2;open,1,1\" />\n"
						+ "<param name=\"Example4\" value=\"Hyperbola; 1/(x-1);-3,5,-3,5,1,2;asymptote,1,0,1,1\" />\n" + "\n"
						+ "<param name=\"Example5\" value=\"Absolute Value;abs(x-1)+1;-1,3,-1,3,1,2\" />\n"
						+ "<param name=\"Example6\" value=\"A Cusp;x^(2/3);-2,2,-2,2,0,1\" />\n"
						+ "<param name=\"Example7\" value=\"A Vertical Tangent;x^(1/3);-2,2,-2,2,0,1\" />\n"
						+ "<param name=\"ShowTangentSlope\" value=\"no\" />\n" + "<param name=\"ShowTangentLine\" value=\"no\" />\n"
						+ "</applet>", wikiModel.render("<applet\n"
						+ "code=\"net.sourceforge.webcompmath.applets.SecantTangent\" codebase=\"./assets\"\n"
						+ "archive=\"webcompmath.jar\" height=\"400\" width=\"480\"><param\n" + "name=\"UseRestoreButton\" value=\"yes\" />\n"
						+ "<param name=\"UseSaveButton\" value=\"yes\" />\n" + "<param name=\"UseComputeButton\" value=\"no\" />\n"
						+ "<param name=\"UseZoomButtons\" value=\"yes\" />\n" + "<param name=\"UseEqualizeButton\" value=\"yes\" />\n"
						+ "<param name=\"OptionalStars\" value=\"yes\" />\n" + "<param name=\"Factorials\" value=\"yes\" />\n"
						+ "<param name=\"OptionalFunctions\" value=\"yes\" />\n" + "<param name=\"RationalExponents\" value=\"yes\" />\n"
						+ "<param name=\"UseLoadButton\" value=\"no\" />\n"
						+ "<param name=\"Example1\" value=\"A Jump Discontinuity;x&lt;1?x:x+1;-1,3,-1,3,1,2;open,1,1;closed,1,2\" />\n"
						+ "<param name=\"Example2\" value=\"A Displaced Point;x&lt;&gt;1?x:2;-1,3,-1,3,1,2;open,1,1;closed,1,2\" />\n"
						+ "<param name=\"Example3\" value=\"A Missing Point;x&lt;&gt;1?x;-1,3,-1,3,1,2;open,1,1\" />\n"
						+ "<param name=\"Example4\" value=\"Hyperbola; 1/(x-1);-3,5,-3,5,1,2;asymptote,1,0,1,1\" />\n" + "\n"
						+ "<param name=\"Example5\" value=\"Absolute Value;abs(x-1)+1;-1,3,-1,3,1,2\" />\n"
						+ "<param name=\"Example6\" value=\"A Cusp;x^(2/3);-2,2,-2,2,0,1\" />\n"
						+ "<param name=\"Example7\" value=\"A Vertical Tangent;x^(1/3);-2,2,-2,2,0,1\" />\n"
						+ "<param name=\"ShowTangentSlope\" value=\"no\" />\n" + "<param name=\"ShowTangentLine\" value=\"no\" />\n" + "\n"
						+ "</applet>"));
	}

}