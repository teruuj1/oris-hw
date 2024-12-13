package ru.kpfu.itis.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/handle")
public class ExceptionHandler extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handle(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handle(req, resp);
    }

    public void handle(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Throwable throwable = (Throwable) req.getAttribute("jakarta.servlet.error.exception");
        Integer code = (Integer) req.getAttribute("jakarta.servlet.error.status.code");
        String url = (String) req.getAttribute("jakarta.servlet.error.request_url");

        req.setAttribute("statusCode", code);
        req.setAttribute("url", url == null ? "": url);

        if (code==500) {
            req.setAttribute("message", throwable.getMessage());
        }

        req.getRequestDispatcher("exception.ftl").forward(req, resp);
    }
}
