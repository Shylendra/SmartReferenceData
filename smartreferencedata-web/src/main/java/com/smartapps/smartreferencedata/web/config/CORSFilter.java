package com.smartapps.smartreferencedata.web.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(-1)  
@Component
public class CORSFilter implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		
        HttpServletResponse response = (HttpServletResponse) res;
        
        response.setHeader("Access-Control-Allow-Origin", "*"); // * = all domainName
        response.setHeader("Access-Control-Allow-Credentials", "true"); // allow CrossDomain to use Origin Domain
        response.setHeader("Access-Control-Allow-Methods", "*"); 
        response.setHeader("Access-Control-Max-Age", "3600"); // Preflight cache duration in browser
        response.setHeader("Access-Control-Allow-Headers", "*"); // all header
        chain.doFilter(req, res);
	}

}
