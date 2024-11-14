package ru.kpfu.itis.servlet;

import jakarta.servlet.annotation.WebServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.kpfu.itis.dto.UserDto;

import java.io.IOException;
import java.util.List;


@WebServlet(name = "userServlet", urlPatterns = "/users")
public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("users", List.of(new UserDto("Kaveh", 1, "Kaveykavey"), new UserDto("Al-Haitham", 10, "ilovekaveh")));
        req.getRequestDispatcher("users.ftl").forward(req, resp);

    }
}
