package com.medicare.appointment.dao;

import com.medicare.appointment.model.Appointment;
import com.medicare.connect.Connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AppointmentDAO extends Connect {

    private static final String INSERT_INTO_APPOINTMENT = "INSERT INTO appointments (patient_id, doctor_id, appDate, appTime, motif ) VALUES(?, ?, ?, ?, ?);";

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

}
