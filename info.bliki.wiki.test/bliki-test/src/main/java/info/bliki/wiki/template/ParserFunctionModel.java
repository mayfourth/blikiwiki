package info.bliki.wiki.template;

import info.bliki.wiki.model.Configuration;
import info.bliki.wiki.model.WikiModel;
import info.bliki.wiki.template.extension.AttributeRenderer;
import info.bliki.wiki.template.extension.DollarContext;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

/**
 * Wiki model implementation which allows some special JUnit tests for template
 * parser functions
 * 
 */
public class ParserFunctionModel extends WikiModel {
	final static String CONCAT = "{{{1|}}}{{{2|}}}{{{3|}}}{{{4|}}}{{{5|}}}{{{6|}}}{{{7|}}}{{{8|}}}{{{9|}}}{{{10|}}}";

	public class DateRenderer implements AttributeRenderer {
		public String toString(Object o) {
			SimpleDateFormat f = new SimpleDateFormat ("yyyy.MM.dd");
			return f.format(((Calendar)o).getTime());
		}
		public String toString(Object o, String formatString) {
			return toString(o);
		}
	}
	
	public class UppercaseRenderer implements AttributeRenderer {
		public String toString(Object o) {
			return (String)o;
		}
		public String toString(Object o, String formatString) {
			if ( formatString.equals("upper") ) {
				return ((String)o).toUpperCase();
			}
			return toString(o);
		}
	}
	/**
	 * Add German namespaces to the wiki model
	 * 
	 * @param imageBaseURL
	 * @param linkBaseURL
	 */
	public ParserFunctionModel(String imageBaseURL, String linkBaseURL) {
		super(imageBaseURL, linkBaseURL);
		Configuration.DEFAULT_CONFIGURATION.addTemplateFunction("#$", DollarContext.CONST);
	}

	/**
	 * Add templates: &quot;Test&quot;, &quot;Templ1&quot;, &quot;Templ2&quot;,
	 * &quot;Include Page&quot;
	 * 
	 */
	@Override
	public String getRawWikiContent(String namespace, String articleName, Map<String, String> map) {
		String result = super.getRawWikiContent(namespace, articleName, map);
		if (result != null) {
			return result;
		}
		String name = encodeTitleToUrl(articleName, true);
		if (name.equals("Concat")) {
			return CONCAT;
		}
		return null;
	}

}
