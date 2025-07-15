package com.solvd.project.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.project.interfaces.Processable;
import com.solvd.project.interfaces.Product;

public class Washer<T extends Product> implements Processable {
    private T item;
    private static final Logger logger = LogManager.getLogger(Washer.class);

    public Washer(T item) {
        this.item = item;
    }

    @Override
    public void prepare() {
        logger.info("🚿 Washing " + item.getName() + " to remove dirt and residue.");
    }
}
