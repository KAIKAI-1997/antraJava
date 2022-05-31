package com.HWday19.secure;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;


@WebFilter("/*")
public class filter1 implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("before filter1");

        chain.doFilter(request,response);

        System.out.println("after filter1");
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
