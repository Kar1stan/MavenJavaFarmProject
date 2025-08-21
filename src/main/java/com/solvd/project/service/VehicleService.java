package com.solvd.project.service;

import com.solvd.project.dao.interfaces.GenericDAO;
import com.solvd.project.model.Vehicles;

import java.util.List;

public class VehicleService {
    private final GenericDAO<Vehicles, Integer> dao;

    public VehicleService(GenericDAO<Vehicles, Integer> dao) {
        this.dao = dao;
    }

    public Vehicles get(int id) {
        return dao.getById(id);
    }

    public List<Vehicles> getAll() {
        return dao.getAll();
    }

    public void create(Vehicles vehicle) {
        dao.insert(vehicle);
    }

    public void update(Vehicles vehicle) {
        dao.update(vehicle);
    }

    public void delete(int id) {
        dao.delete(id);
    }
}