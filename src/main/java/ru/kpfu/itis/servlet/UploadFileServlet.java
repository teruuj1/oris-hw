package ru.kpfu.itis.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;

@WebServlet(urlPatterns = "/upload")
@MultipartConfig(
        maxFileSize = 5*1024*1024,
        maxRequestSize = 10*1024*1024
)
public class UploadFileServlet extends HttpServlet {

    private static final String FILE_PREFIX = "C:";
    private static final int DIRECTORIES_COUNT = 10;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("upload.ftl");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Part part = req.getPart("file");

        String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
        File file = new File(FILE_PREFIX + File.separator + filename.hashCode() % DIRECTORIES_COUNT +
                File.separator + filename);

        InputStream content = part.getInputStream();
        file.getParentFile().mkdirs();
        file.createNewFile();

        FileOutputStream outputStream = new FileOutputStream(file);
        byte[] buffer = new byte[content.available()];
        content.read(buffer);
        outputStream.write(buffer);
        outputStream.close();
    }
}
