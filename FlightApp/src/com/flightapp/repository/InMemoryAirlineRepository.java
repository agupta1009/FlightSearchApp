package com.flightapp.repository;

import com.flightapp.model.Airline;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryAirlineRepository implements AirlineRepository {

    private final Map<String, Airline> airLineMap = new ConcurrentHashMap<>();

    @Override
    public void addAirline(Airline airline) {
        airLineMap.put(airline.getName(), airline);
    }

    @Override
    public Airline getAirline(String name) {
        return airLineMap.getOrDefault(name, null);
    }

    @Override
    public boolean airlineExists(String name) {
        return getAirline(name) != null;
    }
}
