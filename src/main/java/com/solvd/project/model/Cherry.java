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

public class Cherry implements Product {
    private double weight;
    private boolean ripe;
    public LocalDate harvestDate;
    public int daysToSpoil;
    private RipenessLevel ripenessLevel;
    private StorageType storageType;
    private ProcessingStage stage;
    private static final Logger logger = LogManager.getLogger(Cherry.class);

    public Cherry(double weight, boolean ripe, LocalDate harvestDate, int daysToSpoil, RipenessLevel ripenessLevel,
            StorageType storageType, ProcessingStage stage) {
        this.weight = weight;
        this.ripe = ripe;
        this.harvestDate = harvestDate;
        this.daysToSpoil = daysToSpoil;
        this.ripenessLevel = ripenessLevel;
        this.storageType = storageType;
        this.stage = stage;
    }

    @Override
    public String getName() {
        return "Cherry";
    }

    public RipenessLevel getRipenessLevel() {
        return ripenessLevel;
    }

    public StorageType getStorageType() {
        return storageType;
    }

    public ProcessingStage getStage() {
        return stage;
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
