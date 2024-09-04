package com.flightapp.model;

import java.time.Duration;
import java.time.LocalDateTime;

public class Flight {
    private String airline;
    private City source;
    private City destination;
    private int price;
    private boolean mealAvailable;
    private String classType;
    private boolean drinksProvided;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;

    public Flight(String airline, City source, City destination, int price, boolean mealAvailable, String classType,
            boolean drinksProvided, LocalDateTime departureTime, LocalDateTime arrivalTime) {
        this.airline = airline;
        this.source = source;
        this.destination = destination;
        this.price = price;
        this.mealAvailable = mealAvailable;
        this.classType = classType;
        this.drinksProvided = drinksProvided;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public City getSource() {
        return source;
    }

    public void setSource(City source) {
        this.source = source;
    }

    public City getDestination() {
        return destination;
    }

    public void setDestination(City destination) {
        this.destination = destination;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isMealAvailable() {
        return mealAvailable;
    }

    public void setMealAvailable(boolean mealAvailable) {
        this.mealAvailable = mealAvailable;
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public boolean isDrinksProvided() {
        return drinksProvided;
    }

    public void setDrinksProvided(boolean drinksProvided) {
        this.drinksProvided = drinksProvided;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public long getDurationMinutes() {
        return Duration.between(departureTime, arrivalTime).toMinutes();
    }

    @Override
    public String toString() {
        return String.format("%s to %s via %s for %d (Duration: %d min)", source.getCityCode(), destination.getCityCode(),
                airline, price, getDurationMinutes());
    }
}
