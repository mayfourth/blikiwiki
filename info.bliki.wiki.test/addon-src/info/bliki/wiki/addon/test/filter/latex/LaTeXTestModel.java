package info.bliki.wiki.addon.test.filter.latex;

import info.bliki.wiki.addon.filter.LaTeXConverter;
import info.bliki.wiki.filter.Encoder;
import info.bliki.wiki.model.Configuration;
import info.bliki.wiki.model.WikiModel;

import java.util.Locale;
import java.util.Map;

/**
 * Wiki model implementation which allows some special junit tests
 * 
 */
public class LaTeXTestModel extends WikiModel {
	public LaTeXTestModel(String imageBaseURL, String linkBaseURL) {
		super(Configuration.DEFAULT_CONFIGURATION, Locale.GERMAN, imageBaseURL, linkBaseURL);
	}

	@Override
	public String getRawWikiContent(String namespace, String articleName, Map map) {
		String result = super.getRawWikiContent(namespace, articleName, map);
		if (result != null) {
			return result;
		}
		String name = encodeTitleToUrl(articleName, true);
		if (name.equals("Test")) {
			return "a) First: {{{1}}} Second: {{{2}}}";
		} else if (name.equals("Templ1")) {
			return "b) First: {{{a}}} Second: {{{2}}}";
		} else if (name.equals("Templ2")) {
			return "c) First: {{{1}}} Second: {{{2}}}";
		} else if (name.equals("Include_Page")) {
			return "an include page";
		}
		return null;
	}

	// some german namespaces
	@Override
	public String getImageNamespace() {
		return "Bild";
	}

	@Override
	public boolean isImageNamespace(String name) {
		return super.isImageNamespace(name);
	}

	public String renderLaTeX(String rawWikiText) {
		return render(new LaTeXConverter(), rawWikiText);
	}
}
