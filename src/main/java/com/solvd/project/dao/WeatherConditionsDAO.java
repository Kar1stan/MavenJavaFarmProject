package com.solvd.project.dao;

import com.solvd.project.dao.interfaces.GenericDAO;
import com.solvd.project.model.WeatherConditions;
import java.sql.*;
import java.sql.Date;
import java.util.*;

public class WeatherConditionsDAO implements GenericDAO<WeatherConditions, Integer> {
    private final Connection conn;

    public WeatherConditionsDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public WeatherConditions getById(Integer id) {
        try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM WeatherConditions WHERE WeatherId = ?")) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new WeatherConditions(
                        rs.getInt("WeatherId"),
                        rs.getString("Condition"),
                        rs.getDouble("Temperature"),
                        rs.getDate("Reported Date").toLocalDate());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<WeatherConditions> getAll() {
        List<WeatherConditions> conditions = new ArrayList<>();
        try (Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM WeatherConditions")) {
            while (rs.next()) {
                conditions.add(new WeatherConditions(
                        rs.getInt("WeatherId"),
                        rs.getString("Condition"),
                        rs.getDouble("Temperature"),
                        rs.getDate("Reported Date").toLocalDate()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conditions;
    }

    @Override
    public void insert(WeatherConditions wc) {
        try (PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO WeatherConditions (Condition, Temperature,Date) VALUES (?, ?, ?, ?)")) {
            stmt.setString(1, wc.getCondition());
            stmt.setDouble(2, wc.getTemperature());
            stmt.setDate(4, Date.valueOf(wc.getDateReported()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(WeatherConditions wc) {
        try (PreparedStatement stmt = conn.prepareStatement(
                "UPDATE WeatherConditions SET Condition = ?, Temperature = ?, Visibility = ?, Date = ? WHERE WeatherId = ?")) {
            stmt.setString(1, wc.getCondition());
            stmt.setDouble(2, wc.getTemperature());
            stmt.setDate(4, Date.valueOf(wc.getDateReported()));
            stmt.setInt(5, wc.getWeatherId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer id) {
        try (PreparedStatement stmt = conn.prepareStatement("DELETE FROM WeatherConditions WHERE WeatherId = ?")) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
