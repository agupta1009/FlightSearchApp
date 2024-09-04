package com.flightapp.repository;

import com.flightapp.model.City;
import com.flightapp.model.Flight;

import java.util.List;

public interface FlightRepository {
    void addFlight(Flight flight);
    List<Flight> getFlightsFromCity(City city);
}
