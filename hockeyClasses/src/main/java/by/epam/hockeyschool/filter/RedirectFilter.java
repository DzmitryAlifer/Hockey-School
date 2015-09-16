package by.epam.hockeyschool.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Dmitry Olifer on 16.07.2015.
 *
 * The class {@code RedirectFilter} is an implementation of
 * {@code Filter} that realizes all its necessary methods that
 * forms a life-cycle of a filter. Gives an ability to redirect
 * the call to the main page with direct reference to jsp.
 */
@WebFilter  (urlPatterns = {"/jsp/*"},
            initParams = {@WebInitParam(name = "indexPath", value = "/index.jsp")})
public class RedirectFilter implements Filter {

    private String indexPath;

    /**
     * Filter starting method. Starts filter with every servlet's
     * call.
     * @param   filterConfig the basic data of for filter.
     * @throws  ServletException if any servlet errors occurs.
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        indexPath = filterConfig.getInitParameter("indexPath");
    }

    /**
     * Filter's main method that processes the {@code request} and also
     * works with {@code response}. Besides this, calls {@code doFilter()}
     * method that starts other filters and servlet.
     * @param   request the instance of {@code ServletRequest}.
     * @param   response the instance of {@code ServletResponse}.
     * @param   chain the instance of {@code FilterChain}.
     * @throws  java.io.IOException if any input/output errors occurs.
     * @throws  ServletException if any servlet errors occurs.
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.sendRedirect(httpRequest.getContextPath() + indexPath);
        chain.doFilter(request, response);
    }

    /**
     * Filter ending method. Calls when filter stops its existence.
     */
    @Override
    public void destroy() {
    }
}
