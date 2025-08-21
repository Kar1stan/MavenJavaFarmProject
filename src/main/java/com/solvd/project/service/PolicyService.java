package com.solvd.project.service;

import com.solvd.project.dao.interfaces.GenericDAO;
import com.solvd.project.model.Policy;
import java.util.List;

public class PolicyService {
    private final GenericDAO<Policy, Integer> dao;

    public PolicyService(GenericDAO<Policy, Integer> dao) {
        this.dao = dao;
    }

    public Policy get(int id) {
        return dao.getById(id);
    }

    public List<Policy> getAll() {
        return dao.getAll();
    }

    public void create(Policy policy) {
        dao.insert(policy);
    }

    public void update(Policy policy) {
        dao.update(policy);
    }

    public void delete(int id) {
        dao.delete(id);
    }
}
