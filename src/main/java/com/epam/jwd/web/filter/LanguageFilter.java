package com.epam.jwd.web.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@WebFilter(urlPatterns = "/*")
public class LanguageFilter implements Filter {

    private static final String LANGUAGE_COOKIE_NAME = "lang";
    private static final String DEFAULT_LANGUAGE = "en_US";
    private static final String DEFAULT_PATH = "/";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (doesNotContainLangCookie((HttpServletRequest) request)) {
            addLangCookie((HttpServletResponse) response);
        }
        chain.doFilter(request, response);
    }

    private boolean doesNotContainLangCookie(HttpServletRequest httpRequest) {
        final Cookie[] cookies = httpRequest.getCookies();
        return cookies == null || Arrays.stream(cookies)
                .noneMatch(cookie -> cookie.getName().equals(LANGUAGE_COOKIE_NAME));
    }

    private void addLangCookie(HttpServletResponse response) {
        final Cookie languageCookie = new Cookie(LANGUAGE_COOKIE_NAME, DEFAULT_LANGUAGE);
        languageCookie.setPath(DEFAULT_PATH);
        response.addCookie(languageCookie);
    }

}
