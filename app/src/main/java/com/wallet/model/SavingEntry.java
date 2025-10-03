package com.wallet.model;

public class SavingEntry {
    private int id;
    private int walletId;
    private double amount;
    private String note;
    private String date; // ISO string from SQLite


    public SavingEntry(int id, int walletId, double amount, String note, String date) {
        this.id = id;
        this.walletId = walletId;
        this.amount = amount;
        this.note = note;
        this.date = date;
    }

    // getters
    public int getId() { return id; }
    public int getWalletId() { return walletId; }
    public double getAmount() { return amount; }
    public String getNote() { return note; }
    public String getDate() { return date; }
}