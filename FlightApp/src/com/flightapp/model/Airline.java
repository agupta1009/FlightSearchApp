package com.flightapp.model;

public class Airline {
    private String name;

    public Airline(String name) {
        if (name == null || name.length() <= 2) {
            throw new IllegalArgumentException("Airline name must be longer than 2 characters.");
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Airline airline = (Airline) obj;
        return name.equals(airline.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return name;
    }
}
