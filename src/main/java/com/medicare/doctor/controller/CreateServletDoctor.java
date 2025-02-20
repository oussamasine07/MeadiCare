package com.medicare.doctor.controller;

import com.medicare.doctor.dao.DoctorDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/doctor/create")
public class CreateServletDoctor extends HttpServlet {

    DoctorDAO doctorDAO = null;
    public void init () {
        doctorDAO = new DoctorDAO();
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException
    {
        System.out.println(req.getServletPath());

        RequestDispatcher rd = req.getRequestDispatcher( "./pages/home.jsp");
        rd.forward(req, res);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException
    {

    }
}
