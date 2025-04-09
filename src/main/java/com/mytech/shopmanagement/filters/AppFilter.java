package com.mytech.shopmanagement.filters;

import java.io.IOException;

import com.tech.shopmanagement.wrapper.AppRequestWrapper;
import com.tech.shopmanagement.wrapper.AppResponseWrapper;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AppFilter extends HttpFilter implements Filter {

	private static final long serialVersionUID = -4392349866994930884L;
	
	@Override
	protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		AppRequestWrapper appRequestWrapper= new AppRequestWrapper(request);
		AppResponseWrapper appResponseWrapper= new AppResponseWrapper(response);
		super.doFilter(request, response, chain);
	}

}
