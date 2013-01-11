package info.bliki.wiki.client.filter;


public class FilterUtil {
  public static String getImageRoot() {
    return "theme/images";
  }

  public static String getSpaceRoot() {
    return "space";
  }

  public static String getExecRoot() {
    return "exec";
  }

  public static String getCommentsRoot() {
    return "comments";
  }

//  private static List extensions = Arrays.asList(new String[] { "png", "jpg", "jpeg", "gif" });

  public static String normalizeServerImageName(String wikiLink) {
    wikiLink = wikiLink.replaceAll(":", "/");
    return wikiLink.replaceAll(" ", "_");
  }

  public static String createServerImage(String location, String name, String extension, boolean createHTMLLevel, int level) {
    StringBuffer filename = new StringBuffer();
    if (location != null) {
      filename.append(location);
      filename.append("/");
    }
    String normalized = normalizeServerImageName(name);

    if (location == null && createHTMLLevel) {
      for (int i = 0; i < level; i++) {
        filename.append("../");
      }
    }

    filename.append(normalized);
    if (!normalized.startsWith("Image/") && extension != null) {
      filename.append(".");
      filename.append(extension);
    }
    return filename.toString();
  }

  public static String createServerImage(String location, String name, String extension) {
    return createServerImage(location, name, extension, false, 0);
  }

  public static String createHTMLLink(String location, String name, String extension, boolean createHTMLLevel, int level) {
    StringBuffer filename = new StringBuffer();
    if (location != null) {
        filename.append(location);
        filename.append("/");
    }
//    System.out.println(filename);
    String normalized = FilterUtil.normalizeWikiLink(name);

    if (location == null && createHTMLLevel) {
      for (int i = 0; i < level; i++) {
        filename.append("../");
      }
    }

    filename.append(normalized);
    if (!normalized.startsWith("Image/")) {
      filename.append(".");
      filename.append(extension); 
    }
    return filename.toString();
  }

  public static String createHTMLLink(String location, String name, String extension) {
    return createHTMLLink(location, name, extension, false, 0);
  }

  public static String normalizeWikiLink(String wikiLink) {
    if (wikiLink.length() > 0) {
      int index = wikiLink.lastIndexOf('/');
      if (index >= 0) {
        if (wikiLink.length() == index + 1) {
          wikiLink = wikiLink.substring(0, index + 1) + wikiLink.substring(index + 1, 1).toUpperCase();
        } else if (wikiLink.length() > index + 1) {
          wikiLink = wikiLink.substring(0, index + 1) + wikiLink.substring(index + 1, index + 2).toUpperCase()
              + wikiLink.substring(index + 2);
        }
      } else {
        if (wikiLink.length() > 1) {
          wikiLink = wikiLink.substring(0, 1).toUpperCase() + wikiLink.substring(1);
        } else {
          wikiLink = wikiLink.substring(0, 1).toUpperCase();
        }
      }
    }
    wikiLink = wikiLink.replaceAll(":", "/");
    return wikiLink.replaceAll(" ", "_");
  }
}