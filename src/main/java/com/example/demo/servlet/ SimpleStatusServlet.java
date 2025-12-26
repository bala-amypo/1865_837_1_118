package com.example.demo.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/status")
public class SimpleStatusServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Set content type to "text/plain" as required by SRS
        resp.setContentType("text/plain");
        
        // Set HTTP status to 200 OK
        resp.setStatus(HttpServletResponse.SC_OK);
        
        // Write message: "OK"
        resp.getWriter().write("OK");
    }
}