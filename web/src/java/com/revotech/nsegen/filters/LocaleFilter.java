package com.revotech.nsegen.filters;

/**
 * Created by Revotech on 22.07.16.
 */

import com.revotech.nsegen.constants.Locales;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;

/**
 * Class {@code LocaleFilter} is the class, that implements {@code Filter} interface to
 * deal with localization in the system.
 * @author Veronica Bahel
 */
public class LocaleFilter implements Filter {
    private String locale;

    private static Logger log = Logger.getLogger(LocaleFilter.class);

    /**
     * <p>Sets initial locale for the system.</p>
     * <p>
     * @param fConfig is the configuration of the filter.
     */
    @Override
    public void init(FilterConfig fConfig){ locale = Locales.EN_LOCALE; }
    /**
     * <p>Sets necessary locale for the next pages.</p>
     * @param request is necessary to get actual locale and set next one.
     * @param response is necessary for the servlet to work correctly
     * @param chain is filter parameter
     * @throws IOException
     * @throws ServletException
     * @see IOException
     * @see ServletException
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {


        HttpServletRequest req = (HttpServletRequest) request;
        String lang = req.getParameter("locale");
        if(lang != null){
            req.getSession().setAttribute(Locales.ATTR_LOCALE, lang);
        }
        //req.removeAttribute(locale);
        //log.info(req.getServletPath());
        if (req.getSession().getAttribute(Locales.ATTR_LOCALE) == null) {
            req.getSession().setAttribute(Locales.ATTR_LOCALE, locale);
        }
        if (req.getSession().getAttribute(Locales.ATTR_LOCALE) == Locales.EN_LOCALE) {
            req.getSession().setAttribute(Locales.ATTR_LOCALE, Locales.EN_LOCALE);
        }
        if (req.getSession().getAttribute(Locales.ATTR_LOCALE) == Locales.RU_LOCALE) {
            req.getSession().setAttribute(Locales.ATTR_LOCALE, Locales.RU_LOCALE);
        }
        chain.doFilter(request, response);
    }


    @Override
    public void destroy() { }
}