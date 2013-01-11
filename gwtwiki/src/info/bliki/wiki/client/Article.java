package info.bliki.wiki.client;

import info.bliki.wiki.client.filter.WikipediaFilter;
import info.bliki.wiki.client.util.StringUtil;

import java.util.HashMap;

public class Article {
	protected WikipediaFilter fWikiFilter;

	protected HashMap fWikiSettings;

	private String fTitle;

	private String fUnescapedTitle;

	private String fWikiText;

	private String fHtmlText;

	public Article(String unescapedTitle, String wikiText) {
		fUnescapedTitle = unescapedTitle;
		fTitle = normalizeTitle(fUnescapedTitle);
		if (wikiText == null || wikiText.length() == 0) {
			fWikiText = " ";
		} else {
			fWikiText = wikiText;
		}
		// setup wiki engine for rendering:
		fWikiFilter = new WikipediaFilter();
		fWikiSettings = new HashMap();
		fWikiSettings.put("wiki_url", "Wiki.html?title=${title}");
		// fWikiSettings.put("image_url",
		// "http://www.mywiki.com/wiki/${image}");
		// fWikiSettings.put("bb_codes", "true");
		try {
			fHtmlText = fWikiFilter.filter(fWikiText, fWikiSettings);
		} catch (Exception e) {
			fHtmlText = "<center><font color=\"red\">Exception occured: " + e.getMessage() + "</font></center>";
		}
	}

	public static String normalizeTitle(String unescapedTitle) {
		String str = StringUtil.replaceAll(unescapedTitle, "_", " ");
		return str;
	}

	public String getHtmlText() {
		return fHtmlText;
	}

	public String getTitle() {
		return fTitle;
	}

	public String getWikiText() {
		return fWikiText;
	}
}
