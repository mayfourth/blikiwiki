package info.bliki.wiki.client.util;

/*
 * Copyright 2006 Robert Hanson <iamroberthanson AT gmail.com>
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *    http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.util.HashMap;
import java.util.Map;

public class Location {
	private String hash;

	private String host;

	private String hostName;

	private String href;

	private String path;

	private String port;

	private String protocol;

	private String queryString;

	private HashMap paramMap;

	public String getHash() {
		return hash;
	}

	public String getHost() {
		return host;
	}

	public String getHostName() {
		return hostName;
	}

	public String getHref() {
		return href;
	}

	public String getParameter(String name) {
		return (String) paramMap.get(name);
	}

	public Map getParameterMap() {
		return paramMap;
	}

	public HashMap getParamMap() {
		return paramMap;
	}

	public String getPath() {
		return path;
	}

	public String getPort() {
		return port;
	}

	public String getProtocol() {
		return protocol;
	}

	public String getQueryString() {
		return queryString;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public void setParamMap(HashMap paramMap) {
		this.paramMap = paramMap;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	protected void setQueryString(String queryString) {
		this.queryString = queryString;
		paramMap = new HashMap();

		createParameterMap(paramMap, queryString);
	}

	public static String getParameter(String queryString, String name) {
		HashMap map = new HashMap();
		String query = queryString;
		int index = query.indexOf('?');
		if (index>=0) {
			query = query.substring(index);
		}
		createParameterMap(map, query);
		return (String) map.get(name);
	}

	private static void createParameterMap(HashMap map, String queryString) {
		if (queryString != null && queryString.length() > 1) {
			String qs = queryString.substring(1);
			String[] kvPairs = qs.split("&");
			for (int i = 0; i < kvPairs.length; i++) {
				String[] kv = kvPairs[i].split("=");
				if (kv.length > 1) {
					map.put(kv[0], unescape(kv[1]));
				} else {
					map.put(kv[0], "");
				}
			}
		}
	}

	private static native String unescape(String val) /*-{
	 return unescape(val);
	 }-*/;

}