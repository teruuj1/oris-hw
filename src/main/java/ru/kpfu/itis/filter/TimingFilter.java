package ru.kpfu.itis.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(urlPatterns = "/*", filterName = "timingFilter")
public class TimingFilter implements Filter {

    private static final long TIMEOUT = 60 * 1000;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        HttpSession session = httpRequest.getSession(false);

        long currentTime = System.currentTimeMillis();
        if (session != null && !httpRequest.getRequestURI().contains("login")) {
            Long lastAccessTime = (Long) session.getAttribute("lastAccessTime");
            if (lastAccessTime == null) {
                session.setAttribute("lastAccessTime", currentTime);
            } else {
                if (currentTime - lastAccessTime > TIMEOUT) {
                    httpResponse.sendRedirect("/login");
                    session.invalidate();
                    return;
                }
            }
            session.setAttribute("lastAccessTime", currentTime);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
