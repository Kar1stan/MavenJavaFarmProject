package com.solvd.project.model;

import com.solvd.project.interfaces.Product;

public abstract class Fruit implements Product {
    public abstract boolean isSweet();

    public void juice() {
        System.out.println("Juicing the fruit 🍹...");
    }
}
