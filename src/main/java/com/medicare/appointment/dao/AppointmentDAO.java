package com.medicare.appointment.dao;

import com.medicare.appointment.model.Appointment;
import com.medicare.connect.Connect;
import com.medicare.patient.model.Patient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDAO extends Connect {

    private static final String INSERT_INTO_APPOINTMENT = "INSERT INTO appointments (patient_id, doctor_id, appDate, appTime, motif ) VALUES(?, ?, ?, ?, ?);";
    private static final String GET_APPOINTMENTS_BY_PATIENT_ID = "SELECT * FROM appointments WHERE patient_id = ?;";
    private static final String GET_APPOINTMENTS_BY_DOCTOR_ID = "SELECT * FROM appointments WHERE doctor_id = ?;";
    private static final String CANCEL_APPOINTMENT = "UPDATE appointments SET is_canceled = TRUE WHERE id = ?;";

    public void insetAppointment (Appointment appointment) {
        try (
            Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(INSERT_INTO_APPOINTMENT);
        ){
            stmt.setInt(1, appointment.getPatientId());
            stmt.setInt(2, appointment.getDoctorId());
            stmt.setString(3, appointment.getAppDate());
            stmt.setString(4, appointment.getAppTime());
            stmt.setString(5, appointment.getMotif());
            stmt.executeUpdate();
        }
        catch ( SQLException e ) {
            e.printStackTrace();
        }
    }

    public List<Appointment> getAppointmentByPatientId ( int patientId ) {
        List<Appointment> appointments = new ArrayList<>();

        try (
            Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(GET_APPOINTMENTS_BY_PATIENT_ID);
        ){
            stmt.setInt(1, patientId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String appDate = rs.getString("appDate");
                String appTime = rs.getString("appTime");
                String motif = rs.getString("motif");

                appointments.add(new Appointment(id, appDate, appTime, motif));

            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return appointments;
    }

    public List<Appointment> getAppointmentByDcotorId ( int doctorId ) {
        List<Appointment> appointments = new ArrayList<>();

        try (
                Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement(GET_APPOINTMENTS_BY_DOCTOR_ID);
        ){
            stmt.setInt(1, doctorId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String appDate = rs.getString("appDate");
                String appTime = rs.getString("appTime");
                String motif = rs.getString("motif");

                appointments.add(new Appointment(id, appDate, appTime, motif));

            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return appointments;
    }

    public void cancelAppointment ( int appointmentId ) {
        try (
            Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(CANCEL_APPOINTMENT);
        ){
            stmt.setInt(1, appointmentId);
            stmt.executeUpdate();
        }
        catch ( SQLException e ) {
            e.printStackTrace();
        }
    }

}
