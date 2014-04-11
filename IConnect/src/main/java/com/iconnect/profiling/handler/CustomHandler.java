package com.iconnect.profiling.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

public class CustomHandler implements AccessDeniedHandler {
	private String url;

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public void handle(HttpServletRequest request,
			HttpServletResponse response, AccessDeniedException exception)
			throws IOException, ServletException {
		response.sendRedirect(url);
		request.getSession().setAttribute("url", "No permissions to this page");
	}

}
