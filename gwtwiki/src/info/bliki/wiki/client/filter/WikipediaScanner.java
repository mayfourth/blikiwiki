package info.bliki.wiki.client.filter;

import info.bliki.wiki.client.filter.tags.TagParsingException;
import info.bliki.wiki.client.util.StringUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class WikipediaScanner {
	public final static String TAG_NAME = "$TAG_NAME";

	final protected String fSource;

	private int fScannerPosition;

	public WikipediaScanner(String src) {
		this(src, 0);
	}

	public WikipediaScanner(String src, int position) {
		fSource = src;
		fScannerPosition = position;
	}

	public final char charAt(int index) {
		if (index >= fSource.length()) {
			throw new IndexOutOfBoundsException("Required position: " + index
					+ " is greater than WikipediaScanner#fSource length: " + fSource.length());
		}
		return fSource.charAt(index);
	}

	public int getPosition() {
		return fScannerPosition;
	}

	public void setPosition(int newPos) {
		fScannerPosition = newPos;
	}

	public WPTable htmlTable() {
		return null;
	}

	public WPTable wpTable() {
		WPTable table = null;
		try {
			if (fScannerPosition < 0) {
				// simulate newline
				fScannerPosition = 0;
			}
			if (charAt(fScannerPosition++) != '{') {
				return null;
			}
			if (charAt(fScannerPosition++) != '|') {
				return null;
			}
			ArrayList rows = new ArrayList();
			table = new WPTable(rows);
			int startPos = fScannerPosition;
			// read params until end of line
			nextNewline();
			table.setParams(StringUtil.str(fSource, startPos, fScannerPosition
					- startPos));
			// table.setParams(fSource.substring(startPos, fScannerPosition
			// - startPos));
			ArrayList cells = new ArrayList();
			WPRow row = new WPRow(cells);
			WPCell cell = null;

			char ch = ' ';

			while (true) {
				ch = charAt(fScannerPosition++);
				switch (ch) {
				case '\n':
					ch = charAt(fScannerPosition++);
					switch (ch) {
					case '|': // "\n|"
						if (cell != null) {
							cell.setEndPos(fScannerPosition - 2);
							cell = null;
						}

						ch = charAt(fScannerPosition++);
						switch (ch) {
						case '-': // new row - "\n|-"
							table.add(row);
							cells = new ArrayList();
							row = new WPRow(cells);
							startPos = fScannerPosition;
							nextNewline();
							row.setParams(StringUtil.str(fSource, startPos,
									fScannerPosition - startPos));
							// row.setParams(fSource.substring(startPos,
							// fScannerPosition - startPos));
							break;
						case '+': // new row - "\n|+"
							table.add(row);
							cells = new ArrayList();
							row = new WPRow(cells);
							row.setType(WPCell.CAPTION);
							cell = new WPCell(fScannerPosition);
							cell.setType(WPCell.CAPTION);
							cells.add(cell);
							nextNewline();
							cell.setEndPos(fScannerPosition);
							cell = null;

							table.add(row);
							cells = new ArrayList();
							row = new WPRow(cells);
							break;
						case '}': // end of table - "\n|}"
							table.add(row);
							return table;
						default:
							fScannerPosition--;
							cell = new WPCell(fScannerPosition);
							cells.add(cell);
						}

						break;
					case '!': // "\n!"
						if (cell != null) {
							cell.setEndPos(fScannerPosition - 2);
							cell = null;
						}
						ch = charAt(fScannerPosition++);
						cell = new WPCell(fScannerPosition - 1);
						cell.setType(WPCell.TH);
						cells.add(cell);

						break;
					case '{': // "\n{"
						if (charAt(fScannerPosition) == '|') {
							// nested table
							fScannerPosition = indexEndOfTable();
							break;
						}
						break;
					default:
						fScannerPosition--;
					}
					break;
				case '|':
					ch = charAt(fScannerPosition++);
					if (ch == '|') {
						if (cell != null) {
							cell.setEndPos(fScannerPosition - 2);
							cell = null;
						}
						cell = new WPCell(fScannerPosition);
						cells.add(cell);
					} else {
						fScannerPosition--;
					}
					break;
				case '!':
					ch = charAt(fScannerPosition++);
					if (ch == '!') {
						if (cell != null) {
							cell.setEndPos(fScannerPosition - 2);
							cell = null;
						}
						cell = new WPCell(fScannerPosition);
						cell.setType(WPCell.TH);
						cells.add(cell);
					} else {
						fScannerPosition--;
					}
					break;
				// case '!':
				// ch = source[pos++];
				// switch (ch) {
				// case '|': // new cell
				// cells.add(new Cell(cellString));
				// }
				// break;
				}
			}
		} catch (IndexOutOfBoundsException e) {
			// ..
		}
		if (table != null) {
			return table;
		}
		return null;
	}

	public WPList wpList() {
		WPList list = null;
		WPListElement listElement = null;
		try {
			char ch;
			if (fScannerPosition < 0) {
				// simulate newline
				fScannerPosition = 0;
				ch = '\n';
			} else {
				ch = charAt(fScannerPosition++);
			}

			int startPos;
			char[] sequence;
			list = new WPList();
			int count;
			int type;
			while (true) {
				if (ch == '\n' || fScannerPosition == 0) {
					if (listElement != null) {
						listElement.setEndPos(fScannerPosition - 1);
						list.add(listElement);
						listElement = null;
					}
					ch = charAt(fScannerPosition++);
					switch (ch) {
					case '*':
						count = 1;
						while (charAt(fScannerPosition) == '*'
								|| charAt(fScannerPosition) == '#') {
							count++;
							fScannerPosition++;
						}
						sequence = new char[count];
						StringUtil.arraycopy(fSource, fScannerPosition - count,
								sequence, 0, count);
						// last character determines type of list
						if (charAt(fScannerPosition - 1) == '#') {
							type = WPListElement.OL;
						} else {
							type = WPListElement.UL;
						}

						while (true) {
							ch = charAt(fScannerPosition++);
							if (!StringUtil.isWhitespace(ch)) {
								startPos = fScannerPosition - 1;
								listElement = new WPListElement(type, count,
										sequence, startPos);
								break;
							}
							if (ch == '\n') {
								fScannerPosition--; // to detect next row
								startPos = fScannerPosition;
								listElement = new WPListElement(type, count,
										sequence, startPos);
								listElement.setEndPos(startPos);
								list.add(listElement);
								listElement = null;
								break;
							}
						}

						break;
					case '#':
						count = 1;
						while (charAt(fScannerPosition) == '*'
								|| charAt(fScannerPosition) == '#') {
							count++;
							fScannerPosition++;
						}
						sequence = new char[count];
						StringUtil.arraycopy(fSource, fScannerPosition - count,
								sequence, 0, count);
						// last character determines type of list
						if (charAt(fScannerPosition - 1) == '#') {
							type = WPListElement.OL;
						} else {
							type = WPListElement.UL;
						}

						while (true) {
							ch = charAt(fScannerPosition++);
							if (!StringUtil.isWhitespace(ch)) {
								startPos = fScannerPosition - 1;
								listElement = new WPListElement(type, count,
										sequence, startPos);
								break;
							}
							if (ch == '\n') {
								fScannerPosition--; // to detect next row
								startPos = fScannerPosition;
								listElement = new WPListElement(type, count,
										sequence, startPos);
								listElement.setEndPos(startPos);
								list.add(listElement);
								listElement = null;
								break;
							}
						}

						break;
					default:
						return list;
					}
				}
				ch = charAt(fScannerPosition++);
			}
		} catch (IndexOutOfBoundsException e) {
			// e.printStackTrace();
		}
		if (list != null) {
			if (listElement != null) {
				listElement.setEndPos(fScannerPosition - 1);
				list.add(listElement);
				listElement = null;
			}
			return list;
		}
		return null;
	}

	public int nextNewline() {
		while (true) {
			if (charAt(fScannerPosition++) == '\n') {
				return --fScannerPosition;
			}
		}
	}

	public int indexEndOfComment() {
		char ch;
		try {
			while (true) {
				ch = charAt(fScannerPosition++);
				if (ch == '-' && charAt(fScannerPosition) == '-'
						&& charAt(fScannerPosition + 1) == '>') {
					return fScannerPosition + 2;
				}
			}
		} catch (IndexOutOfBoundsException e) {
			// ..
		}
		return -1;
	}

	public int indexEndOfNowiki() {
		char ch;
		try {
			while (true) {
				ch = charAt(fScannerPosition++);
				if (ch == '<' && charAt(fScannerPosition) == '/'
						&& charAt(fScannerPosition + 1) == 'n'
						&& charAt(fScannerPosition + 2) == 'o'
						&& charAt(fScannerPosition + 3) == 'w'
						&& charAt(fScannerPosition + 4) == 'i'
						&& charAt(fScannerPosition + 5) == 'k'
						&& charAt(fScannerPosition + 6) == 'i'
						&& charAt(fScannerPosition + 7) == '>') {
					return fScannerPosition + 8;
				}
			}
		} catch (IndexOutOfBoundsException e) {
			// ..
		}
		return -1;
	}

	public int indexEndOfTable() {
		// check nowiki and html comments?
		int count = 1;
		char ch;
		try {
			while (true) {
				ch = charAt(fScannerPosition++);
				if (ch == '<' && charAt(fScannerPosition) == '!'
						&& charAt(fScannerPosition + 1) == '-'
						&& charAt(fScannerPosition + 2) == '-') {
					fScannerPosition = indexEndOfComment();
					if (fScannerPosition == (-1)) {
						return -1;
					}
				} else if (ch == '<' && charAt(fScannerPosition) == 'n'
						&& charAt(fScannerPosition + 1) == 'o'
						&& charAt(fScannerPosition + 2) == 'w'
						&& charAt(fScannerPosition + 3) == 'i'
						&& charAt(fScannerPosition + 4) == 'k'
						&& charAt(fScannerPosition + 5) == 'i'
						&& charAt(fScannerPosition + 6) == '>') {
					fScannerPosition = indexEndOfNowiki();
					if (fScannerPosition == (-1)) {
						return -1;
					}
				} else if (ch == '\n' && charAt(fScannerPosition) == '|'
						&& charAt(fScannerPosition + 1) == '}') {
					count--;
					if (count == 0) {
						return fScannerPosition + 2;
					}
				} else if (ch == '\n' && charAt(fScannerPosition) == '{'
						&& charAt(fScannerPosition + 1) == '|') {
					// assume nested table
					count++;
				}
			}
		} catch (IndexOutOfBoundsException e) {
			// ..
		}
		return -1;
	}

	public int indexOf(char ch) {
		try {
			while (true) {
				if (charAt(fScannerPosition++) == ch) {
					return fScannerPosition - 1;
				}
			}
		} catch (IndexOutOfBoundsException e) {
			// ..
		}
		return -1;
	}

	public int indexOf(char ch, char stop) {
		try {
			char c;
			while (true) {
				c = charAt(fScannerPosition);
				if (c == ch) {
					return fScannerPosition;
				}
				if (c == stop) {
					return -1;
				}
				fScannerPosition++;
			}
		} catch (IndexOutOfBoundsException e) {
			// ..
		}
		return -1;
	}

	/**
	 * Scan the attributes of a wiki table cell
	 * 
	 * @return
	 */
	public int indexOfAttributes() {
		try {
			// int start = fScannerPosition;
			char ch = charAt(fScannerPosition);
			while (true) {

				// TODO scan for NOWIKI and HTML comments

				// scan for Wiki links, which probably contain '|' character
				if (ch == '[') {
					int countBrackets = 1;
					fScannerPosition++;
					while (countBrackets > 0) {
						ch = charAt(fScannerPosition++);
						if (ch == '[') {
							++countBrackets;
						} else if (ch == ']') {
							--countBrackets;
						}
					}
					continue;
				}
				if (ch == '|') {
					if (charAt(fScannerPosition + 1) == '|') {
						return -1;
					}
					return fScannerPosition;
				}
				// if (!StringUtil.isJavaIdentifierPart(ch) &&
				// !StringUtil.isWhitespace(ch) && ch != '=' && ch != '\"' && ch
				// != '%'
				// && ch != '#' && ch != ';' && ch != ':') {
				// return -1;
				// }
				if (charAt(fScannerPosition) == '\n') {
					return -1;
				}
				ch = charAt(++fScannerPosition);
			}
		} catch (IndexOutOfBoundsException e) {
			// ..
		}
		return -1;
	}

	public int scanHTMLTag(HashMap map, int currPos) throws TagParsingException {
		// inserts the tagname with key WikipediaScanner.TAG_NAME into the map
		int end = scanHTMLStarttag(map, currPos);
		// inserts the attributes as key, value pairs into the map
		return scanHTMLAttributes(map, end);
	}

	public int scanHTMLStarttag(HashMap map, int currPos)
			throws TagParsingException {
		fScannerPosition = currPos;
		try {
			char ch = charAt(fScannerPosition);
			if (ch == '<') {
				++fScannerPosition;
			}
			int attrStartPosition = fScannerPosition;
			while (StringUtil.isLetterOrDigit(charAt(fScannerPosition++))) {
			}
			int attrEndPosition = --fScannerPosition;
			map.put(TAG_NAME, StringUtil.str(fSource, attrStartPosition,
					attrEndPosition - attrStartPosition));
			// map.put(TAG_NAME, fSource.substring(attrStartPosition,
			// attrEndPosition - attrStartPosition));
			return fScannerPosition;
		} catch (IndexOutOfBoundsException e) {
			throw new TagParsingException();
		}
	}

	public int scanHTMLAttributes(HashMap map, int currPos)
			throws TagParsingException {
		fScannerPosition = currPos;
		try {
			char ch = charAt(fScannerPosition);
			if (ch == '>') {
				return ++fScannerPosition;
			}

			while (ch != '>') {
				scanWhiteSpace();
				ch = charAt(fScannerPosition++);
				if (StringUtil.isLetter(ch)) {
					scanSingleAttribute(map);
				} else {
					if (ch == '>') {
						return fScannerPosition;
					}
					throw new TagParsingException();
				}
				scanWhiteSpace();
			}
			return fScannerPosition;
		} catch (IndexOutOfBoundsException e) {
			throw new TagParsingException();
		}
	}

	private void scanSingleAttribute(HashMap map) {
		// boolean doubleQuotes;
		// scan attribute name
		int attrStartPosition = fScannerPosition - 1;
		while (StringUtil.isLetterOrDigit(charAt(fScannerPosition++))) {
		}
		int attrEndPosition = --fScannerPosition;

		scanWhiteSpace();
		char ch = charAt(fScannerPosition++);
		if (ch != '=') {
			return;
		}
		scanWhiteSpace();
		ch = charAt(fScannerPosition++);

		if (ch != '\"') {
			fScannerPosition--;
			// doubleQuotes = false;
		} else {
			// doubleQuotes = true;
		}
		// scanAttributeValue();
		int valueStartPosition = fScannerPosition;
		ch = charAt(fScannerPosition++);
		// if (doubleQuotes) {
		// while (ch != '\"') {
		// ch = charAt(fScannerPosition++);
		// }
		// } else {
		int lastSpace = -1;
		while (ch != '\n' && ch != '>' && ch != '<' && ch != '|' && ch != '['
				&& ch != '\'' && ch != ';' && ch != '=' && ch != '\"') {
			if (ch == ' ') {
				lastSpace = fScannerPosition;
			}
			ch = charAt(fScannerPosition++);
		}
		if (ch == '=' && lastSpace != (-1)
				&& lastSpace < (fScannerPosition - 1)) {
			// assuming start of another attribute
			fScannerPosition = lastSpace + 1;
		}
		// }
		int valueEndPosition = --fScannerPosition;
		map.put(StringUtil.str(fSource, attrStartPosition, attrEndPosition
				- attrStartPosition), StringUtil.str(fSource,
				valueStartPosition, valueEndPosition - valueStartPosition));
		// map.put(fSource.substring(attrStartPosition, attrEndPosition
		// - attrStartPosition), fSource.substring(valueStartPosition,
		// valueEndPosition - valueStartPosition));
		if (ch != '\"') {
		}
		ch = charAt(fScannerPosition++);
	}

	public void scanWhiteSpace() {
		while (StringUtil.isWhitespace(charAt(fScannerPosition++))) {
		}
		--fScannerPosition;
	}

	public StringBuffer replaceTemplateParameters(String template,
			Map fTemplateParameters) {
		StringBuffer buffer = new StringBuffer(template.length() + 128);
		int bufferStart = 0;
		try {
			char ch;
			int parameterStart = -1;
			while (true) {
				ch = charAt(fScannerPosition++);
				if (ch == '{' && charAt(fScannerPosition) == '{'
						&& charAt(fScannerPosition + 1) == '{') {
					fScannerPosition += 2;
					parameterStart = fScannerPosition;
				} else if (ch == '}' && charAt(fScannerPosition) == '}'
						&& charAt(fScannerPosition + 1) == '}'
						&& (parameterStart > 0)) {
					String completeTemplateString = StringUtil.str(fSource,
							parameterStart, fScannerPosition - parameterStart
									- 1);
					// String completeTemplateString = fSource.substring(
					// parameterStart, fScannerPosition - parameterStart
					// - 1);
					completeTemplateString = completeTemplateString.trim();
					String value = (String) fTemplateParameters
							.get(completeTemplateString);

					if (value != null) {
						if (bufferStart < fScannerPosition) {
							StringUtil.append(buffer, fSource, bufferStart,
									parameterStart - bufferStart - 3);
							// buffer.append(fSource.substring(bufferStart,
							// parameterStart - bufferStart - 3));
						}
						buffer.append(value);
						bufferStart = fScannerPosition + 2;
					}
					fScannerPosition += 2;
					parameterStart = -1;
				}

			}
		} catch (IndexOutOfBoundsException e) {
			// ignore
		}
		if (bufferStart < fScannerPosition) {
			StringUtil.append(buffer, fSource, bufferStart, fScannerPosition
					- bufferStart - 1);
			// buffer.append(fSource.substring(bufferStart, fScannerPosition
			// - bufferStart - 1));
		}
		return buffer;
	}

}