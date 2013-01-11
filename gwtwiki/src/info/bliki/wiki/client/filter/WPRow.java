package info.bliki.wiki.client.filter;

import java.util.ArrayList;
import java.util.HashMap;


public class WPRow {
  ArrayList fCells;

  String fParams;

  int fType;

  public WPRow(ArrayList cells) {
    fCells = cells;
    fType = WPCell.DEFAULT;
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
    return fCells.add(o);
  }

  /**
   * @param index
   * @return
   */
  public Object get(int index) {
    return fCells.get(index);
  }

  /**
   * @return
   */
  public int size() {
    return fCells.size();
  }

  public void render(StringBuffer buf, String src) {
    if (fCells.size() > 0) {
      if (fType == WPCell.CAPTION) {
        if (fCells.size()==1) {
          ((WPCell) fCells.get(0)).render(buf, src);
        }
      } else {
        if (fParams == null) {
          buf.append("\n<tr>\n");
        } else {
          String trimmed = fParams.trim();
          if (trimmed.length() == 0) {
            buf.append("\n<tr>\n");
          } else {
            buf.append("\n<tr ");
            buf.append(fParams);
            buf.append(">\n");
          }
        }
        WPCell cell;
        for (int i = 0; i < fCells.size(); i++) {
          cell = (WPCell) fCells.get(i);
          cell.render(buf, src);
        }
        buf.append("</tr>");
      }
    }
  }
  
  public void filter(StringBuffer buf, String src, HashMap wikiSettings, int recursionLevel) {
    if (fCells.size() > 0) {
      if (fType == WPCell.CAPTION) {
        if (fCells.size()==1) {
          ((WPCell) fCells.get(0)).filter(buf, src, wikiSettings, recursionLevel);
        }
      } else {
        if (fParams == null) {
          buf.append("\n<tr>\n");
        } else {
          String trimmed = fParams.trim();
          if (trimmed.length() == 0) {
            buf.append("\n<tr>\n");
          } else {
            buf.append("\n<tr ");
            buf.append(fParams);
            buf.append(">\n");
          }
        }
        WPCell cell; 
        for (int i = 0; i < fCells.size(); i++) {
          cell = (WPCell) fCells.get(i);
          cell.filter(buf, src,  wikiSettings, recursionLevel);
        }
        buf.append("</tr>");
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
   *          The type to set.
   */
  public void setType(int type) {
    fType = type;
  }
}