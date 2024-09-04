package com.flightapp.service;

import com.flightapp.model.Flight;

public interface FlightSearchFilter {
    boolean apply(Flight flight);
}
