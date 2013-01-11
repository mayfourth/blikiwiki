package info.bliki.wiki.client.filter;

import info.bliki.wiki.client.util.StringUtil;

import java.util.HashMap;

public class WPCell {
	int fStartPos;

	int fEndPos;

	public final static int DEFAULT = 1;

	public final static int TH = 2;

	public final static int CAPTION = 4;

	int fType;

	public WPCell(int start) {
		fStartPos = start;
		fType = DEFAULT;
	}

	/**
	 * @return Returns the endPos.
	 */
	public int getEndPos() {
		return fEndPos;
	}

	/**
	 * @param endPos
	 *            The endPos to set.
	 */
	public void setEndPos(int endPos) {
		fEndPos = endPos;
	}

	/**
	 * @return Returns the startPos.
	 */
	public int getStartPos() {
		return fStartPos;
	}

	/**
	 * @param startPos
	 *            The startPos to set.
	 */
	public void setStartPos(int startPos) {
		fStartPos = startPos;
	}

	public void render(StringBuffer buf, String src) {
		if (fEndPos > fStartPos) {
			char[] content;
			String params = null;
			WikipediaScanner scan = new WikipediaScanner(src, fStartPos);
			int index = scan.indexOf('|', '\n');
			if (index == (-1) || index >= fEndPos) {
				content = new char[fEndPos - fStartPos];
				StringUtil.arraycopy(src, fStartPos, content, 0, fEndPos
						- fStartPos);
			} else {
				content = new char[fEndPos - index - 1];
				StringUtil.arraycopy(src, index + 1, content, 0, fEndPos
						- index - 1);
				 params = StringUtil.str(src, fStartPos, index - fStartPos);
//				params = src.substring(fStartPos, index - fStartPos);
			}
			if (fType == CAPTION) {
				if (params == null) {
					buf.append("\n<caption>");
				} else {
					buf.append("<caption ");
					buf.append(params);
					buf.append(">");
				}
				buf.append(content);

				buf.append("</caption>");
			} else if (fType == TH) {
				if (params == null) {
					buf.append("<th>");
				} else {
					buf.append("<th ");
					buf.append(params);
					buf.append(">");
				}
				buf.append(content);

				buf.append("</th>");
			} else {
				if (params == null) {
					buf.append("<td>");
				} else {
					buf.append("<td ");
					buf.append(params);
					buf.append(">");
				}
				buf.append(content);

				buf.append("</td>");
			}
		}
	}

	public void filter(StringBuffer buf, String src, 
			HashMap wikiSettings,   int recursionLevel) {
		if (fEndPos > fStartPos) {
			String content;
			String params = null;
			WikipediaScanner scan = new WikipediaScanner(src, fStartPos);
			// int index = scan.indexOf('|','\n');
			int index = scan.indexOfAttributes();
			if (index == (-1) || index >= fEndPos) {
				 content = StringUtil.str(src,fStartPos, fEndPos - fStartPos);
//				content = src.substring(fStartPos, fEndPos - fStartPos);
			} else {
				 content = StringUtil.str(src,index + 1, fEndPos - index-1);
//				content = src.substring(index + 1, fEndPos - index - 1);
				 params = StringUtil.str(src, fStartPos, index - fStartPos);
//				params = src.substring(fStartPos, index - fStartPos);
			}

			content = WikipediaFilter.filterParser(content.trim(), 
					wikiSettings,  null, recursionLevel);

			if (fType == CAPTION) {
				if (params == null) {
					buf.append("\n<caption>");
				} else {
					buf.append("<caption ");
					buf.append(params);
					buf.append(">");
				}
				buf.append(content);

				buf.append("</caption>");
			} else if (fType == TH) {
				if (params == null) {
					buf.append("<th>");
				} else {
					buf.append("<th ");
					buf.append(params);
					buf.append(">");
				}
				buf.append(content);

				buf.append("</th>");
			} else {
				if (params == null) {
					buf.append("<td>");
				} else {
					buf.append("<td ");
					buf.append(params);
					buf.append(">");
				}
				buf.append(content);

				buf.append("</td>");
			}
		}
	}

	/**
	 * @return Returns the type.
	 */
	public int getType() {
		return fType;
	}

	/**
	 * @param type
	 *            The type to set.
	 */
	public void setType(int type) {
		fType = type;
	}
}