package com.solvd.project.model;

public class Singleton {
    private static Singleton INSTANCE;

    private Singleton() {
    }

    public static Singleton getInstance() {
        if (INSTANCE == null) {
            synchronized (Singleton.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Singleton();
                }
            }
        }
        return INSTANCE;
    }

    public void printMessage(String message) {
        System.out.println("[" + Thread.currentThread().getName() + "]: " + message);
    }
}
