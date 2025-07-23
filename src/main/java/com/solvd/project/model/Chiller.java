package com.solvd.project.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.project.interfaces.Processable;
import com.solvd.project.interfaces.Product;

public class Chiller<T extends Product> implements Processable {
    private T item;
    private static final Logger logger = LogManager.getLogger(Chiller.class);

    public Chiller(T item) {
        this.item = item;
    }

    @Override
    public void prepare() {
        logger.info("❄️ Placing " + item.getName() + " into cold storage to preserve freshness.");
    }
}
