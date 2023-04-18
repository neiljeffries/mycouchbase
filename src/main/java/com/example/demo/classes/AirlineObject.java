package com.example.demo.classes;

public class AirlineObject {
    private Airline airline;

    public Airline getAirline() {
        return airline;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }

    @Override
    public String toString() {
        return "AirlineObject [airline=" + airline + "]";
    }

}
