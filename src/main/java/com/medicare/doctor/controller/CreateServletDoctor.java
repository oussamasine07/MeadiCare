package com.medicare.doctor.controller;

import com.medicare.doctor.dao.DoctorDAO;
import com.medicare.doctor.model.Doctor;
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
import java.util.Map;


public class CreateServletDoctor extends HttpServlet {

    DoctorDAO doctorDAO = null;
    public void init () {
        doctorDAO = new DoctorDAO();
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException
    {
        System.out.println(req.getServletPath());

        RequestDispatcher rd = req.getRequestDispatcher( "/pages/doctor/create.jsp");
        rd.forward(req, res);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException
    {
        ArrayList<InputError> errors = new ArrayList<>();

        String name = req.getParameter("name");
        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");

        if ( Validate.validateEmpty("name", name) != null ) errors.add(Validate.validateEmpty("name", name));
        if ( Validate.validateEmpty("username", username) != null ) errors.add(Validate.validateEmpty("username", username));
        if ( doctorDAO.getDoctorByUsername( username ) != null ) errors.add(new InputError("username", "this username is already taken"));
        if ( Validate.validateEmpty("email", email) != null ) errors.add(Validate.validateEmpty("email", email));
        if ( Validate.validateEmail("email", email) != null ) errors.add(Validate.validateEmail("email", email));
        if ( Validate.validateEmpty("phone", phone) != null ) errors.add(Validate.validateEmpty("phone", phone));
        if ( Validate.validatePhone("phone", phone) != null ) errors.add(Validate.validatePhone("phone", phone));

        if (!errors.isEmpty()) {
            HttpSession session = req.getSession();
            session.setAttribute("errors", errors);

            Map<String, String> map = new HashMap<>();
            map.put("name", name);
            map.put("username", username);
            map.put("email", email);
            map.put("phone", phone);


            session.setAttribute("fieldOld", new Input(map));

            res.sendRedirect("/MediCare/doctor/create");
        } else {
            Doctor doctor = new Doctor( name, username, email, phone );

            doctorDAO.inserDoctor( doctor );

            res.sendRedirect("/MediCare/doctor");
        }
    }
}
