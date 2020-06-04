package webfilters;

import model.User;
import service.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/")
public class UserLoginFilter implements Filter {
    private UserService userService = UserService.getInstance();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        String name = req.getParameter("name");
        String password = req.getParameter("password");

        if (userService.validateUser(name, password)) {
            User user = userService.getUserByName(name);
            req.getSession().setAttribute("role", user.getRole());
            req.getSession().setAttribute("id", user.getId());
        } else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().println("No Such User Here!");
        }

        HttpSession session = req.getSession();
        String role = (String) session.getAttribute("role");
        if (role == null) {
            req.getRequestDispatcher("/loginPage.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("/").forward(req, resp);
        }
    }
}
