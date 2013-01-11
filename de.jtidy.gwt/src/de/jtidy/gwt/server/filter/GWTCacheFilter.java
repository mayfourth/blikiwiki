package de.jtidy.gwt.server.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GWTCacheFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		if (request instanceof HttpServletRequest) {
			String requestUriStr = ((HttpServletRequest) request).getRequestURI();
			int index = requestUriStr.indexOf(".cache");
			if (index >= 0) {
				((HttpServletResponse) response).setHeader("Cache-Control", "public,max-age=" + Integer.MAX_VALUE);
				chain.doFilter(request, response);
				return;
			}
			index = requestUriStr.indexOf(".nocache");
			if (index >= 0) {
				((HttpServletResponse) response).setHeader("Cache-Control", "private,no-cache,no-store");
				chain.doFilter(request, response);
				return;
			}

			index = requestUriStr.lastIndexOf(".");
			if (index >= 0) {
				int len = requestUriStr.length();
				boolean isImage = false;
				String extStr;
				if ((len - index - 1) == 3) {
					extStr = requestUriStr.substring(index + 1);
					if (extStr.equals("gif") || extStr.equals("png") || extStr.equals("jpg")) {
						isImage = true;
					}
				} else if ((len - index - 1) == 4) {
					extStr = requestUriStr.substring(index + 1);
					if (extStr.equals("jpeg")) {
						isImage = true;
					}
				}
				if (isImage) {
					((HttpServletResponse) response).setHeader("Cache-Control", "public,max-age=100000");
					chain.doFilter(request, response);
					return;
				}
			}
		}
		chain.doFilter(request, response);
	}

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
	}

}
