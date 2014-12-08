package at.irian.cdiatwork.ideafork.ee.frontend.servlet;

import at.irian.cdiatwork.ideafork.ee.shared.ActiveUserHolder;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(urlPatterns = {"/pages/import/*", "/idea/import"})
public class UserAwareFilter implements Filter {
    @Inject
    private ActiveUserHolder userHolder;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (userHolder.isLoggedIn()) { //lazy check: valid user + no session-timeout
            chain.doFilter(request, response);
        } else {
            request.getRequestDispatcher("/pages/user/login.xhtml").forward(request, response);
        }
    }

    @Override
    public void destroy() {
    }
}
