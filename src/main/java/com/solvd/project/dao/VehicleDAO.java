package com.solvd.project.dao;

import com.solvd.project.dao.interfaces.GenericDAO;
import com.solvd.project.model.Vehicles;
import java.sql.*;
import java.util.*;

public class VehicleDAO implements GenericDAO<Vehicles, Integer> {
    private final Connection conn;

    public VehicleDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Vehicles getById(Integer id) {
        try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Vehicles WHERE VehicleId = ?")) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Vehicles(
                        rs.getInt("VehicleId"),
                        rs.getString("Model"),
                        rs.getString("Registration_Year"),
                        rs.getString("VIN"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Vehicles> getAll() {
        List<Vehicles> vehicles = new ArrayList<>();
        try (Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM Vehicles")) {
            while (rs.next()) {
                vehicles.add(new Vehicles(
                        rs.getInt("VehicleId"),
                        rs.getString("Model"),
                        rs.getString("Registration_Year"),
                        rs.getString("VIN")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicles;
    }

    @Override
    public void insert(Vehicles vehicle) {
        try (PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO Vehicles (Model, Registration_Year, VIN) VALUES (?, ?, ?)")) {
            stmt.setString(1, vehicle.getModel());
            stmt.setString(2, vehicle.getRegistrationYear());
            stmt.setString(3, vehicle.getVin());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Vehicles vehicle) {
        try (PreparedStatement stmt = conn.prepareStatement(
                "UPDATE Vehicles SET Model = ?, Registration_Year = ?, VIN = ? WHERE VehicleId = ?")) {
            stmt.setString(1, vehicle.getModel());
            stmt.setString(2, vehicle.getRegistrationYear());
            stmt.setString(3, vehicle.getVin());
            stmt.setInt(4, vehicle.getVehicleId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer id) {
        try (PreparedStatement stmt = conn.prepareStatement("DELETE FROM Vehicles WHERE VehicleId = ?")) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}