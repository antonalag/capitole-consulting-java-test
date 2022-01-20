package com.alvarezaguilera.javatest.filter;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Filter that shows through logs the time needed to execute a REST request.
 * 
 * @author anjoal
 *
 */
@Component
@WebFilter("/*")
public class StatsFilter implements Filter {

	private static final Logger LOG = LoggerFactory.getLogger(StatsFilter.class);

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		Instant start = Instant.now();
		try {
			chain.doFilter(request, response);
		} finally {
			Instant finish = Instant.now();
			long time = Duration.between(start, finish).toMillis();
			LOG.info("{}: executed in {} ms", ((HttpServletRequest) request).getRequestURI(), time);
		}
		
	}
	
}
