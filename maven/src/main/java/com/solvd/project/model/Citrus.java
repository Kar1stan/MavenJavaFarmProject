package com.solvd.project.model;

import com.solvd.project.interfaces.Product;

public abstract class Citrus implements Product {
    public abstract double getAcidity(); // pH level

    public void zest() {
        System.out.println("Zesting the citrus fruit 🍋...");
    }
}