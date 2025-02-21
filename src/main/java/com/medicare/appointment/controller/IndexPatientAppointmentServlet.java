package com.medicare.appointment.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class IndexPatientAppointmentServlet extends HttpServlet {

    public void init () {

    }

    protected void doGet (HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException
    {
        RequestDispatcher rd = req.getRequestDispatcher("/pages/appointment/patient-appointment.jsp");
        rd.forward(req, res);
    }
}
