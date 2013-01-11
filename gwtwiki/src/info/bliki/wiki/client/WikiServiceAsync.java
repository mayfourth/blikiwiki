package info.bliki.wiki.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

interface WikiServiceAsync {
	public void send(String title, String body, int counter, AsyncCallback callback);
}