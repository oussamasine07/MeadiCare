package com.medicare.appointment.controller;

import com.medicare.appointment.dao.AppointmentDAO;
import com.medicare.appointment.model.Appointment;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class CancelPatientAppointmentServlet extends HttpServlet {

    AppointmentDAO appointmentDAO = null;

    public void init () {
        appointmentDAO = new AppointmentDAO();
    }



    protected void doPost (HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException
    {
        int id = Integer.parseInt(req.getParameter("id"));
        System.out.println("testing");
        appointmentDAO.cancelAppointment( id );

        res.sendRedirect("/MediCare/appointment/patient?patientId=" + id);
    }
}
