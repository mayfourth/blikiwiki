package info.bliki.wiki.client.filter;

import info.bliki.wiki.client.util.StringUtil;

import java.util.HashMap;


public class WPListElement {
  int fStartPos;

  int fEndPos;

  public static final int OL = 2;

  public static final int UL = 1;

  char[] fSequence;

  int fType;

  int fLevel;

  public WPListElement(int type, int level, char[] sequence, int start) {
    fType = type;
    fLevel = level;
    fSequence = sequence;
    fStartPos = start;
  }

  /**
   * @return Returns the endPos.
   */
  public int getEndPos() {
    return fEndPos;
  }

  /**
   * @param endPos
   *          The endPos to set.
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
   *          The startPos to set.
   */
  public void setStartPos(int startPos) {
    fStartPos = startPos;
  }

  public void render(StringBuffer buf, char[] src) {
    if (fEndPos > fStartPos) {
      buf.append("<li>");
      //      buf.append(fSequence);

      buf.append(src, fStartPos, fEndPos - fStartPos);

      buf.append("</li>");
    }
  }

  public void filter(StringBuffer buf, String src, HashMap wikiSettings, int recursionLevel) {
    if (fEndPos > fStartPos) {
      buf.append("<li>");
      buf.append(WikipediaFilter.filterParser(StringUtil.str(src, fStartPos, fEndPos - fStartPos), wikiSettings, null, recursionLevel));
      
      buf.append("\n</li>");
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
   *          The type to set.
   */
  public void setType(int type) {
    fType = type;
  }

  /**
   * @return Returns the level.
   */
  public int getLevel() {
    return fLevel;
  }

  /**
   * @param level
   *          The level to set.
   */
  public void setLevel(int level) {
    fLevel = level;
  }

  /**
   * @return Returns the sequence.
   */
  public char[] getSequence() {
    return fSequence;
  }

  /**
   * @param sequence
   *          The sequence to set.
   */
  public void setSequence(char[] sequence) {
    fSequence = sequence;
  }
}