package info.bliki.gae.utils;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import org.apache.commons.io.FileUtils;

public class Utilities {
  /**
   * Return the current ClassLoader. First try to get the current thread's
   * ClassLoader, and if that fails return the ClassLoader that loaded this
   * class instance.
   * 
   * @return An instance of the current ClassLoader.
   */
  private static ClassLoader getClassLoader() {
    ClassLoader loader = null;
    try {
      loader = Thread.currentThread().getContextClassLoader();
    } catch (SecurityException e) {
//      logger.fine("Unable to retrieve thread class loader, trying default");
    }
    if (loader == null) {
      loader = Utilities.class.getClassLoader();
    }
    return loader;
  }
  /**
   * Utility method for reading a file from a classpath directory and returning
   * its contents as a String.
   * 
   * @param filename
   *          The name of the file to be read, either as an absolute file path
   *          or relative to the classpath.
   * @return A string representation of the file contents.
   * @throws FileNotFoundException
   *           Thrown if the file cannot be found or if an I/O exception occurs.
   */
  public static String readFile(String filename) throws IOException {
    InputStream is = null;
    InputStreamReader isr = null;
    BufferedReader br = null;
    try {
      // file = findProperties(propertyFile);
      is = BlikiBase.class.getResourceAsStream(filename);

      if (is == null) {
//        logger.warning("File " + filename + " does not exist");
      } else {
//        logger.config("Loading pfile " + filename);
        isr = new InputStreamReader(is);
        br = new BufferedReader(isr);
        String line;
        StringBuffer buf = new StringBuffer();
        while ((line = br.readLine()) != null) {
          buf.append(line);
          buf.append('\n');
        }
        return buf.toString();
      }
    } catch (IOException e) {
//      logger.severe("Failure while trying to load properties file " + filename, e);
    } finally {
      if (is != null) {
        try {
          is.close();
        } catch (IOException e) {
        }
        if (br != null) {
          try {
            br.close();
          } catch (IOException e) {
          }
        }
        if (isr != null) {
          try {
            isr.close();
          } catch (IOException e) {
          }
        }
      }
    }

    File file = new File(filename);
//    if (file.exists()) {
//      // file passed in as full path
//      return FileUtils.readFileToString(file, "UTF-8");
//    }
    // look for file in resource directories
    ClassLoader loader = Utilities.getClassLoader();
    URL url = loader.getResource(filename);
    file = FileUtils.toFile(url);
    if (file == null || !file.exists()) {
      throw new FileNotFoundException("File " + filename + " is not available for reading");
    }
    return FileUtils.readFileToString(file, "UTF-8");
  }
}
