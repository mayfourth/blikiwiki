package info.bliki.wiki.impl;

import info.bliki.api.Page;
import info.bliki.api.User;
import info.bliki.wiki.filter.WikipediaParser;
import info.bliki.wiki.filter.AbstractParser.ParsedPageName;
import info.bliki.wiki.model.Configuration;
import info.bliki.wiki.model.ImageFormat;
import info.bliki.wiki.model.WikiModel;
import info.bliki.wiki.namespaces.INamespace.NamespaceCode;
import info.bliki.wiki.tags.WPATag;

import java.util.*;

public class APIWikiModelInMemory extends WikiModel
{
    private final User user;
    private final Map<String, String> contentCache;

    public APIWikiModelInMemory(User user, Locale locale, String imageBaseURL, String linkBaseURL, Map<String, String> contentCache) {
        super(Configuration.DEFAULT_CONFIGURATION, locale, imageBaseURL, linkBaseURL);
        this.user = user;
        this.contentCache = contentCache;
    }

    @Override
    public String getRawWikiContent(ParsedPageName parsedPagename, Map<String, String> templateParameters) {
        final String result = super.getRawWikiContent(parsedPagename, templateParameters);
        if(result != null) {
            return result;
        }

        if (parsedPagename.namespace.isType(NamespaceCode.TEMPLATE_NAMESPACE_KEY)) {
            final String contentKey = parsedPagename.namespace.makeFullPagename(parsedPagename.pagename);
            String content = contentCache.get(contentKey);
            if(content == null) {
                final String[] pageTitles = new String[]{contentKey};
                final List<Page> pages = user.queryContent(pageTitles);
                if(!pages.isEmpty()) {
                    final Page page = pages.get(0);
                    content = page.getCurrentContent();
                    if(content != null) {
                        if(content.length() <= 0) {
                            content = null;
                        }
                    }
                }
                contentCache.put(contentKey, content);
            }
            return content;
        }
        return null;
    }

    @Override
    public void appendInternalLink(String topic, String hashSection, String topicDescription, String cssClass, boolean isParseRecursive) {
        WPATag tagNode = new WPATag();
        tagNode.addAttribute("id", "w", true);
        String href = topic;
        if(hashSection != null) {
            href = href + '#' + hashSection;
        }
        tagNode.addAttribute("href", href, true);
        if(cssClass != null) {
            tagNode.addAttribute("class", cssClass, true);
        }
        tagNode.addObjectAttribute("wikilink", topic);

        pushNode(tagNode);
        WikipediaParser.parseRecursive(topicDescription.trim(), this, false, true);
        popNode();
    }

    @Override
    public void appendInternalImageLink(String hrefImageLink, String srcImageLink, ImageFormat imageFormat) {
        super.appendInternalImageLink(normalizeImageName(hrefImageLink), normalizeImageName(srcImageLink), imageFormat);
    }

    // TODO: add "File" as a language-independent alias for the File namespace
//	@Override
//	public boolean isImageNamespace(String namespace) {
//		return (super.isImageNamespace(namespace) || namespace.equalsIgnoreCase("File"));
//	}
	
    /**
     * TODO Why is the size and ".png" appended to the original image name/link?! This bad method is only required to revert this strange modification...
     * @param imageLink
     * @return
     */
    private static String normalizeImageName(String imageLink) {
        final int sizeSplitPos = imageLink.indexOf('-');
		String modifiedImageLink = imageLink;
        if(sizeSplitPos > -1) {
            final int typeSplitPos = imageLink.indexOf(':');
            if(typeSplitPos > -1) {
                String type = imageLink.substring(0, typeSplitPos + 1);
                modifiedImageLink = type + imageLink.substring(sizeSplitPos + 1);
            } else {
				modifiedImageLink = imageLink.substring(sizeSplitPos + 1);
			}
        }
		if(modifiedImageLink.endsWith(".svg.png")) {
			modifiedImageLink = modifiedImageLink.substring(0, modifiedImageLink.length() - 4);
		}
        return modifiedImageLink;
    }
}