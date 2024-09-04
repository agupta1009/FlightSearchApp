package com.flightapp.service;

import com.flightapp.model.City;
import com.flightapp.model.FlightSearchResult;
import com.flightapp.repository.FlightRepository;

import java.util.List;

public interface FlightSearchStrategy {
    FlightSearchResult search(City source, City destination, FlightRepository repository, List<FlightSearchFilter> filters);
}
