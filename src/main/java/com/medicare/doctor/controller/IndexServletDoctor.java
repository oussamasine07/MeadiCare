package com.medicare.doctor.controller;

import com.medicare.doctor.dao.DoctorDAO;
import com.medicare.doctor.model.Doctor;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class IndexServletDoctor extends HttpServlet {

    DoctorDAO doctorDAO = null;
    public void init () {
        doctorDAO = new DoctorDAO();
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException
    {
        List<Doctor> doctors = doctorDAO.getDoctors();
        req.setAttribute("doctors", doctors );
        RequestDispatcher rd = req.getRequestDispatcher("pages/doctor/index.jsp");
        rd.forward(req, res);
    }

    public void doPost (HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException
    {

    }
}
