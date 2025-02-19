package com.medicare.patient.dao;

import com.medicare.connect.Connect;
import com.medicare.patient.model.Patient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PatientDAO extends Connect {



    // sql code
    private static String ALL_PATIENTS  = "SELECT * FROM patients;";
    private static String GET_PATIENT_BY_ID = "SELECT * FROM patients WHERE id = ?";

    public List<Patient> getPatients () {
        List<Patient> patients = new ArrayList<Patient>();

        try (
            Connection con = getConnection();
            PreparedStatement stmt = con.prepareStatement(ALL_PATIENTS);
        ){
            System.out.println("connected on " + con);
            ResultSet rs = stmt.executeQuery();

            while ( rs.next() ) {
                int id = Integer.parseInt(rs.getString("id"));
                String name = rs.getString("name");
                String username = rs.getString("username");
                String email = rs.getString("email");
                String phone = rs.getString("phone");

                Patient patient = new Patient(id, name, username, email, phone);
                patients.add( patient );
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return patients;
    }





}
