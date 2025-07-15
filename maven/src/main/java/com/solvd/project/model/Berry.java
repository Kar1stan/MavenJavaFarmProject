package com.solvd.project.model;

import com.solvd.project.interfaces.Product;

public abstract class Berry implements Product {
    public abstract int getAntioxidantLevel(); // scale of 1–10

    public void wash() {
        System.out.println("Washing the berries 🍓...");
    }
}
