package com.medicare.appointment.dao;

import com.medicare.appointment.model.Appointment;
import com.medicare.connect.Connect;
import com.medicare.doctor.model.Doctor;
import com.medicare.patient.model.Patient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDAO extends Connect {

    private static final String INSERT_INTO_APPOINTMENT = "INSERT INTO appointments (patient_id, doctor_id, appDate, appTime, motif ) VALUES(?, ?, ?, ?, ?);";
    private static final String GET_APPOINTMENTS_BY_PATIENT_ID = "SELECT\n" +
            "\tappointments.id,\n" +
            "    appointments.appDate,\n" +
            "    appointments.appTime,\n" +
            "    appointments.motif,\n" +
            "    doctors.name,\n" +
            "    doctors.phone\n" +
            "FROM appointments\n" +
            "inner join doctors\n" +
            "on doctors.id = appointments.doctor_id\n" +
            "where appointments.patient_id = ?;";

    private static final String GET_APPOINTMENTS_BY_DOCTOR_ID = "SELECT \n" +
            "\tappointments.id,\n" +
            "    appointments.appDate,\n" +
            "    appointments.appTime,\n" +
            "    appointments.motif,\n" +
            "    patients.name,\n" +
            "    patients.phone\n" +
            "FROM appointments\n" +
            "inner join patients\n" +
            "on patients.id = appointments.patient_id\n" +
            "where appointments.doctor_id = ?;";
    private static final String CANCEL_APPOINTMENT = "UPDATE appointments SET is_canceled = TRUE WHERE id = ?;";

    private static final String GET_APPOINTMENT_BY_ID = "SELECT\n" +
            "\tappointments.id,\n" +
            "    appointments.appDate,\n" +
            "    appointments.appTime,\n" +
            "    patients.name\n" +
            "FROM appointments\n" +
            "inner join patients\n" +
            "on patients.id = appointments.patient_id\n" +
            "where appointments.id = ?;";

    private static final String POSTPONE_APPOINTMENT = "UPDATE appointments\n" +
            "SET\n" +
            "    appDate = ?,\n" +
            "    appTime = ?\n" +
            "WHERE \n" +
            "    appointments.id = ?;";

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
                String name = rs.getString("name");
                String phone = rs.getString("phone");

                appointments.add(new Appointment(id, appDate, appTime, motif, new Doctor(name, phone)));

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
                String name = rs.getString("name");
                String phone = rs.getString("phone");

                appointments.add(new Appointment(id, appDate, appTime, motif, new Patient(name, phone)));

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

    public Appointment getAppointmentById (int apptId ) {
        Appointment appointment = null;

        try (
             Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(GET_APPOINTMENT_BY_ID);
        ) {
            stmt.setInt(1, apptId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String appDate = rs.getString("appDate");
                String appTime = rs.getString("appTime");
                String name = rs.getString("name");
                appointment = new Appointment(id, appDate, appTime, new Patient(name));
            }
        }
        catch ( SQLException e) {
            e.printStackTrace();
        }

        return appointment;
    }

    public void postponeAppointment ( Appointment appointment ) {
        try (
                Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement(POSTPONE_APPOINTMENT);
        ){
            stmt.setString(1, appointment.getAppDate());
            stmt.setString(2, appointment.getAppTime());
            stmt.setInt(3, appointment.getId());
            stmt.executeUpdate();
        }
        catch ( SQLException e ) {
            e.printStackTrace();
        }
    }

}
