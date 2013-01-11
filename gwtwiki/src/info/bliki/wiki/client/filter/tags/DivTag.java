package info.bliki.wiki.client.filter.tags;

import info.bliki.wiki.client.util.StringUtil;

public class DivTag extends OpenTagToken {

  public DivTag(int token, String name, String openTag) {
    super(token, name);
  }

  public boolean generate(int srcPtr, int srcLength) {
    int startPtr = srcPtr;
    switch (srcLength) {
    case 5:
      if ((charAt(srcPtr) == 'a' || charAt(srcPtr) == 'A') && (charAt(++srcPtr) == 'l' || charAt(srcPtr) == 'L')
          && (charAt(++srcPtr) == 'i' || charAt(srcPtr) == 'I') && (charAt(++srcPtr) == 'g' || charAt(srcPtr) == 'G')
          && (charAt(++srcPtr) == 'n' || charAt(srcPtr) == 'N')) {
        if (checkValueText()) {
          fBuffer.append(" align");
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