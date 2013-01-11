package info.bliki.wiki.client;

import com.google.gwt.user.client.rpc.RemoteService;

public interface WikiService extends RemoteService {
	public String send(String title, String body, int counter);
}