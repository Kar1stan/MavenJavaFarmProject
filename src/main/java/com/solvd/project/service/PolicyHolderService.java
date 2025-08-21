package com.solvd.project.service;

import com.solvd.project.dao.interfaces.GenericDAO;
import com.solvd.project.model.PolicyHolders;
import java.util.List;

public class PolicyHolderService {
    private final GenericDAO<PolicyHolders, Integer> dao;

    public PolicyHolderService(GenericDAO<PolicyHolders, Integer> dao) {
        this.dao = dao;
    }

    public PolicyHolders get(int id) {
        return dao.getById(id);
    }

    public List<PolicyHolders> getAll() {
        return dao.getAll();
    }

    public void create(PolicyHolders holder) {
        dao.insert(holder);
    }

    public void update(PolicyHolders holder) {
        dao.update(holder);
    }

    public void delete(int id) {
        dao.delete(id);
    }
}
