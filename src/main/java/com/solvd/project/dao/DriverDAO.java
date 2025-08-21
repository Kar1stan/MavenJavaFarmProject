package com.solvd.project.dao;

import com.solvd.project.dao.interfaces.GenericDAO;
import com.solvd.project.model.Drivers;
import java.sql.*;
import java.util.*;

public class DriverDAO implements GenericDAO<Drivers, Integer> {
    private final Connection conn;

    public DriverDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Drivers getById(Integer id) {
        try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Drivers WHERE DriverId = ?")) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Drivers(
                        rs.getInt("DriverId"),
                        rs.getString("License"),
                        rs.getString("Experience"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Drivers> getAll() {
        List<Drivers> drivers = new ArrayList<>();
        try (Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM Drivers")) {
            while (rs.next()) {
                drivers.add(new Drivers(
                        rs.getInt("DriverId"),
                        rs.getString("License"),
                        rs.getString("Experience")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return drivers;
    }

    @Override
    public void insert(Drivers driver) {
        try (PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO Drivers (License, Experience) VALUES (?, ?)")) {
            stmt.setString(1, driver.getLicense());
            stmt.setString(2, driver.getExperience());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Drivers driver) {
        try (PreparedStatement stmt = conn.prepareStatement(
                "UPDATE Drivers SET License = ?, Experience = ? WHERE DriverId = ?")) {
            stmt.setString(1, driver.getLicense());
            stmt.setString(2, driver.getExperience());
            stmt.setInt(3, driver.getDriverId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer id) {
        try (PreparedStatement stmt = conn.prepareStatement("DELETE FROM Drivers WHERE DriverId = ?")) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}