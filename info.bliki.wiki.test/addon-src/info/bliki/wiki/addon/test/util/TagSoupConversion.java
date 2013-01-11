package info.bliki.wiki.addon.test.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.Writer;

import org.ccil.cowan.tagsoup.HTMLSchema;
import org.ccil.cowan.tagsoup.Parser;
import org.ccil.cowan.tagsoup.XMLWriter;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class TagSoupConversion {
	public final static String XHTML_START = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
			+ "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \n"
			+ "   \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">\n" + "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n"
			+ "<head>\n" + "        <title>test</title>\n\n    </head>\n" + "    <body>";

	public final static String XHTML_END = "</body>\n" + "</html>";

	public static void main(String[] args) {
		String snippet = "Hello <b>World <i>italic <BR>next line";
		String xhtmlSnippet = convertSnippet(snippet);
		System.out.println(xhtmlSnippet);
	}

	public static String convertSnippet(String badHTMLSnippet) {
		byte[] bytes;
		try {
			String xhtml = XHTML_START + badHTMLSnippet + XHTML_END;
			bytes = xhtml.getBytes("UTF-8");
			InputStream in = new ByteArrayInputStream(bytes);
			Parser parser = new Parser();
			HTMLSchema schema = new HTMLSchema();
			parser.setProperty(Parser.schemaProperty, schema);
			Writer w = new StringWriter();
			XMLWriter x = new XMLWriter(w);
			x.setOutputProperty(XMLWriter.METHOD, "xml");
			x.setOutputProperty(XMLWriter.OMIT_XML_DECLARATION, "yes");
			x.setPrefix(schema.getURI(), "");

			parser.setFeature(Parser.namespacesFeature, false);
			parser.setFeature(Parser.defaultAttributesFeature, true);
			parser.setContentHandler(x);

			InputSource is = new InputSource(in);
			is.setEncoding("UTF-8");
			parser.parse(is);
			return w.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
		return null;
	}
}
