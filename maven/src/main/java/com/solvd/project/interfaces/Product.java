package com.solvd.project.interfaces;

public interface Product {
     String getName();

     double getWeight(); // in kg

     boolean isRipe();

     public void printDaysToSpoil();
}