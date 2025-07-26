package com.solvd.project.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.project.interfaces.Processable;
import com.solvd.project.interfaces.Product;

public record Washer<T extends Product>(T item) implements Processable {
    private static final Logger logger = LogManager.getLogger(Washer.class);

    @Override
    public void prepare() {
        logger.info("🚿 Washing " + item.getName() + " to remove dirt and residue.");
    }
}
