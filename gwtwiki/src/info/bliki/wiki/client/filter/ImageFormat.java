package info.bliki.wiki.client.filter;

/**
 * Copied from Patch #1488331 sf.net user: o_rossmueller
 * 
 * Modified by axelclk
 * 
 * http://sourceforge.net/tracker/index.php?func=detail&aid=1488331&group_id=128886&atid=713150
 * 
 */
public class ImageFormat {
	private String filename;

	private String type;

	private String location = "none";

	private String sizeStr = null;

	private int size = -1;

	private String caption;

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location.toLowerCase();
	}

	public int getSize() {
		return size;
	}

	public String getSizeStr() {
		return sizeStr;
	}

	public void setSize(String size) {
		sizeStr = size.toLowerCase();
		if (sizeStr.endsWith("px")) {
			try {
				this.size = Integer.parseInt(sizeStr.substring(0, sizeStr
						.length() - 2));
			} catch (NumberFormatException e) {
				this.size = -1;
				this.sizeStr = null;
			}
		}
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type.toLowerCase();
	}

	public static ImageFormat getImageFormat(String linkText, String imageLocale) {
		ImageFormat img = new ImageFormat();
		String[] tokens = linkText.split("\\|");
		String token = tokens[0].trim();
		if (token.startsWith(imageLocale + ':')) {
			img.setFilename(token.substring(imageLocale.length() + 1));
		} else {
			// Image:
			img.setFilename(token.substring(6));
		}
		for (int i = 1; i < tokens.length; i++) {
			token = tokens[i].trim();
			if (token.equalsIgnoreCase("frame")
					|| token.equalsIgnoreCase("thumb")
					|| token.equalsIgnoreCase("thumbnail")) {
				img.setType(token);
				continue;
			}

			if (token.equalsIgnoreCase("right")
					|| token.equalsIgnoreCase("left")
					|| token.equalsIgnoreCase("center")
					|| token.equalsIgnoreCase("none")) {
				img.setLocation(token);
				continue;
			}

			if (tokens[i].toLowerCase().endsWith("px")) {
				img.setSize(token);
				continue;
			}

			img.setCaption(token);
		}

		return img;
	}

}
