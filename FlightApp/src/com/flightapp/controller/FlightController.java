package com.flightapp.controller;

import com.flightapp.model.FlightSearchResult;
import com.flightapp.repository.AirlineRepository;
import com.flightapp.repository.CityRepository;
import com.flightapp.repository.FlightRepository;
import com.flightapp.repository.InMemoryAirlineRepository;
import com.flightapp.repository.InMemoryCityRepository;
import com.flightapp.repository.InmemoryFlightRepository;
import com.flightapp.service.FlightBookingService;
import com.flightapp.service.FlightSearchFilter;
import com.flightapp.service.MinCostAndMinHopsFlightSearchStrategy;

import java.time.LocalDateTime;
import java.util.List;

public class FlightController {
    static FlightRepository flightRepository = new InmemoryFlightRepository();
    static AirlineRepository airlineRepository = new InMemoryAirlineRepository();
    static CityRepository cityRepository = new InMemoryCityRepository();

    private static volatile FlightController instance = null;

    private final FlightBookingService flightBookingService;

    public FlightController(FlightBookingService flightBookingService) {
        this.flightBookingService = flightBookingService;
    }

    public static FlightController getInstance() {
        if (instance == null) {
            synchronized (FlightController.class) {
                if (instance == null) {
                    instance = new FlightController(
                            new FlightBookingService(flightRepository, new MinCostAndMinHopsFlightSearchStrategy(),
                                    airlineRepository, cityRepository));
                }
            }
        }
        return instance;
    }

    public void registerFlight(String airline, String source, String destination, int price, boolean mealAvailable,
            String classType, boolean drinksProvided, LocalDateTime departureTime, LocalDateTime arrivalTime) {
        flightBookingService.registerFlight(airline, source, destination, price, mealAvailable, classType, drinksProvided,
                departureTime, arrivalTime);
    }

    public FlightSearchResult searchFlights(String source, String destination, List<FlightSearchFilter> filters,
            boolean sortByDurationAscending) {
        return flightBookingService.searchFlights(source, destination, filters, sortByDurationAscending);
    }

    public void addCity(String cityCode) {
        flightBookingService.addCity(cityCode);
    }

    public void addAirline(String name) {
        flightBookingService.addAirline(name);
    }

}
