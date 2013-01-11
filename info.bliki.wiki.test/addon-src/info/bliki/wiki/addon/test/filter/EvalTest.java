package info.bliki.wiki.addon.test.filter;

import junit.framework.Test;
import junit.framework.TestSuite;

public class EvalTest extends AddonTestSupport {
	public EvalTest(String name) {
		super(name);
	}

	public static Test suite() {
		return new TestSuite(EvalTest.class);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}

	public void testEval01() {
		assertEquals(
				"\n" + 
				"<p>test <a href=\"../eval.jsp?ci=x%3AArg+1%3Ai%3A10%7Cy%3AArg+2&amp;ca=Factorial%3AFactorial%5Bx%5D%7CFibonacci%3AFibonacci%5Bx%5D%7CBinomial%3ABinomial%5Bx%2Cy%5D\" >Combinatoric <b>Panel</b></a> tag.</p>",
				wikiModel.render("test <eval ci=\"x:Arg 1:i:10|y:Arg 2\" ca=\"Factorial:Factorial[x]|Fibonacci:Fibonacci[x]|Binomial:Binomial[x,y]\">Combinatoric '''Panel'''</eval> tag."));
	}
}
