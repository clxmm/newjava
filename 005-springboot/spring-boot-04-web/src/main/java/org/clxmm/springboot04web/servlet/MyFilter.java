package org.clxmm.springboot04web.servlet;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author clxmm
 * @version 1.0
 * @date 2020/10/11 8:59 下午
 */
public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        System.out.println(" fileter process");
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
