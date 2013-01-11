package info.bliki.wiki.client.filter.tags;

import info.bliki.wiki.client.util.StringUtil;

public class TableTag extends OpenTagToken {

	public TableTag(int token, String name, String openTag) {
		super(token, name);
	}

	public boolean generate(int srcPtr, int srcLength) {
		int startPtr = srcPtr;
		switch (srcLength) {
		case 5:
			if ((charAt(srcPtr) == 'a' || charAt(srcPtr) == 'A')
					&& (charAt(++srcPtr) == 'l' || charAt(srcPtr) == 'L')
					&& (charAt(++srcPtr) == 'i' || charAt(srcPtr) == 'I')
					&& (charAt(++srcPtr) == 'g' || charAt(srcPtr) == 'G')
					&& (charAt(++srcPtr) == 'n' || charAt(srcPtr) == 'N')) {
				if (checkValueText()) {
					fBuffer.append(" align");
					fBuffer.append("=\"");
					StringUtil.append(fBuffer,fSource, valueStartPosition,
					 valueEndPosition - valueStartPosition);
					fBuffer.append('\"');
				}
				return true;
			}
			srcPtr = startPtr;
			if ((charAt(srcPtr) == 's' || charAt(srcPtr) == 'S')
					&& (charAt(++srcPtr) == 't' || charAt(srcPtr) == 'T')
					&& (charAt(++srcPtr) == 'y' || charAt(srcPtr) == 'Y')
					&& (charAt(++srcPtr) == 'l' || charAt(srcPtr) == 'L')
					&& (charAt(++srcPtr) == 'e' || charAt(srcPtr) == 'E')) {
				if (checkValueText()) {
					fBuffer.append(" style");
					fBuffer.append("=\"");
					StringUtil.append(fBuffer,fSource, valueStartPosition,
					 valueEndPosition - valueStartPosition);
					fBuffer.append('\"');
				}
				return true;
			}
			srcPtr = startPtr;
			if ((charAt(srcPtr) == 'w' || charAt(srcPtr) == 'W')
					&& (charAt(++srcPtr) == 'i' || charAt(srcPtr) == 'I')
					&& (charAt(++srcPtr) == 'd' || charAt(srcPtr) == 'D')
					&& (charAt(++srcPtr) == 't' || charAt(srcPtr) == 'T')
					&& (charAt(++srcPtr) == 'h' || charAt(srcPtr) == 'H')) {
				if (checkValueText()) {
					fBuffer.append(" width");
					fBuffer.append("=\"");
					StringUtil.append(fBuffer,fSource, valueStartPosition,
					 valueEndPosition - valueStartPosition);
					fBuffer.append('\"');
				}
				return true;
			}
			break;
		case 6:
			if ((charAt(srcPtr) == 'b' || charAt(srcPtr) == 'B')
					&& (charAt(++srcPtr) == 'o' || charAt(srcPtr) == 'O')
					&& (charAt(++srcPtr) == 'r' || charAt(srcPtr) == 'R')
					&& (charAt(++srcPtr) == 'd' || charAt(srcPtr) == 'D')
					&& (charAt(++srcPtr) == 'e' || charAt(srcPtr) == 'E')
					&& (charAt(++srcPtr) == 'r' || charAt(srcPtr) == 'R')) {
				if (checkValueText()) {
					fBuffer.append(" border");
					fBuffer.append("=\"");
					StringUtil.append(fBuffer,fSource, valueStartPosition,
					 valueEndPosition - valueStartPosition);
					fBuffer.append('\"');
				}
				return true;
			}
			break;
		case 11:
			if ((charAt(srcPtr) == 'c' || charAt(srcPtr) == 'C')
					&& (charAt(++srcPtr) == 'e' || charAt(srcPtr) == 'E')
					&& (charAt(++srcPtr) == 'l' || charAt(srcPtr) == 'L')
					&& (charAt(++srcPtr) == 'l' || charAt(srcPtr) == 'L')
					&& (charAt(++srcPtr) == 'p' || charAt(srcPtr) == 'P')
					&& (charAt(++srcPtr) == 'a' || charAt(srcPtr) == 'A')
					&& (charAt(++srcPtr) == 'd' || charAt(srcPtr) == 'D')
					&& (charAt(++srcPtr) == 'd' || charAt(srcPtr) == 'D')
					&& (charAt(++srcPtr) == 'i' || charAt(srcPtr) == 'I')
					&& (charAt(++srcPtr) == 'n' || charAt(srcPtr) == 'N')
					&& (charAt(++srcPtr) == 'g' || charAt(srcPtr) == 'G')) {
				if (checkValueText()) {
					fBuffer.append(" cellpadding");
					fBuffer.append("=\"");
					StringUtil.append(fBuffer,fSource, valueStartPosition,
					 valueEndPosition - valueStartPosition);
					fBuffer.append('\"');
				}
				return true;
			}
			srcPtr = startPtr;
			if ((charAt(srcPtr) == 'c' || charAt(srcPtr) == 'C')
					&& (charAt(++srcPtr) == 'e' || charAt(srcPtr) == 'E')
					&& (charAt(++srcPtr) == 'l' || charAt(srcPtr) == 'L')
					&& (charAt(++srcPtr) == 'l' || charAt(srcPtr) == 'L')
					&& (charAt(++srcPtr) == 's' || charAt(srcPtr) == 'S')
					&& (charAt(++srcPtr) == 'p' || charAt(srcPtr) == 'P')
					&& (charAt(++srcPtr) == 'a' || charAt(srcPtr) == 'A')
					&& (charAt(++srcPtr) == 'c' || charAt(srcPtr) == 'C')
					&& (charAt(++srcPtr) == 'i' || charAt(srcPtr) == 'I')
					&& (charAt(++srcPtr) == 'n' || charAt(srcPtr) == 'N')
					&& (charAt(++srcPtr) == 'g' || charAt(srcPtr) == 'G')) {
				if (checkValueText()) {
					fBuffer.append(" cellspacing");
					fBuffer.append("=\"");
					StringUtil.append(fBuffer,fSource, valueStartPosition,
					 valueEndPosition - valueStartPosition);
					fBuffer.append('\"');
				}
				return true;
			}
			break;
		}
		return false;
	}
}