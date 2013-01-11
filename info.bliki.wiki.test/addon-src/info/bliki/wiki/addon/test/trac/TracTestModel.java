package info.bliki.wiki.addon.test.trac;

import info.bliki.htmlcleaner.TagNode;
import info.bliki.wiki.addon.trac.TracModel;
import info.bliki.wiki.filter.Encoder;
import info.bliki.wiki.model.Configuration;

import java.util.Locale;
import java.util.Map;


/**
 * Trac wiki model implementation which allows some special JUnit tests
 * 
 */
public class TracTestModel extends TracModel {
	boolean fSemanticWebActive;

	
	public TracTestModel(String imageBaseURL, String linkBaseURL) {
		this(Locale.ENGLISH, imageBaseURL, linkBaseURL);
	}

	/**
	 * Add German namespaces to the wiki model
	 * 
	 * @param imageBaseURL
	 * @param linkBaseURL
	 */
	public TracTestModel(Locale locale, String imageBaseURL, String linkBaseURL) {
		super(Configuration.DEFAULT_CONFIGURATION, locale, imageBaseURL, linkBaseURL);
		// add some german namespaces
//		fCategoryNamespaces.add("Kategorie");
//		fTemplateNamespaces.add("Vorlage");
//		fImageNamespaces.add("Bild");
		fSemanticWebActive = false;
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
		return null;
	}

	/**
	 * Set the German image namespace
	 */
	@Override
	public String getImageNamespace() {
		return "Bild";
	}

	@Override
	public boolean isImageNamespace(String name) {
		return super.isImageNamespace(name);
	}

	@Override
	public boolean isSemanticWebActive() {
		return fSemanticWebActive;
	}

	@Override
	public void setSemanticWebActive(boolean semanticWeb) {
		this.fSemanticWebActive = semanticWeb;
	}

	public boolean showSyntax(String tagName) {
		// if (tagName.equals("groovy")) {
		// return false;
		// }
		return true;
	}
}
