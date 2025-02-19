package com.medicare.patient.controller;

import com.medicare.patient.dao.PatientDAO;
import com.medicare.patient.model.Patient;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class IndexPatientServlet extends HttpServlet {

    public PatientDAO patientDAO = null;

    @Override
    public void init() throws ServletException {
        patientDAO = new PatientDAO();
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException
    {
        List<Patient> patients = patientDAO.getPatients();
        req.setAttribute("patients", patients);


        RequestDispatcher rd = req.getRequestDispatcher("pages/patient/index.jsp");
        rd.forward( req, res );
    }

    public void doPost ( HttpServletRequest req, HttpServletResponse res )
            throws ServletException, IOException
    {
        // create a new patient here
    }
}
