package com.dou.kylin.common.filter;

/**
 * Created by dou on 15/8/6.
 */
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import javax.servlet.http.HttpSession;
/**
 * Servlet filter which disables URL-encoded session identifiers.
 * 去掉JSESSIONID在url里的显示
 */
public class DisableUrlSessionFilter implements Filter {
/*
    private static Log logger = LogFactory.getLog(DisableUrlSessionFilter.class);
*/
    /**
     * Filters requests to disable URL-based session identifiers.
     */
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        // skip non-http requests
        if (!(request instanceof HttpServletRequest)) {
            chain.doFilter(request, response);
            return;
        }
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        // clear session if session id in URL
        if (httpRequest.isRequestedSessionIdFromURL()) {
            HttpSession session = httpRequest.getSession();
            if (session != null) {
                session.invalidate();
            }
        }
        // wrap response to remove URL encoding
        HttpServletResponseWrapper wrappedResponse = new HttpServletResponseWrapper(
                httpResponse) {
            @Override
            public String encodeRedirectUrl(String url) {
                return url;
            }
            @Override
            public String encodeRedirectURL(String url) {
                return url;
            }
            @Override
            public String encodeUrl(String url) {
                return url;
            }
            @Override
            public String encodeURL(String url) {
                return url;
            }
        };
        // process next request in chain
        chain.doFilter(request, wrappedResponse);
    }
    /**
     * Unused.
     */
    public void init(FilterConfig config) throws ServletException {
    }
    /**
     * Unused.
     */
    public void destroy() {
    }
}
