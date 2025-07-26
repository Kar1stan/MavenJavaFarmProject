package com.solvd.project.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.project.model.Cherry;
import com.solvd.project.enums.ProcessingStage;
import com.solvd.project.enums.RipenessLevel;
import com.solvd.project.enums.StorageType;
import com.solvd.project.interfaces.Product;

public record Cherry(double weight, boolean ripe, LocalDate harvestDate, int daysToSpoil, RipenessLevel ripenessLevel,
        StorageType storageType, ProcessingStage stage) implements Product {

    private static final Logger logger = LogManager.getLogger(Cherry.class);

    @Override
    public String getName() {
        return "Cherry";
    }

    @Override
    public double getWeight() {
        return weight;
    }

    @Override
    public boolean isRipe() {
        return ripe;
    }

    @Override
    public void printDaysToSpoil() {
        LocalDate expiryDate = harvestDate.plusDays(daysToSpoil);
        long daysRemaining = ChronoUnit.DAYS.between(LocalDate.now(), expiryDate);

        if (daysRemaining > 0) {
            logger.info(getName() + " will spoil in " + daysRemaining + " day(s).");
        } else {
            logger.info(getName() + " has already spoiled.");
        }
    }

    public void wining() {
        logger.info("Wining cherry");
    }
}
