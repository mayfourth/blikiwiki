package de.jtidy.gwt.client.html2wiki;

import java.util.HashMap;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ConverterServiceAsync {
	public void convert(String expression, HashMap<String,String> properties, int counter, AsyncCallback callback);
}