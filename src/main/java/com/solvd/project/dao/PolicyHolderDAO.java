package com.solvd.project.dao;

import com.solvd.project.dao.interfaces.GenericDAO;
import com.solvd.project.model.PolicyHolders;
import java.sql.*;
import java.util.*;

public class PolicyHolderDAO implements GenericDAO<PolicyHolders, Integer> {
    private final Connection conn;

    public PolicyHolderDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public PolicyHolders getById(Integer id) {
        try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM PolicyHolders WHERE HolderId = ?")) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new PolicyHolders(
                        rs.getInt("HolderId"),
                        rs.getString("Name"),
                        rs.getString("Contact"),
                        rs.getDate("dob").toLocalDate());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<PolicyHolders> getAll() {
        List<PolicyHolders> holders = new ArrayList<>();
        try (Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM PolicyHolders")) {
            while (rs.next()) {
                holders.add(new PolicyHolders(
                        rs.getInt("HolderId"),
                        rs.getString("Name"),
                        rs.getString("Conctact"),
                        rs.getDate("dob").toLocalDate()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return holders;
    }

    @Override
    public void insert(PolicyHolders holder) {
        try (PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO PolicyHolders (Name, Phone) VALUES (?, ?, ?)")) {
            stmt.setString(1, holder.getName());
            stmt.setString(3, holder.getPhone());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(PolicyHolders holder) {
        try (PreparedStatement stmt = conn.prepareStatement(
                "UPDATE PolicyHolders SET Name = ?, Phone = ? WHERE HolderId = ?")) {
            stmt.setString(1, holder.getName());
            stmt.setString(3, holder.getPhone());
            stmt.setInt(4, holder.getPolicyHolderId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer id) {
        try (PreparedStatement stmt = conn.prepareStatement("DELETE FROM PolicyHolders WHERE HolderId = ?")) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
