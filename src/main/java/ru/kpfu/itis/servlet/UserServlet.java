package ru.kpfu.itis.servlet;

import jakarta.servlet.annotation.WebServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kpfu.itis.service.UserService;
import ru.kpfu.itis.service.impl.UserServiceImpl;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.List;


@WebServlet(name = "userServlet", urlPatterns = "/users")
public class UserServlet extends HttpServlet {

    private static final Logger LOG =
            LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private final UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        if (login != null) {
            req.setAttribute("users", List.of("feokff", ";l,;,l;,"));
        } else {
            req.setAttribute("users", userService.getAll());
        }
        LOG.info("users page has been opened");
        req.getRequestDispatcher("users.ftl").forward(req, resp);
    }
}
