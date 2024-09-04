package com.flightapp.service;

import com.flightapp.model.Airline;
import com.flightapp.model.City;
import com.flightapp.model.Flight;
import com.flightapp.model.FlightSearchResult;
import com.flightapp.repository.AirlineRepository;
import com.flightapp.repository.CityRepository;
import com.flightapp.repository.FlightRepository;

import java.time.LocalDateTime;
import java.util.List;

public class FlightBookingService {
    private final FlightRepository flightRepository;
    private final FlightSearchStrategy flightSearchStrategy;
    private final AirlineRepository airlineRepository;
    private final CityRepository cityRepository;

    public FlightBookingService(FlightRepository flightRepository, FlightSearchStrategy flightSearchStrategy,
            AirlineRepository airlineRepository, CityRepository cityRepository) {
        this.flightRepository = flightRepository;
        this.flightSearchStrategy = flightSearchStrategy;
        this.airlineRepository = airlineRepository;
        this.cityRepository = cityRepository;
    }

    public void registerFlight(String airlineName, String sourceCode, String destinationCode, int price, boolean mealAvailable,
            String classType, boolean drinksProvided, LocalDateTime departureTime, LocalDateTime arrivalTime) {
        City source = cityRepository.getCity(sourceCode);
        City destination = cityRepository.getCity(destinationCode);
        Airline airline = airlineRepository.getAirline(airlineName);

        if (source == null || destination == null) {
            throw new IllegalArgumentException("Source or Destination city not found!");
        }
        if (airline == null) {
            throw new IllegalArgumentException("Airline not found!");

        }

        Flight flight =
                new Flight(airline.getName(), source, destination, price, mealAvailable, classType, drinksProvided, departureTime,
                        arrivalTime);
        flightRepository.addFlight(flight);
        System.out.printf("%s %s -> %s flight registered%n", airline.getName(), source.getCityCode(), destination.getCityCode());

    }

    public FlightSearchResult searchFlights(String sourceCode, String destinationCode, List<FlightSearchFilter> filters,
            boolean sortByDurationAscending) {
        City source = cityRepository.getCity(sourceCode);
        City destination = cityRepository.getCity(destinationCode);

        if (source == null || destination == null) {
            throw new IllegalArgumentException("Source or Destination city not found!");
        }

        FlightSearchResult result = flightSearchStrategy.search(source, destination, flightRepository, filters);
        result.sortFlightByDuration(sortByDurationAscending);
//        result.printResults();
        return result;
    }

    public void addCity(String cityCode) {
        cityRepository.addCity(new City(cityCode));
        System.out.println("City added: " + cityCode);

    }

    public void addAirline(String name) {
        if (airlineRepository.airlineExists(name)) {
            System.out.println("Airline already exists: " + name);
        } else {
            airlineRepository.addAirline(new Airline(name));
            System.out.println("Airline added: " + name);
        }
    }
}
