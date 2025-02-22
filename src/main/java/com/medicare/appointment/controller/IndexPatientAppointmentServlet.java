package com.medicare.appointment.controller;

import com.medicare.appointment.dao.AppointmentDAO;
import com.medicare.appointment.model.Appointment;
import com.medicare.patient.dao.PatientDAO;
import com.medicare.patient.model.Patient;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class IndexPatientAppointmentServlet extends HttpServlet {

    PatientDAO patientDAO = null;
    AppointmentDAO appointmentDAO = null;
    public void init () {
        patientDAO = new PatientDAO();
        appointmentDAO = new AppointmentDAO();
    }

    protected void doGet (HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException
    {
        List<Patient> patients = patientDAO.getPatients();
        req.setAttribute("patients", patients);

        if ( req.getParameter("patientId") != null ) {
            int patientId = Integer.parseInt(req.getParameter("patientId"));

            List<Appointment> appointments = appointmentDAO.getAppointmentByPatientId(patientId);
            req.setAttribute("appointments", appointments);
            appointments.forEach(app -> System.out.println(app.getMotif()));
        }

        RequestDispatcher rd = req.getRequestDispatcher("/pages/appointment/patient-appointment.jsp");
        rd.forward(req, res);
    }
}
