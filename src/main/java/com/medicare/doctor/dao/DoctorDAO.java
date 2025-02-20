package com.medicare.doctor.dao;

import com.medicare.connect.Connect;
import com.medicare.doctor.model.Doctor;
import com.medicare.patient.model.Patient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DoctorDAO extends Connect {
    // sql code
    private static String ALL_DOCTORS  = "SELECT * FROM doctors;";
    private static String GET_DOCTOR_BY_ID = "SELECT * FROM doctors WHERE id = ?";
    private static String GET_DOCTOR_BY_USERNAME = "SELECT * FROM doctors WHERE username = ?";
    private static String INSERT_NEW_DOCTOR = "INSERT INTO doctors (name, username, email, phone) VALUES (?, ?, ?, ?)";

    public List<Doctor> getDoctors () {
        List<Doctor> doctors = new ArrayList<Doctor>();

        try (
                Connection con = getConnection();
                PreparedStatement stmt = con.prepareStatement(ALL_DOCTORS);
        ){
            System.out.println("connected on " + con);
            ResultSet rs = stmt.executeQuery();

            while ( rs.next() ) {
                int id = Integer.parseInt(rs.getString("id"));
                String name = rs.getString("name");
                String username = rs.getString("username");
                String email = rs.getString("email");
                String phone = rs.getString("phone");

                Doctor doctor = new Doctor(id, name, username, email, phone);
                doctors.add( doctor );
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return doctors;
    }

    public Doctor getDoctorById ( int doctorId ) {
        Doctor doctor = null;
        try (
                Connection con = getConnection();
                PreparedStatement stmt = con.prepareStatement(GET_DOCTOR_BY_ID)
        ){
            stmt.setInt(1, doctorId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = Integer.parseInt(rs.getString("id"));
                String name = rs.getString("name");
                String username = rs.getString("username");
                String email = rs.getString("email");
                String phone = rs.getString("phone");

                doctor = new Doctor(id, name, username, email, phone);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return doctor;
    }

    public Doctor getDoctorByUsername ( String doctorUsername ) {
        Doctor doctor = null;
        try (
                Connection con = getConnection();
                PreparedStatement stmt = con.prepareStatement(GET_DOCTOR_BY_USERNAME)
        ){
            stmt.setString(1, doctorUsername);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = Integer.parseInt(rs.getString("id"));
                String name = rs.getString("name");
                String username = rs.getString("username");
                String email = rs.getString("email");
                String phone = rs.getString("phone");

                doctor = new Doctor(id, name, username, email, phone);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return doctor;
    }

    public void inserDoctor ( Doctor doctor ) {
        try (
                Connection con = getConnection();
                PreparedStatement stmt = con.prepareStatement(INSERT_NEW_DOCTOR);
        ){
            stmt.setString(1, doctor.getName());
            stmt.setString(2, doctor.getUsername());
            stmt.setString(3, doctor.getEmail());
            stmt.setString(4, doctor.getPhone());

            stmt.executeUpdate();
        }
        catch ( SQLException e) {
            e.printStackTrace();
        }
    }
}
