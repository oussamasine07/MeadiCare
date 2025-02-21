package com.medicare.appointment.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class IndexDoctorAppointmentServlet extends HttpServlet {

    public void init () {

    }

    public void doGet(HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException
    {
        // TODO allow doctor to see all his appointment
        RequestDispatcher rd = req.getRequestDispatcher("/pages/appointment/doctor-appointment.jsp");
        rd.forward(req, res);
    }

}
