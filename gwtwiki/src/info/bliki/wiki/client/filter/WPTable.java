package info.bliki.wiki.client.filter;

import java.util.ArrayList;
import java.util.HashMap;


public class WPTable {
  String fParams;

  ArrayList fRows;

  public WPTable(ArrayList rows) {
    fRows = rows;
    fParams = null;
  }

  /**
   * @return Returns the params.
   */
  public String getParams() {
    return fParams;
  }

  /**
   * @param params
   *          The params to set.
   */
  public void setParams(String params) {
    this.fParams = params;
  }

  /**
   * @param o
   * @return
   */
  public boolean add(Object o) {
    return fRows.add(o);
  }

  /**
   * @param index
   * @return
   */
  public Object get(int index) {
    return fRows.get(index);
  }

  /**
   * @return
   */
  public int size() {
    return fRows.size();
  }

  public void render(StringBuffer buf, String src) {
    if (fRows.size() > 0) {
      if (fParams == null) {
        buf.append("\n<table>");
      } else {
        String trimmed = fParams.trim();
        if (trimmed.length() == 0) {
          buf.append("\n<table>");
        } else {
          buf.append("\n<table ");
          buf.append(fParams);
          buf.append(">");
        }
      }
      WPRow row;
      for (int i = 0; i < fRows.size(); i++) {
        row = (WPRow) fRows.get(i);
        row.render(buf, src);
      }
      buf.append("</table>");
    }
  }
  
  public void filter(StringBuffer buf, String src, HashMap wikiSettings, int recursionLevel) {
    if (fRows.size() > 0) {
      if (fParams == null) {
        buf.append("\n<table>");
      } else {
        String trimmed = fParams.trim();
        if (trimmed.length() == 0) {
          buf.append("\n<table>");
        } else {
          buf.append("\n<table ");
          buf.append(fParams);
          buf.append(">");
        }
      }
      WPRow row;
      for (int i = 0; i < fRows.size(); i++) {
        row = (WPRow) fRows.get(i);
        row.filter(buf, src, wikiSettings, recursionLevel);
      }
      buf.append("</table>");
    }
  }
}