package info.bliki.wiki.client.filter.tags;

public class ListToken  extends AbstractTag {
	int fLevel;

	public ListToken(int token, int level) {
		super(token);
		fLevel = level;
	}

	public int getLevel() {
		return fLevel;
	}
}