package info.bliki.wiki.addon.test.filter.latex;

import junit.framework.TestCase;

/**
 * Support class for defining JUnit FilterTests.
 * 
 */

public class LaTeXTestSupport extends TestCase
{
	protected LaTeXTestModel wikiModel = null;

	public LaTeXTestSupport(String s)
	{
		super(s);
	}

	@Override
	protected void setUp() throws Exception
	{
		super.setUp();
		wikiModel = new LaTeXTestModel("${image}", "${title}");
	}

}
