package com.medicare.appointment.controller;

import com.medicare.appointment.dao.AppointmentDAO;
import com.medicare.appointment.model.Appointment;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class PostponeAppointmentServlet extends HttpServlet {

    AppointmentDAO appointmentDAO = null;
    public void init () {
        appointmentDAO = new AppointmentDAO();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException
    {
        int apptId = Integer.parseInt(req.getParameter("id"));
        Appointment appointment = appointmentDAO.getAppointmentById( apptId );
        req.setAttribute("appointment", appointment);

        RequestDispatcher rd = req.getRequestDispatcher("/pages/appointment/postpone-appointment.jsp");
        rd.forward(req, res);
    }

    protected void doPost (HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException
    {
        int id = Integer.parseInt(req.getParameter("id"));
        String date = req.getParameter("date");
        String time = req.getParameter("time");

        Appointment appointment = new Appointment(id, date, time);

        appointmentDAO.postponeAppointment(appointment);

        res.sendRedirect("/MediCare/appointment/doctor");

    }

}




















