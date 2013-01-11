package de.jtidy.gwt.server;

import info.bliki.html.HTML2WikiConverter;
import info.bliki.html.googlecode.ToGoogleCode;
import info.bliki.html.googlecode.ToMoinMoin;
import info.bliki.html.googlecode.ToTrac;
import info.bliki.html.wikipedia.ToWikipedia;
import info.bliki.wiki.filter.PlainTextConverter;
import info.bliki.wiki.model.Configuration;
import info.bliki.wiki.model.WikiModel;
import info.bliki.wiki.tags.code.ABAPCodeFilter;
import info.bliki.wiki.tags.code.CSharpCodeFilter;
import info.bliki.wiki.tags.code.JavaCodeFilter;
import info.bliki.wiki.tags.code.JavaScriptCodeFilter;
import info.bliki.wiki.tags.code.PHPCodeFilter;
import info.bliki.wiki.tags.code.PythonCodeFilter;
import info.bliki.wiki.tags.code.SourceCodeFormatter;
import info.bliki.wiki.tags.code.XMLCodeFilter;

import java.util.HashMap;
import java.util.Locale;

import javax.servlet.http.HttpServletResponse;

import com.google.apphosting.api.DeadlineExceededException;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.jtidy.gwt.client.html2wiki.ConverterService;

public class ConverterServiceImpl extends RemoteServiceServlet implements ConverterService {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8241742504165409909L;

	final public static int MAX_VALUE = 1024 * 1024;

	public ConverterServiceImpl() {
		super();
	}

	public String convert(String expression, HashMap<String, String> map, int counter) {
		if (expression == null) {
			return "";
		}
		if (expression.length() > MAX_VALUE) {
			return "Input text to big";
		}
		if (expression.trim().length() == 0) {
			return "Input text contains no input";
		}

		boolean no_div = false;
		boolean no_font = false;
		String convertType = "wiki2html";
		if (map != null) {
			String prop = (String) map.get("converter");
			if (prop != null) {
				convertType = prop;
			}
			if (prop != null && prop.equals("true")) {
				no_font = true;
			}
			prop = (String) map.get("no_div");
			if (prop != null && prop.equals("true")) {
				no_div = true;
			}
			prop = (String) map.get("no_font");

			if (prop != null && prop.equals("true")) {
				no_font = true;
			}
		}
		String result = null;

		try {
			if (convertType.equals("wiki")) {
				HTML2WikiConverter conv = new HTML2WikiConverter();
				conv.setInputHTML(expression);
				result = conv.toWiki(new ToWikipedia(no_div, no_font));
			} else if (convertType.equals("wiki2html")) {
				WikiModel wikiModel = new WikiModel(Configuration.DEFAULT_CONFIGURATION, Locale.ENGLISH, "${image}", "${title}");
				wikiModel.setUp();
				try {
					result = wikiModel.render(expression);
				} finally {
					wikiModel.tearDown();
				}
			} else if (convertType.equals("wiki2plain")) {
				WikiModel wikiModel = new WikiModel(Configuration.DEFAULT_CONFIGURATION, Locale.ENGLISH, "${image}", "${title}");
				wikiModel.setUp();
				try {
					result = wikiModel.render(new PlainTextConverter(), expression);
				} finally {
					wikiModel.tearDown();
				}
			} else if (convertType.equals("googlecode")) {
				HTML2WikiConverter conv = new HTML2WikiConverter();
				conv.setInputHTML(expression);
				result = conv.toWiki(new ToGoogleCode(no_div, no_font));
			} else if (convertType.equals("trac")) {
				HTML2WikiConverter conv = new HTML2WikiConverter();
				conv.setInputHTML(expression);
				result = conv.toWiki(new ToTrac(no_div, no_font));
			} else if (convertType.equals("moinmoin")) {
				HTML2WikiConverter conv = new HTML2WikiConverter();
				conv.setInputHTML(expression);
				result = conv.toWiki(new ToMoinMoin(no_div, no_font));
			} else {
				SourceCodeFormatter f = null;
				if (convertType.equals("java")) {
					f = new JavaCodeFilter();
				} else if (convertType.equals("php")) {
					f = new PHPCodeFilter();
				} else if (convertType.equals("csharp")) {
					f = new CSharpCodeFilter();
				} else if (convertType.equals("xml")) {
					f = new XMLCodeFilter();
				} else if (convertType.equals("javascript")) {
					f = new JavaScriptCodeFilter();
				} else if (convertType.equals("abap")) {
					f = new ABAPCodeFilter();
				} else if (convertType.equals("python")) {
					f = new PythonCodeFilter();
				}
				if (f != null) {
					result = generateCode(expression, convertType, f);
				}
			}
			if (result != null) {
				return result;
			}
		} catch (DeadlineExceededException deadline) {
			// got this deadline exception
			return "Deadline exceeded for converting input text";
		} 

		return "Error converting input text";
	}

	private String generateCode(String expression, String convertType, SourceCodeFormatter f) {
		String result;
		String coding1 = "<pre class=\"";
		String coding2 = "\" style=\"border: 1px solid #b4d0dc; background-color: #ecf8ff;\">";
		String coding3 = "</pre>";
		result = f.filter(expression);
		result = coding1 + convertType + coding2 + result + coding3;
		return result;
	}

}
