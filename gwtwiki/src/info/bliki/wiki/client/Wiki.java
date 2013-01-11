package info.bliki.wiki.client;

import info.bliki.wiki.client.filter.WikipediaFilter;
import info.bliki.wiki.client.util.Location;
import info.bliki.wiki.client.util.WindowUtils;

import java.util.HashMap;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.InvocationException;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * A simple GWT wiki editor with basic wikipedia syntax support
 */
public class Wiki implements EntryPoint {

	public static final HTML WIKI_TITLE = new HTML();

	public static final Label WIKI_EDITOR_TITLE_LABEL = new Label("Title:");

	public static final TextBox WIKI_EDITOR_TITLE = new TextBox();

	public static WikiTextarea WIKI_EDITOR_BODY = null;

	public static final HTML PREVIEW_HTML = new HTML();

	public static final HTML ANSWER_HTML = new HTML();

	private final Button fPreviewButton = new Button("(P)review");

	protected WikipediaFilter fWikiFilter;

	protected HashMap<String, String> fWikiSettings;

	private final WikiServiceAsync fService;

	public Wiki() {
		fService = (WikiServiceAsync) GWT.create(WikiService.class);
		ServiceDefTarget target = (ServiceDefTarget) fService;
		if (GWT.isScript()) {
			String url = GWT.getModuleBaseURL();
			url += "/wiki";
			target.setServiceEntryPoint(url);
		} else {
			target.setServiceEntryPoint("/wiki");
		}
	}

	public void onModuleLoad() {
		Location loc = WindowUtils.getLocation();
		String title = loc.getParameter("title");
		if (title == null || title.length() == 0) {
			title = getMainArticle();
		}
		// setup wiki engine for preview:
		fWikiFilter = new WikipediaFilter();
		fWikiSettings = new HashMap<String, String>();
		fWikiSettings.put("wiki_url", "Wiki.html?title=${title}");
		WIKI_EDITOR_BODY = new WikiTextarea(fWikiSettings);
		fPreviewButton.addClickListener(new ClickListener() {
			public void onClick(Widget sender) {
				String str = WIKI_EDITOR_BODY.getText().trim();
				if (str.length() > 0) {
					String result = fWikiFilter.filter(str, fWikiSettings);
					PREVIEW_HTML.setHTML(result);
					renderCode();
				}
				WIKI_EDITOR_BODY.setFocus(true);
			}
		});

		VerticalPanel panel = WikiLoaderPanel.PANEL;
		panel.setHorizontalAlignment(VerticalPanel.ALIGN_LEFT);
		panel.setVerticalAlignment(VerticalPanel.ALIGN_TOP);
		WikiLoaderPanel.HTML_PANEL.loadHTML(title);
		panel.setWidth("100%");

		WIKI_EDITOR_BODY.setCharacterWidth(80);
		WIKI_EDITOR_BODY.setVisibleLines(10);
		VerticalPanel editPanel = new VerticalPanel();
		editPanel.setHorizontalAlignment(VerticalPanel.ALIGN_LEFT);
		editPanel.setVerticalAlignment(VerticalPanel.ALIGN_TOP);
		// editPanel.setSpacing(8);
		HorizontalPanel p1 = new HorizontalPanel();
		p1.add(WIKI_EDITOR_TITLE_LABEL);
		p1.add(WIKI_EDITOR_TITLE);
		editPanel.add(p1);
		editPanel.add(WIKI_EDITOR_BODY);
		HorizontalPanel p2 = new HorizontalPanel();
		p2.add(fPreviewButton);
		// p2.add(fSendArticle);
		p2.add(ANSWER_HTML);
		editPanel.add(p2);
		editPanel.add(ANSWER_HTML);
		editPanel.add(PREVIEW_HTML);

		RootPanel slot = RootPanel.get("editor");
		if (slot != null) {
			slot.add(editPanel);
		}
	}

	public static native String getMainArticle() /*-{
	 return $wnd.article;
	 }-*/;

	public static void callService(final WikiServiceAsync service, String title, String body) {
		ANSWER_HTML.setHTML("Processing submission...");
		service.send(title, body, 1, new AsyncCallback() {
			public void onSuccess(Object obj) {
				ANSWER_HTML.setHTML("Thanks for your submission");
			}

			public void onFailure(Throwable caught) {
				// Convenient way to find out which exception was
				// thrown.
				try {
					// fLoadingImage.setUrl("images/searching.gif");
					throw caught;
				} catch (InvocationException e) {
					// the call didn't complete cleanly
					ANSWER_HTML.setHTML("InvocationException occured: " + e.getMessage());
				} catch (Throwable e) {
					// last resort -- a very unexpected exception
					ANSWER_HTML.setHTML("Exception occured: " + e.getMessage());
				}

			}
		});
	}

	public static native void renderCode() /*-{
	  $wnd.dp.SyntaxHighlighter.HighlightAll('code');
	 }-*/;
}
