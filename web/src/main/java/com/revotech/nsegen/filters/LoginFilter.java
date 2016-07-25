package com.revotech.nsegen.filters;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Revotech on 14.07.16.
 */
public class LoginFilter implements Filter {

    private static Logger log = Logger.getLogger(LoginFilter.class);

    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        String nickname = null;
        if(session.getAttribute("nickname") != null ) {
            nickname = session.getAttribute("nickname").toString();
        }
        if (nickname == null) {
            resp.sendRedirect("authorization?action=signinpage");
            return;
        }
        // pass the request along the filter chain
        chain.doFilter(request, response);
    }

    public void init(FilterConfig fConfig) throws ServletException {
    }

}
