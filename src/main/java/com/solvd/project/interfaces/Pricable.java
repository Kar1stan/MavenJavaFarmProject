package com.solvd.project.interfaces;

import com.solvd.project.exceptions.PriceException;

public interface Pricable {
    double getTotalPrice() throws PriceException;
}