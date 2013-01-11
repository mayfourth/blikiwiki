package info.bliki.wiki.client;

import info.bliki.wiki.client.util.Location;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.HTTPRequest;
import com.google.gwt.user.client.ResponseTextHandler;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class WikiLoaderPanel extends HTMLPanel implements ClickListener {
	public final static VerticalPanel PANEL = new VerticalPanel();

	public static Article WIKI_ARTICLE = new Article("", "");

	public static WikiLoaderPanel HTML_PANEL = new WikiLoaderPanel(WIKI_ARTICLE);

	public WikiLoaderPanel(Article article) {
		super(article.getHtmlText());
		WIKI_ARTICLE = article;
	}

	private void addWikiLinks(Element elem) {
		String elemId = DOM.getAttribute(elem, "id");
		if ((elemId != null) && elemId.startsWith("w")) {
			// TODO add history token
			Element parent = DOM.getParent(elem);
			String text = DOM.getInnerText(elem);
			String name = DOM.getAttribute(elem, "href");
			if (name == null || name.length() == 0) {
				name = text;
			}
			WikiLink link = new WikiLink(text, name);
			link.addClickListener(this);
			RootPanel.get().add(link);
			DOM.insertChild(parent, link.getElement(), DOM.getChildIndex(
					parent, elem));
			DOM.removeChild(parent, elem);
			return;
		}
		Element child = DOM.getFirstChild(elem);
		Element child2;
		while (child != null) {
			child2 = child;
			child = DOM.getNextSibling(child);
			addWikiLinks(child2);
		}
	}

	public void createLinks() {
		addWikiLinks(getElement());
	}

	public void loadHTML(final String fileName) {
		HTTPRequest.asyncGet(fileName + ".wp", new ResponseTextHandler() {
			public void onCompletion(String responseText) {
				PANEL.clear();
				Article article = new Article(fileName, responseText);
				HTML_PANEL = new WikiLoaderPanel(article);
				HTML_PANEL.createLinks();
				PANEL.add(HTML_PANEL);
				Wiki.WIKI_TITLE.setText(article.getTitle());
				// copy current text to the editor tab:
				Wiki.WIKI_EDITOR_BODY.setText(article.getWikiText());
				Wiki.WIKI_EDITOR_TITLE.setText(article.getTitle());
			}
		});
	}

	public void onClick(Widget sender) {
		if (sender instanceof WikiLink) {
			// PANEL.clear();
			String queryString = ((WikiLink) sender).getTargetHistoryToken();
			String title = Location.getParameter(queryString, "title");
			if (title == null) {
				title = "No title defined.";
			}
			// int index = fileName.indexOf('#');
			// if (index >= 0) {
			// fileName = fileName.substring(index + 1);
			// }
			loadHTML(title);
		}
	}

}
