package com.solvd.project.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.project.interfaces.Processable;
import com.solvd.project.interfaces.Product;

public class Peeler<T extends Product> implements Processable {
    private T item;
    private static final Logger logger = LogManager.getLogger(Peeler.class);

    public Peeler(T item) {
        this.item = item;
    }

    @Override
    public void prepare() {
        logger.info("🔪 Peeling " + item.getName() + " for processing or direct sale.");
    }
}
