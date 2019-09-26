package com.as.lingod.config;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by brander on 2019/9/14
 */
@Component
@WebFilter(urlPatterns = "/*", filterName = "validate")
public class ValidateFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        resp.setHeader("Access-Control-Max-Age", "3600");
        //设置可以接受的请求头
        resp.setHeader("Access-Control-Allow-Headers", "validatecode, content-type, cache-control, postman-token,Authorization");
        filterChain.doFilter(req,response);
    }

    @Override
    public void destroy() {

    }
}