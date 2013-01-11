package info.bliki.wiki.client.filter.tags;

public class OpenTagToken extends AbstractTag {

	private String fTagName;

	private String fOpenTag;

	public OpenTagToken(int token, String name) {
		super(token);
		fTagName = name;
		fOpenTag = "<" + name + ">";
	}

	public OpenTagToken(int token, String name, String openTag) {
		super(token);
		fTagName = name;
//		String ttag = "<" + name + ">";
//		if (!ttag.equals(openTag)) {
//			System.out.println(openTag);
//		}
		fOpenTag = openTag;
	}

	public String getTagName() {
		return fTagName;
	}

	public String getOpenTag() {
		return fOpenTag;
	}

	public void setTagName(String name) {
		fTagName = name;
	}

	public void setOpenTag(String openTag) {
		fOpenTag = openTag;
	}
}