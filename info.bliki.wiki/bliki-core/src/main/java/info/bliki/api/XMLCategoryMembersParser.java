package info.bliki.api;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 * Reads <code>PageInfo</code> data from an XML file generated by the <a
 * href="http://meta.wikimedia.org/w/api.php">Wikimedia API</a> through the
 * <code>categorymembers</code> query.
 */
public class XMLCategoryMembersParser extends AbstractXMLParser {
	private static final String CM_TAG = "cm";
	private static final String CATEGORYMEMBERS_TAG = "categorymembers";
	private static final String CMCONTINUE_ID = "cmcontinue";

	private PageInfo fPage;

	private List<PageInfo> pagesList;

	private String cmContinue;

	public XMLCategoryMembersParser(String xmlText) throws SAXException {
		super(xmlText);
		this.pagesList = new ArrayList<PageInfo>();
		this.cmContinue = "";
	}

	@Override
	public void endElement(String uri, String name, String qName) {
		try {
			if (CM_TAG.equals(qName)) {// ||
				// CATEGORY_ID.equals(qName))
				// {
				if (fPage != null) {
					pagesList.add(fPage);
				}
				// System.out.println(getString());
			}

			fData = null;
			fAttributes = null;

		} catch (RuntimeException re) {
			re.printStackTrace();
		}
	}

	/**
	 * @return the cmContinue
	 */
	public String getCmContinue() {
		if (cmContinue == null) {
			return "";
		}
		return cmContinue;
	}

	public List<PageInfo> getPagesList() {
		return pagesList;
	}

	@Override
	public void startElement(String namespaceURI, String localName, String qName, Attributes atts) {
		fAttributes = atts;

		if (CM_TAG.equals(qName)) {
			fPage = new PageInfo();
			fPage.setPageid(fAttributes.getValue(AbstractXMLParser.PAGE_ID));
			fPage.setNs(fAttributes.getValue(AbstractXMLParser.NS_ID));
			fPage.setTitle(fAttributes.getValue(AbstractXMLParser.TITLE_ID));
		} else if (CATEGORYMEMBERS_TAG.equals(qName)) {
			cmContinue = fAttributes.getValue(CMCONTINUE_ID);
		}
		fData = null;
	}

}
