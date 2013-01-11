package info.bliki.wiki.server;

import info.bliki.wiki.client.WikiService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class WikiServiceImpl extends RemoteServiceServlet implements WikiService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 45532146667247760L;

	public WikiServiceImpl() {
		super();
	}

	public String send(String title, String body, int counter) {
		// some checks
		if (body.length() > Short.MAX_VALUE) {
			return " ";
		}
		if (title.length() > Byte.MAX_VALUE) {
			return " ";
		}
		byte[] buffer;
		FileOutputStream fo = null;
		try {
			title = title.replaceAll(" ", "_") + "_";
			buffer = body.getBytes("utf-8");
			File dir = new File("c:\\temp");
			File file = File.createTempFile(title, ".wp", dir);
			fo = new FileOutputStream(file);
			fo.write(buffer);
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fo != null) {
				try {
					fo.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return " ";
	}

}
