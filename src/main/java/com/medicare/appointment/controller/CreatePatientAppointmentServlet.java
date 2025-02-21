package com.medicare.appointment.controller;

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

public class CreatePatientAppointmentServlet extends HttpServlet {

    public void init () {

    }

    public void doGet (HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException
    {

        RequestDispatcher rd = req.getRequestDispatcher("/pages/appointment/create-appointment.jsp");
        rd.forward(req, res);
    }

    public void doPost (HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException
    {
        ArrayList<InputError> errors = new ArrayList<>();

        String name = req.getParameter("name");
        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");

        if ( Validate.validateEmpty("name", name) != null ) errors.add(Validate.validateEmpty("name", name));
        if ( Validate.validateEmpty("username", username) != null ) errors.add(Validate.validateEmpty("username", username));
        //if ( patientDAO.getPatientByUsername( username ) != null ) errors.add(new InputError("username", "this username is already taken"));
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

            res.sendRedirect("/MediCare/patient/create");
        } else {
            Patient patient = new Patient( name, username, email, phone );

            //patientDAO.inserPatient( patient );

            res.sendRedirect("/MediCare/patient");
        }
    }
}
