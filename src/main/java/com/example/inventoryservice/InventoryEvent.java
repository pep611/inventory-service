package com.example.inventoryservice;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class InventoryEvent implements Serializable{

    private String bookingId;
    private String busNumber;
    private int noOfSeats;

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getBusNumber() {
        return busNumber;
    }

    public void setBusNumber(String busNumber) {
        this.busNumber = busNumber;
    }
    public int getNoOfSeats() {
        return noOfSeats;
    }

    public void setNoOfSeats(int noOfSeats) {
        this.noOfSeats = noOfSeats;
    }
}
