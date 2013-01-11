package info.bliki.wiki.client.filter.tags;

public class CloseTagToken extends AbstractTag {
	private String fTagName;

	private String fCloseTag;

	public CloseTagToken(int token, String name) {
		super(token);
		fTagName = name;
		fCloseTag = "</" + name + ">";
	}

	public CloseTagToken(int token, String name, String closeTag) {
		super(token);
		fTagName = name;
//		String ttag = "</" + name + ">";
//		if (!ttag.equals(closeTag)) {
//			System.out.println("");
//		}
		fCloseTag = closeTag;
	}
	
	public String getTagName() {
		return fTagName;
	}

	public String getCloseTag() {
		return fCloseTag;
	}

	public void setTagName(String name) {
		fTagName = name;
	}

	public void setCloseTag(String closeTag) {
		fCloseTag = closeTag;
	}

}
