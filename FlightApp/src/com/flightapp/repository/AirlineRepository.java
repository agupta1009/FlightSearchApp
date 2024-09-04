package com.flightapp.repository;

import com.flightapp.model.Airline;

import java.util.Set;

public interface AirlineRepository {
    void addAirline(Airline airline);
    Airline getAirline(String name);
    boolean airlineExists(String name);
}
