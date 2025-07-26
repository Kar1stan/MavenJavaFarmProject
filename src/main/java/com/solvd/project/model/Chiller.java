package com.solvd.project.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.project.interfaces.Processable;
import com.solvd.project.interfaces.Product;

public record Chiller<T extends Product>(T item) implements Processable {
    private static final Logger logger = LogManager.getLogger(Chiller.class);

    @Override
    public void prepare() {
        logger.info("❄️ Placing " + item.getName() + " into cold storage to preserve freshness.");
    }
}
