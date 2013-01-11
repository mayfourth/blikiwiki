package info.bliki.wiki.addon.test.trac;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class AllTracFilterTests extends TestCase {
	public AllTracFilterTests(String name) {
		super(name);
	}

	public static Test suite() {
		TestSuite s = new TestSuite();

		s.addTestSuite(BasicFilterTest.class);
		s.addTestSuite(HTTPUrlFilterTest.class);
		s.addTestSuite(TracLinkFilterTest.class);
		return s;
	}

}
