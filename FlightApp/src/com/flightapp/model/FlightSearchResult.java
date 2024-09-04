package com.flightapp.model;

import java.util.Comparator;
import java.util.List;

public class FlightSearchResult {
    private List<Flight> cheapestFlight;
    private List<Flight> minHopsFlight;

    public FlightSearchResult(List<Flight> cheapestFlight, List<Flight> minHopsFlight) {
        this.cheapestFlight = cheapestFlight;
        this.minHopsFlight = minHopsFlight;
    }

    public List<Flight> getCheapestFlight() {
        return cheapestFlight;
    }

    public void setCheapestFlight(List<Flight> cheapestFlight) {
        this.cheapestFlight = cheapestFlight;
    }

    public List<Flight> getMinHopsFlight() {
        return minHopsFlight;
    }

    public void setMinHopsFlight(List<Flight> minHopsFlight) {
        this.minHopsFlight = minHopsFlight;
    }

    public void sortFlightByDuration(boolean ascending) {
        Comparator<Flight> comparator = Comparator.comparing(Flight::getDurationMinutes);
        if (!ascending) {
            comparator = comparator.reversed();
        }
        if (!(cheapestFlight == null || cheapestFlight.isEmpty())) {
            this.cheapestFlight.sort(comparator);
        }
        if (!(minHopsFlight == null || minHopsFlight.isEmpty())) {
            this.minHopsFlight.sort(comparator);

        }
    }

    public void printResults() {
        if (cheapestFlight==null || cheapestFlight.isEmpty()){
            System.out.println("no flight");
        }else{
        System.out.println("Cheapest Flight:");
        cheapestFlight.forEach(System.out::println);
        System.out.println("Total Flights: " + cheapestFlight.size());
        System.out.println("Total Cost: " + cheapestFlight.stream().mapToInt(Flight::getPrice).sum());}

        if(minHopsFlight==null||minHopsFlight.isEmpty()){
            System.out.println("no flight");
        }else{
        System.out.println("\nFlight with Minimum Hops:");
        minHopsFlight.forEach(System.out::println);
        System.out.println("Total Flights: " + minHopsFlight.size());
        System.out.println("Total Cost: " + minHopsFlight.stream().mapToInt(Flight::getPrice).sum());}
    }
}
