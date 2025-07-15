package com.solvd.project.model;

import com.solvd.project.interfaces.Product;

public abstract class Melon implements Product {
    public abstract int getWaterContent(); // % water by weight

    public void slice() {
        System.out.println("Slicing the melon 🍈...");
    }
}
