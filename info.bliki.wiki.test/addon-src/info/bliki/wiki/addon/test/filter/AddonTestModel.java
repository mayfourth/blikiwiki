package info.bliki.wiki.addon.test.filter;

import info.bliki.htmlcleaner.TagNode;
import info.bliki.wiki.addon.model.AddonConfiguration;
import info.bliki.wiki.filter.Encoder;
import info.bliki.wiki.model.WikiModel;

import java.util.Locale;
import java.util.Map;


/**
 * Wiki model implementation which allows some special JUnit tests with
 * namespaces and predefined templates
 * 
 */
public class AddonTestModel extends WikiModel {
	public final static String REFLIST_TEXT = "<div class=\"references-small\" {{#if: {{{colwidth|}}}| style=\"-moz-column-width:{{{colwidth}}}; -webkit-column-width:{{{colwidth}}}; column-width:{{{colwidth}}};\" | {{#if: {{{1|}}}| style=\"-moz-column-count:{{{1}}}; -webkit-column-count:{{{1}}}; column-count:{{{1}}} }};\" |}}>\n"
			+ "<references /></div><noinclude>{{pp-template|small=yes}}{{template doc}}</noinclude>\n";

	public final static String CITE_WEB_TEXT = "[{{{url}}} {{{title}}}]";

	public final static String IFEQ_TEST = "{{#ifeq: {{{1}}}|{{{2}}} | {{{1}}} equals {{{2}}} | {{{1}}} is not equal {{{2}}}}}";

	boolean fSemanticWebActive;

	static {
		TagNode.addAllowedAttribute("style");
		TagNode.addAllowedAttribute("cht");
		TagNode.addAllowedAttribute("chd");
		TagNode.addAllowedAttribute("chs");
		TagNode.addAllowedAttribute("chl");
		TagNode.addAllowedAttribute("url");
		TagNode.addAllowedAttribute("ci");
		TagNode.addAllowedAttribute("ca");
		TagNode.addAllowedAttribute("xrange");
		TagNode.addAllowedAttribute("yrange");
		TagNode.addAllowedAttribute("function");
		TagNode.addAllowedAttribute("d");
	}
	/**
	 * Add German namespaces to the wiki model
	 * 
	 * @param imageBaseURL
	 * @param linkBaseURL
	 */
	public AddonTestModel(String imageBaseURL, String linkBaseURL) {
		super(AddonConfiguration.DEFAULT_CONFIGURATION, Locale.GERMAN,imageBaseURL, linkBaseURL);
//		// add some german namespaces
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
		if (name.equals("Reflist")) {
			return REFLIST_TEXT;
		} else if (name.equals("Cite_web")) {
			return CITE_WEB_TEXT;
		} else if (name.equals("Test")) {
			return "a) First: {{{1}}} Second: {{{2}}}";
		} else if (name.equals("Templ1")) {
			return "b) First: {{{a}}} Second: {{{2}}}";
		} else if (name.equals("Templ2")) {
			return "c) First: {{{1}}} Second: {{{2}}}";
		} else if (name.equals("Include_Page")) {
			return "an include page";
		} else if (name.equals("Ifeq")) {
			return IFEQ_TEST;
		}
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

}
