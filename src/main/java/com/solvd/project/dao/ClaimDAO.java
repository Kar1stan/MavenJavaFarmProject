package com.solvd.project.dao;

import com.solvd.project.dao.interfaces.GenericDAO;
import com.solvd.project.model.Claims;
import java.sql.*;
import java.sql.Date;
import java.util.*;

public class ClaimDAO implements GenericDAO<Claims, Integer> {
    private final Connection conn;

    public ClaimDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Claims getById(Integer id) {
        try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Claims WHERE ClaimId = ?")) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Claims(
                        rs.getInt("ClaimId"),
                        rs.getInt("Amount"),
                        rs.getString("Status"),
                        rs.getDate("Date_Filled").toLocalDate());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Claims> getAll() {
        List<Claims> claims = new ArrayList<>();
        try (Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM Claims")) {
            while (rs.next()) {
                claims.add(new Claims(
                        rs.getInt("ClaimId"),
                        rs.getInt("Amount"),
                        rs.getString("Status"),
                        rs.getDate("Date_Filled").toLocalDate()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return claims;
    }

    @Override
    public void insert(Claims claim) {
        try (PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO Claims (Amount, Status, Date_Filled) VALUES (?, ?, ?)")) {
            stmt.setInt(1, claim.getAmount());
            stmt.setString(2, claim.getStatus());
            stmt.setDate(3, Date.valueOf(claim.getDateFilled()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Claims claim) {
        try (PreparedStatement stmt = conn.prepareStatement(
                "UPDATE Claims SET Amount = ?, Status = ?, Date_Filled = ? WHERE ClaimId = ?")) {
            stmt.setInt(1, claim.getAmount());
            stmt.setString(2, claim.getStatus());
            stmt.setDate(3, Date.valueOf(claim.getDateFilled()));
            stmt.setInt(4, claim.getClaimId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer id) {
        try (PreparedStatement stmt = conn.prepareStatement("DELETE FROM Claims WHERE ClaimId = ?")) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}