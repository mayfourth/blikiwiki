package info.bliki.wiki;

import info.bliki.api.query.test.QueryTest;
import info.bliki.html.test.AllHTMLTests;
import info.bliki.wiki.template.TemplateFunctionsTest;
import info.bliki.wiki.template.expr.EvalDoubleTestCase;
import info.bliki.wiki.test.code.AllCodeTests;
import info.bliki.wiki.test.filter.AllFilterTests;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class AllBlikiTests extends TestCase {
	public AllBlikiTests(String name) {
		super(name);
	}

	public static Test suite() {
		TestSuite s = new TestSuite();
		s.addTest(AllFilterTests.suite());
		s.addTest(AllCodeTests.suite());
		s.addTestSuite(TemplateFunctionsTest.class);
		s.addTest(AllHTMLTests.suite());
//		s.addTestSuite(WikiStringBuilderTest.class);
		s.addTest(EvalDoubleTestCase.suite());
		s.addTestSuite(QueryTest.class);
		return s;
	}

}
