package com.example.inventoryservice;

public class SeatSearchRequest {

    private String source;
    private String destination;
    private int requestedSeats;

    // getters and setters
    public String getSource() { return source; }
    public void setSource(String source) { this.source = source; }
    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }
    public int getRequestedSeats() { return requestedSeats; }
    public void setRequestedSeats(int requestedSeats) { this.requestedSeats = requestedSeats; }
}
