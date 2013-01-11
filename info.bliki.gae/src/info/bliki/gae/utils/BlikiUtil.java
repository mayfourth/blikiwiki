package info.bliki.gae.utils;

import info.bliki.gae.db.WikiUserService;

import java.io.IOException;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;
import org.jamwiki.model.WikiUser;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class BlikiUtil {
  // private static UserService userService =
  // UserServiceFactory.getUserService();

  /**
   * Replace all '_' (underscore) characters with a ' ' (space) character
   * 
   * @param title
   *          the title of the wiki page
   * @return
   */
  // public static String decodeTitle(String title) {
  // return title.replaceAll("_", " ");
  // }

  /**
   * Replace all ' ' (space) characters with a '_' (underscore) character
   * 
   * @param title
   *          the title of the wiki page
   * @return
   */
  // public static String encodeTitle(String title) {
  // return title.replaceAll(" ", "_");
  // }

  // public static boolean isTopicEditor() {
  // if (!userService.isUserLoggedIn()) {
  // return false;
  // }
  // if (userService.isUserAdmin()) {
  // return true;
  // }
  // WikiUser wikiUser = WikiUserService.getWikiUser();
  // if (wikiUser != null) {
  // return wikiUser.isTopicEditor();
  // }
  // return false;
  // }

  // public static boolean isSystemAdmin() {
  // if (!userService.isUserLoggedIn()) {
  // return false;
  // }
  // if (userService.isUserAdmin()) {
  // return true;
  // }
  // WikiUser wikiUser = WikiUserService.getWikiUser();
  // if (wikiUser != null) {
  // return wikiUser.isSystemAdmin();
  // }
  // return false;
  // }

  // public static String securityCheck(String destinationUrl) {
  // // logger.debug("destination url: " + destinationUrl);
  // if (!userService.isUserLoggedIn()) {
  // return "redirect:" + userService.createLoginURL("/" + destinationUrl);
  // } else if (!isTopicEditor()) {
  // return "common/403";
  // }
  // return destinationUrl;
  // }

  /**
   * Utility method for reading special topic values from files and returning
   * the file contents.
   * 
   * @param locale
   *          The locale for the user viewing the special page.
   * @param pageName
   *          The name of the special page being retrieved.
   */
  // public static String readSpecialPage(Locale locale, String pageName)
  // throws IOException {
  // String contents = null;
  // String filename = null;
  // String language = null;
  // String country = null;
  // if (locale != null) {
  // language = locale.getLanguage();
  // country = locale.getCountry();
  // }
  // String subdirectory = "";
  // if (!StringUtils.isBlank(language) && !StringUtils.isBlank(country)) {
  // try {
  // subdirectory = "/" + BlikiBase.SPECIAL_PAGE_DIR + "/" + language + "_"
  // + country;
  // filename = subdirectory + "/" + pageName + ".txt";
  // // subdirectory = new File(WikiBase.SPECIAL_PAGE_DIR, language + "_" +
  // // country).getPath();
  // // filename = new File(subdirectory,
  // // WikiUtil.encodeForFilename(pageName) + ".txt").getPath();
  // contents = Utilities.readFile(filename);
  // } catch (IOException e) {
  // // logger.info("File " + filename + " does not exist");
  // }
  // }
  // if (contents == null && !StringUtils.isBlank(language)) {
  // try {
  // subdirectory = "/" + BlikiBase.SPECIAL_PAGE_DIR + "/" + language;
  // filename = subdirectory + "/" + pageName + ".txt";
  // contents = Utilities.readFile(filename);
  // } catch (IOException e) {
  // // logger.info("File " + filename + " does not exist");
  // }
  // }
  // if (contents == null) {
  // try {
  // subdirectory = "/" + BlikiBase.SPECIAL_PAGE_DIR;
  // filename = subdirectory + "/" + pageName + ".txt";
  // contents = Utilities.readFile(filename);
  // } catch (IOException e) {
  // // logger.warning("File " + filename + " could not be read", e);
  // throw e;
  // }
  // }
  // return contents;
  // }

}
