package com.example.inventoryservice;
import java.io.Serializable;

public class BookingEvent implements Serializable{

    private String bookingId;
    private String status;

    public String getBookingId() {
        return this.bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
