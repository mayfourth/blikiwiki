package info.bliki.wiki.addon.test.filter;

import junit.framework.Test;
import junit.framework.TestSuite;

public class SampleTest extends AddonTestSupport {
	public SampleTest(String name) {
		super(name);
	}

	public static Test suite() {
		return new TestSuite(SampleTest.class);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}
 
	public void testSampe01() {
		assertEquals(
				"\n" + 
				"<p>test <div id=\"sample\">\n" + 
				"<a href=\"#\" id=\"show\" onclick=\"$(\'evalframe\').show();$(\'hide\').show();$(\'show\').hide();\" />Show Sample</a> \n" + 
				"<a href=\"#\" style=\"display: none;\" id=\"hide\" onclick=\"$(\'evalframe\').hide();;$(\'hide\').hide();$(\'show\').show();\" />Hide Sample</a><br />\n" + 
				"<iframe src=\"../eval.jsp?ci=x%3AArg+1%3Ai%3A10%7Cy%3AArg+2&amp;ca=Factorial%3AFactorial%5Bx%5D%7CFibonacci%3AFibonacci%5Bx%5D%7CBinomial%3ABinomial%5Bx%2Cy%5D\" style=\"display: none;\" id=\"evalframe\" width=\"480\" height=\"160\" \n" + 
				"        scrolling=\"yes\" marginheight=\"0\" marginwidth=\"0\" frameborder=\"1\">\n" + 
				"  <p>You browser doesn\'t support IFRAMEs</p>\n" + 
				"</iframe>\n" + 
				"</div> tag.</p>",
				wikiModel.render("test <sample ci=\"x:Arg 1:i:10|y:Arg 2\" ca=\"Factorial:Factorial[x]|Fibonacci:Fibonacci[x]|Binomial:Binomial[x,y]\" /> tag."));
	}
}
