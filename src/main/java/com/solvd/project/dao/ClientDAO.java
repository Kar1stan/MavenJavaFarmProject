package com.solvd.project.dao;

import com.solvd.project.dao.interfaces.GenericDAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.solvd.project.model.Client;

public class ClientDAO implements GenericDAO<Client, Integer> {
    private final Connection conn;

    public ClientDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Client getById(Integer id) {
        try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Client WHERE id = ?")) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Client(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getDate("dob").toLocalDate());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Client> getAll() {
        List<Client> clients = new ArrayList<>();
        try (Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM Client")) {
            while (rs.next()) {
                clients.add(new Client(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getDate("dob").toLocalDate()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }

    @Override
    public void insert(Client client) {
        try (PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO Client (name, email, dob) VALUES (?, ?, ?)")) {
            stmt.setString(1, client.getName());
            stmt.setString(2, client.getEmail());
            stmt.setDate(3, Date.valueOf(client.getDob()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Client client) {
        try (PreparedStatement stmt = conn.prepareStatement(
                "UPDATE Client SET name = ?, email = ?, dob = ? WHERE id = ?")) {
            stmt.setString(1, client.getName());
            stmt.setString(2, client.getEmail());
            stmt.setDate(3, Date.valueOf(client.getDob()));
            stmt.setInt(4, client.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer id) {
        try (PreparedStatement stmt = conn.prepareStatement("DELETE FROM Client WHERE id = ?")) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}