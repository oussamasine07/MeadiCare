package com.medicare.patient.controller;

import com.medicare.patient.dao.PatientDAO;
import com.medicare.patient.model.Patient;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class CreatePatientServlet extends HttpServlet {

    PatientDAO patientDAO = null;

    @Override
    public void init() {
        patientDAO = new PatientDAO();
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException
    {

        RequestDispatcher rd = req.getRequestDispatcher("/pages/patient/create.jsp");
        rd.forward( req, res );
    }

    public void doPost ( HttpServletRequest req, HttpServletResponse res )
        throws ServletException, IOException
    {
        String name = req.getParameter("name");
        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");

        Patient patient = new Patient( name, username, email, phone );

        patientDAO.inserPatient( patient );

        res.sendRedirect("/MediCare/patient");

    }

}
