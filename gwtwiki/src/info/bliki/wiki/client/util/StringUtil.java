package info.bliki.wiki.client.util;

public class StringUtil {
	public static String str(String source, int offset, int count) {
		return source.substring(offset, offset+count);
//		return new String(source.toCharArray(), offset, count);
	}

	public static void append(StringBuffer buffer, String source, int offset, int count) {
		buffer.append(source.substring(offset, offset+count));
//		buffer.append(source, offset, count);
	}

	public static boolean isWhitespace(char ch) {
		if (ch == ' ' || ch == '\t' || ch == '\r' || ch == '\n') {
			return true;
		}
		return false;
	}

	public static boolean isLetter(char ch) {
		if ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z')) {
			return true;
		}
		return false;
	}

	public static boolean isDigit(char ch) {
		if (ch >= '0' && ch <= '9') {
			return true;
		}
		return false;
	}

	public static boolean isLetterOrDigit(char ch) {
		if ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z')
				|| (ch >= '0' && ch <= '9')) {
			return true;
		}
		return false;
	}

	// public static char toUpperCase(char ch) {
	// if (ch >= 'a' && ch <= 'z') {
	// switch (ch) {
	// case 'a':
	// return 'A';
	// case 'b':
	// return 'A';
	// case 'c':
	// return 'A';
	// case 'd':
	// return 'A';
	// case 'e':
	// return 'A';
	// case 'f':
	// return 'A';
	// case 'g':
	// return 'A';
	// case 'h':
	// return 'A';
	// case 'i':
	// return 'A';
	// case 'j':
	// return 'A';
	// case 'k':
	// return 'A';
	// case 'l':
	// return 'A';
	// case 'm':
	// return 'A';
	// case 'n':
	// return 'A';
	// case 'o':
	// return 'A';
	// case 'p':
	// return 'A';
	// case 'q':
	// return 'A';
	// case 'r':
	// return 'A';
	// case 's':
	// return 'A';
	// case 't':
	// return 'A';
	// case 'u':
	// return 'A';
	// case 'v':
	// return 'A';
	// case 'w':
	// return 'A';
	// case 'x':
	// return 'A';
	// case 'y':
	// return 'A';
	// case 'z':
	// return 'z';
	// }
	// }
	// return ch;
	// }

	public static String replace(String source, String target,
			String replacement) {
		StringBuffer result = new StringBuffer();
		int index = source.indexOf(target);
		int lastIndex = 0;
		if (index >= 0) {
			result.append(source.substring(lastIndex, index));
			result.append(replacement);
			lastIndex = index + target.length();
		}
		if (lastIndex < source.length()) {
			result.append(source.substring(lastIndex));
		}
		return result.toString();
	}

	public static String replaceAll(String source, String target,
			String replacement) {
		StringBuffer result = new StringBuffer();
		int index = source.indexOf(target);
		int lastIndex = 0;
		while (index >= 0) {
			result.append(source.substring(lastIndex, index));
			result.append(replacement);
			lastIndex = index + target.length();
			index = source.indexOf(target, lastIndex);
		}
		if (lastIndex < source.length()) {
			result.append(source.substring(lastIndex));
		}
		return result.toString();
	}

	public static void arraycopy(char[] source, int srcPos, char[] sequence,
			int dstPos, int length) {
		int j = dstPos;
		for (int i = srcPos; i < (srcPos + length); i++) {
			sequence[j++] = source[i];
		}
	}

	public static void arraycopy(String source, int srcPos, char[] sequence,
			int dstPos, int length) {
		int j = dstPos;
		for (int i = srcPos; i < (srcPos + length); i++) {
			sequence[j++] = source.charAt(i);
		}
	}

	/**
	 * Escape special html characters (&lt; &gt; &quot; &amp; &#39;)
	 * 
	 */
	public static String escape(String source) {
		StringBuffer result = new StringBuffer();
		escape(source, result);
		return result.toString();
	}

	/**
	 * Copy the text into the resulting buffer and escape special html
	 * characters (&lt; &gt; &quot; &amp; &#39;)
	 * 
	 * @param buffer
	 *            add converted text into the resulting buffer
	 */
	public static void escape(String text, StringBuffer buffer) {
		final int len = text.length();
		int currentIndex = 0;
		int lastIndex = currentIndex;
		while (currentIndex < len) {
			switch (text.charAt(currentIndex++)) {
			case '\r': //  
				if (lastIndex < (currentIndex - 1)) {
					buffer.append(text.substring(lastIndex, currentIndex - 1));
					lastIndex = currentIndex;
				}
				break;
			case '<': // special html escape character
				if (lastIndex < (currentIndex - 1)) {
					buffer.append(text.substring(lastIndex, currentIndex - 1));
					lastIndex = currentIndex;
				}
				buffer.append("&lt;");
				break;
			case '>': // special html escape character
				if (lastIndex < (currentIndex - 1)) {
					buffer.append(text.substring(lastIndex, currentIndex - 1));
					lastIndex = currentIndex;
				} else {
					lastIndex++;
				}
				buffer.append("&gt;");
				break;
			case '&': // special html escape character
				if (lastIndex < (currentIndex - 1)) {
					buffer.append(text.substring(lastIndex, currentIndex - 1));
					lastIndex = currentIndex;
				} else {
					lastIndex++;
				}
				buffer.append("&amp;");
				break;
			case '\'': // special html escape character
				if (lastIndex < (currentIndex - 1)) {
					buffer.append(text.substring(lastIndex, currentIndex - 1));
					lastIndex = currentIndex;
				} else {
					lastIndex++;
				}
				buffer.append("&#39;");
				break;
			case '\"': // special html escape character
				if (lastIndex < (currentIndex - 1)) {
					buffer.append(text.substring(lastIndex, currentIndex - 1));
					lastIndex = currentIndex;
				} else {
					lastIndex++;
				}
				buffer.append("&quot;");
				break;
			}
		}
		if (lastIndex < (currentIndex)) {
			buffer.append(text.substring(lastIndex, currentIndex));
		}
	}
}
