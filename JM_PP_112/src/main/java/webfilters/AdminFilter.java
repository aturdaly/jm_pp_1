package webfilters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/admin/*")
public class AdminFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession();
        String role = (String) session.getAttribute("role");

        if (role == null) {
            req.getRequestDispatcher("/loginPage").forward(req, resp);
        } else if (role.equals("admin")) {
            filterChain.doFilter(req, resp);
        } else if (role.equals("user")) {
            resp.sendRedirect("/");
        }
    }
}
