package com.flightapp.repository;

import com.flightapp.model.City;
import com.flightapp.model.Flight;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InmemoryFlightRepository implements FlightRepository {
    private final Map<City, List<Flight>> flightMap = new ConcurrentHashMap<>();
    @Override
    public void addFlight(Flight flight) {
        flightMap.computeIfAbsent(flight.getSource(), k -> new ArrayList<>()).add(flight);
    }

    @Override
    public List<Flight> getFlightsFromCity(City city) {
        return flightMap.getOrDefault(city, Collections.emptyList());
    }

}
