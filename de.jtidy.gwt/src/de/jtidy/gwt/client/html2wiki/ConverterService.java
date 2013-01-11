package de.jtidy.gwt.client.html2wiki;

import java.util.HashMap;

import com.google.gwt.user.client.rpc.RemoteService;

public interface ConverterService extends RemoteService {
	public String convert(String expression, HashMap<String,String> properties, int counter);
}