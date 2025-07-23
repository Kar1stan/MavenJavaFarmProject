package com.solvd.project.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.project.model.Onion;
import com.solvd.project.enums.ProcessingStage;
import com.solvd.project.enums.RipenessLevel;
import com.solvd.project.enums.StorageType;
import com.solvd.project.exceptions.EmptyException;
import com.solvd.project.exceptions.SpoiledException;
import com.solvd.project.interfaces.Harvestable;
import com.solvd.project.interfaces.Pricable;
import com.solvd.project.interfaces.Product;
import com.solvd.project.interfaces.Storable;

public class Onion extends Citrus implements Product, Harvestable, Storable, Pricable {
    private double weight;
    private boolean ripe;
    public LocalDate harvestDate;
    public int daysToSpoil;
    public double pricePerKg;
    private boolean organic;
    private RipenessLevel ripenessLevel;
    private StorageType storageType;
    private ProcessingStage stage;
    private static final Logger logger = LogManager.getLogger(Onion.class);

    public Onion(double weight, boolean ripe, LocalDate harvestDate, int daysToSpoil, boolean organic,
            RipenessLevel ripenessLevel,
            StorageType storageType, ProcessingStage stage) {
        this.weight = weight;
        this.ripe = ripe;
        this.harvestDate = harvestDate;
        this.daysToSpoil = daysToSpoil;
        this.organic = organic;
        this.ripenessLevel = ripenessLevel;
        this.storageType = storageType;
        this.stage = stage;
    }

    @Override
    public String getName() {
        return "Onion";
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

    // Business logic for pricing
    @Override
    public double getTotalPrice() {
        double basePrice = weight * pricePerKg;
        // Apply 15% discount if close to spoiling (≤ 2 days left)
        long daysRemaining = ChronoUnit.DAYS.between(LocalDate.now(), harvestDate.plusDays(daysToSpoil));
        if (daysRemaining <= 2) {
            basePrice *= 0.85;
        }

        return basePrice;
    }

    // 🌾 Business logic for harvesting
    @Override
    public void harvest() {
        if (ripe) {
            System.out.println("Harvesting ripe apple 🍏... no special care needed.");
        } else {
            System.out.println("Harvesting unripe apple 🍏... requires gentle handling and cold storage.");
        }
    }

    // 📦 Business logic for storage
    @Override
    public String getStorageAdvice() throws EmptyException {
        String baseAdvice = organic
                ? "Store in a breathable crate at 1–4°C (organic protection recommended)."
                : "Store in standard refrigerated bins at 1–4°C.";

        long daysLeft = ChronoUnit.DAYS.between(LocalDate.now(), harvestDate.plusDays(daysToSpoil));

        if (daysLeft <= 2) {
            baseAdvice += " Consume within 48 hours!";
        } else if (daysLeft == 0) {
            throw new EmptyException("The product is gone", new RuntimeException());
        }

        return baseAdvice;
    }

    public void crying() throws QueueSize {
        logger.info("Crying over onion");
        throw new SpoiledException("Exception for the sake of exception", new Exception());

    }

    public double getAcidity() {
        return 2.3;
    }
}
