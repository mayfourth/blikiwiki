package info.bliki.wiki.client.filter.tags;

import info.bliki.wiki.client.util.StringUtil;

public class FontTag extends OpenTagToken {

  public FontTag(int token, String name, String openTag) {
    super(token, name);
  }

  public boolean generate(int srcPtr, int srcLength) {
    int startPtr = srcPtr;
    switch (srcLength) {
    case 5:
      if ((charAt(srcPtr) == 'c' || charAt(srcPtr) == 'C') && (charAt(++srcPtr) == 'o' || charAt(srcPtr) == 'O')
          && (charAt(++srcPtr) == 'l' || charAt(srcPtr) == 'L') && (charAt(++srcPtr) == 'o' || charAt(srcPtr) == 'O')
          && (charAt(++srcPtr) == 'r' || charAt(srcPtr) == 'R')) {
        if (checkValueText()) {
          fBuffer.append(" color");
          fBuffer.append("=\"");
          StringUtil.append(fBuffer,fSource, valueStartPosition, valueEndPosition - valueStartPosition);
          fBuffer.append('\"');
        }
        return true;
      }
      srcPtr = startPtr;
      if ((charAt(srcPtr) == 's' || charAt(srcPtr) == 'S') && (charAt(++srcPtr) == 't' || charAt(srcPtr) == 'T')
          && (charAt(++srcPtr) == 'y' || charAt(srcPtr) == 'Y') && (charAt(++srcPtr) == 'l' || charAt(srcPtr) == 'L')
          && (charAt(++srcPtr) == 'e' || charAt(srcPtr) == 'E')) {
        if (checkValueText()) {
          fBuffer.append(" style");
          fBuffer.append("=\"");
          StringUtil.append(fBuffer,fSource, valueStartPosition, valueEndPosition - valueStartPosition);
          fBuffer.append('\"');
        }
        return true;
      }
      break;
    }
    return false;
  }
}