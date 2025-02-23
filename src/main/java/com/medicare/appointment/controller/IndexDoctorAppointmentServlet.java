package com.medicare.appointment.controller;

import com.medicare.appointment.dao.AppointmentDAO;
import com.medicare.appointment.model.Appointment;
import com.medicare.doctor.dao.DoctorDAO;
import com.medicare.doctor.model.Doctor;
import com.medicare.patient.model.Patient;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class IndexDoctorAppointmentServlet extends HttpServlet {

    DoctorDAO doctorDAO = null;
    AppointmentDAO appointmentDAO = null;
    public void init () {
        doctorDAO = new DoctorDAO();
        appointmentDAO = new AppointmentDAO();
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException
    {
        List<Doctor> doctors = doctorDAO.getDoctors();
        req.setAttribute("doctors", doctors);
        System.out.println(req.getParameter("doctorId"));
        if ( req.getParameter("doctorId") != null ) {
            int doctorId = Integer.parseInt(req.getParameter("doctorId"));

            List<Appointment> appointments = appointmentDAO.getAppointmentByDcotorId(doctorId);
            appointments.forEach(app -> System.out.println(app.getPatient().getName()));
            req.setAttribute("appointments", appointments);

        }


        RequestDispatcher rd = req.getRequestDispatcher("/pages/appointment/doctor-appointment.jsp");
        rd.forward(req, res);
    }

}
