package com.medicare.home.controller;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.RequestDispatcher;

import java.io.IOException;

public class HomeServlet extends HttpServlet {

    public void init () {}

    public void doGet( HttpServletRequest req, HttpServletResponse res )
        throws ServletException, IOException
    {
        // get data from database

        RequestDispatcher rd = req.getRequestDispatcher("/pages/home.jsp");
        rd.forward(req, res);
    }


}
