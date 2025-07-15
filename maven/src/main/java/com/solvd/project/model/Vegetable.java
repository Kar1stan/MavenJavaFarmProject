package com.solvd.project.model;

import com.solvd.project.interfaces.Product;

public abstract class Vegetable implements Product {
    public abstract boolean isLeafy();

    public void chop() {
        System.out.println("Chopping the vegetable 🥕...");
    }
}
