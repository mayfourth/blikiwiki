package info.bliki.wiki.client.filter.tags;

import info.bliki.wiki.client.util.StringUtil;

public class AbstractTag {
	String fSource;

	int fScannerPosition;

	char fChar;

	StringBuffer fBuffer;

	int valueType = -1;

	int attrStartPosition;

	int attrEndPosition;

	int valueStartPosition;

	int valueEndPosition;

	private int fToken;

	public AbstractTag(int token) {
		fToken = token;

	}

	public final char charAt(int index) {
		if (index >= fSource.length()) {
			throw new IndexOutOfBoundsException("Required position: " + index
					+ " is greater than AbstractTag#fSource length: " + fSource.length());
		}
		return fSource.charAt(index);
	}

	public int scanHTMLAttributes(StringBuffer buffer, String source,
			int currPos) throws TagParsingException {
		fSource = source;
		fScannerPosition = currPos;
		fChar = charAt(fScannerPosition);
		if (fChar == '>') {
			return ++fScannerPosition;
		}
		fBuffer = buffer;
		try {
			while (fChar != '>') {
				scanWhiteSpace();
				fChar = charAt(fScannerPosition++);
				if (StringUtil.isLetter(fChar)) {
					scanSingleAttribute();
				} else {
					if (fChar != '>') {
						throw new TagParsingException();
					}
				}
				scanWhiteSpace();
			}
			return fScannerPosition;
		} catch (IndexOutOfBoundsException e) {
			throw new TagParsingException();
		}
		// return fScannerPosition;
	}

	public int scanWPTableAttributes(StringBuffer buffer, String source,
			int currPos) throws TagParsingException {
		fSource = source;
		fScannerPosition = currPos;
		fChar = charAt(fScannerPosition);
		if (fChar == '|') {
			return ++fScannerPosition;
		}
		fBuffer = buffer;
		try {
			while (fChar != '|') {
				scanWhiteSpace();
				fChar = charAt(fScannerPosition++);
				if (StringUtil.isLetter(fChar)) {
					scanSingleAttribute();
				} else {
					if (fChar != '|') {
						throw new TagParsingException();
					}
				}
				scanWhiteSpace();
			}
			return fScannerPosition;
		} catch (IndexOutOfBoundsException e) {
			throw new TagParsingException();
		}
		// return fScannerPosition;
	}

	private void scanSingleAttribute() {
		// boolean doubleQuotes;
		scanAttributeName();

		scanWhiteSpace();
		fChar = charAt(fScannerPosition++);
		if (fChar != '=') {
			return;
		}
		scanWhiteSpace();
		fChar = charAt(fScannerPosition++);

		if (fChar != '\"') {
			fScannerPosition--;
			// doubleQuotes = false;
			scanAttributeValue();
		} else {
			// doubleQuotes = true;
			valueStartPosition = fScannerPosition;
			fChar = charAt(fScannerPosition++);
			while (fChar != '\"') {
				fChar = charAt(fScannerPosition++);
			}
			valueEndPosition = --fScannerPosition;
		}

		generate(attrStartPosition, attrEndPosition - attrStartPosition);
		if (fChar == '\"') {
			fScannerPosition++;
		}
		fChar = charAt(fScannerPosition++);
	}

	public void scanWhiteSpace() {
		while (StringUtil.isWhitespace(charAt(fScannerPosition++))) {
		}
		--fScannerPosition;
	}

	public void scanAttributeName() {
		attrStartPosition = fScannerPosition - 1;
		while (StringUtil.isLetter(charAt(fScannerPosition++))) {
		}

		attrEndPosition = --fScannerPosition;
		// System.out.println(new String(fSource, attrStartPosition,
		// attrEndPosition - attrStartPosition));
	}

	public boolean checkValueText() {
		return true;
	}

	public boolean generate(int srcPtr, int srcLength) {
		return false;
	}

	public void scanAttributeValue() {
		valueStartPosition = fScannerPosition;
		char ch = charAt(fScannerPosition++);
		// if (doubleQuotes) {
		// while (ch != '\"') {
		// ch = fSource[fScannerPosition++];
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
		valueEndPosition = --fScannerPosition;
		// System.out.println(new String(fSource, valueStartPosition,
		// valueEndPosition - valueStartPosition));

		switch (valueType) {
		// case 1: //integer
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		return (obj instanceof AbstractTag)
				&& fToken == ((AbstractTag) obj).fToken;
	}

	/**
	 * @return
	 */
	public int getToken() {
		return fToken;
	}

	public String getTagName() {
		return "";
	}

}