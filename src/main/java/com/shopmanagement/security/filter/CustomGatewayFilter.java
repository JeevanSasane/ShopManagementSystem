//package com.appy.himsrecode.security.filter;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.Ordered;
//import org.springframework.core.annotation.Order;
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@Slf4j
//@Configuration
//@Order(Ordered.HIGHEST_PRECEDENCE)
//public class CustomGatewayFilter implements Filter
//{
//
//    @Override
//    public void init(FilterConfig filterConfig) {
//        log.info("########## Initiating Custom filter ##########");
//    }
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        HttpServletResponse response = (HttpServletResponse) servletResponse;
//        log.info("request.getHeader(\"X-Forwarded-Host\"); " + request.getHeader("X-Forwarded-Host"));
//        String proxyForwardedHostHeader = request.getHeader("X-Forwarded-Host");
//        if (proxyForwardedHostHeader == null) {
//            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized Access, you should pass through the API gateway");
//        }
//        filterChain.doFilter(servletRequest, servletResponse);
//    }
//
//    @Override
//    public void destroy() {
//        log.info("########## Destroying Custom filter ##########");
//    }
//}
