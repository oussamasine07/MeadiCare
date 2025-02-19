package com.medicare.patient.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class CreatePatientServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException
    {

        RequestDispatcher rd = req.getRequestDispatcher("pages/patient/index.jsp");
        rd.forward( req, res );
    }

    public void doPost ( HttpServletRequest req, HttpServletResponse res )
        throws ServletException, IOException
    {
        // create a new patient here
    }

}
