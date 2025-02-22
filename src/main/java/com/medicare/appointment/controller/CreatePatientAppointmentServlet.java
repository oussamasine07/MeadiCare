package com.medicare.appointment.controller;

import com.medicare.appointment.dao.AppointmentDAO;
import com.medicare.appointment.model.Appointment;
import com.medicare.doctor.dao.DoctorDAO;
import com.medicare.doctor.model.Doctor;
import com.medicare.patient.dao.PatientDAO;
import com.medicare.patient.model.Patient;
import com.medicare.validateinput.Input;
import com.medicare.validateinput.InputError;
import com.medicare.validateinput.Validate;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreatePatientAppointmentServlet extends HttpServlet {

    PatientDAO patientDAO = null;
    DoctorDAO doctorDAO = null;
    AppointmentDAO appointmentDAO = null;

    public void init () {
        patientDAO = new PatientDAO();
        doctorDAO = new DoctorDAO();
        appointmentDAO = new AppointmentDAO();
    }

    public void doGet (HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException
    {
        // get all patients
        List<Patient> patients = patientDAO.getPatients();
        // get all doctors
        List<Doctor> doctors = doctorDAO.getDoctors();

        req.setAttribute("patients", patients);
        req.setAttribute("doctors", doctors);
        RequestDispatcher rd = req.getRequestDispatcher("/pages/appointment/create-appointment.jsp");
        rd.forward(req, res);
    }

    public void doPost (HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException
    {
        ArrayList<InputError> errors = new ArrayList<>();

        int patientId = Integer.parseInt(req.getParameter("patientId"));
        int doctorId = Integer.parseInt(req.getParameter("patientId"));
        String appDate = req.getParameter("date");
        String appTime = req.getParameter("time");
        String motif = req.getParameter("motif");


        if ( Validate.validateEmpty("date", appDate) != null ) errors.add(Validate.validateEmpty("date", appDate));
        if ( Validate.validateEmpty("time", appTime) != null ) errors.add(Validate.validateEmpty("time", appTime));
        if ( Validate.validateEmpty("motif", motif) != null ) errors.add(Validate.validateEmpty("motif", motif));

        if (!errors.isEmpty()) {
            HttpSession session = req.getSession();
            session.setAttribute("errors", errors);

            errors.forEach(err -> System.out.println(err.errorMessage));

            Map<String, String> map = new HashMap<>();
            map.put("date", appDate);
            map.put("time", appTime);
            map.put("motif", motif);

            session.setAttribute("fieldOld", new Input(map));

            res.sendRedirect("/MediCare/appointment/patient/create");
        } else {
            Appointment appointment = new Appointment( patientId, doctorId, appDate, appTime, motif );

            appointmentDAO.insetAppointment( appointment );

            res.sendRedirect("/MediCare/appointment/patient");
        }
    }
}
