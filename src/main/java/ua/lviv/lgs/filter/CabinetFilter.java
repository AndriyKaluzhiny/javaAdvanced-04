package ua.lviv.lgs.filter;

import ua.lviv.lgs.service.UserService;
import ua.lviv.lgs.service.impl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.LogRecord;

public class CabinetFilter implements Filter {

    UserService userService = UserServiceImpl.getUserService();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpSession session = httpServletRequest.getSession();

        if (session != null) {
            String email = (String) session.getAttribute("email");

            if (email != null) {
                chain.doFilter(request, response);
                return;
            }
        }
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.sendRedirect("loginPage.jsp");
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
