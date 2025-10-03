package com.wallet.model;

public class Wallet {
    private int id;
    private String name;
    private double targetAmount;
    private double savedAmount;


    public Wallet(int id, String name, double targetAmount, double savedAmount) {
        this.id = id;
        this.name = name;
        this.targetAmount = targetAmount;
        this.savedAmount = savedAmount;
    }


    // getters
    public int getId() { return id; }
    public String getName() { return name; }
    public double getTargetAmount() { return targetAmount; }
    public double getSavedAmount() { return savedAmount; }


    // setters
    public void setSavedAmount(double savedAmount) { this.savedAmount = savedAmount; }
}