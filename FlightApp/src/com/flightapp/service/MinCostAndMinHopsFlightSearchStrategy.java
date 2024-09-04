package com.flightapp.service;

import com.flightapp.model.City;
import com.flightapp.model.Flight;
import com.flightapp.model.FlightSearchResult;
import com.flightapp.repository.FlightRepository;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class MinCostAndMinHopsFlightSearchStrategy implements FlightSearchStrategy {

    private static final int MAX_HOPS = 5;

    @Override
    public FlightSearchResult search(City source, City destination, FlightRepository repository,
            List<FlightSearchFilter> filters) {
        List<List<Flight>> allPaths = findAllPaths(source, destination, repository, filters);
        List<Flight> minHopsFlight = null;
        List<Flight> cheapestFlight = null;
        int minHops = Integer.MAX_VALUE;
        int minCost = Integer.MAX_VALUE;
//        source=a destination=b
//
//        [a->c,c->d,d-e,e-b], [a->d,d->e,e->b]

        for (List<Flight> path : allPaths) {
            int hops = path.size();
            int cost = path.stream().mapToInt(Flight::getPrice).sum();

            if (hops <= minHops) {
                minHops = hops;
                minHopsFlight = new ArrayList<>(path);
            }
            if (cost < minCost) {
                minCost = cost;
                cheapestFlight = new ArrayList<>(path);
            }
        }

        return new FlightSearchResult(cheapestFlight, minHopsFlight);
    }

    private List<List<Flight>> findAllPaths(City source, City destination, FlightRepository repository,
            List<FlightSearchFilter> filters) {
        List<List<Flight>> allPaths = new ArrayList<>();
        Deque<Flight> currentPath = new ArrayDeque<>();
        findPaths(source, destination, repository, filters, allPaths, currentPath, 0);
        return allPaths;
    }

    private void findPaths(City current, City destination, FlightRepository repository, List<FlightSearchFilter> filters,
            List<List<Flight>> allPaths, Deque<Flight> currentPath, int currentHops) {
        if (current.equals(destination)) {
            allPaths.add(new ArrayList<>(currentPath));
            return;
        }

        if (currentHops >= MAX_HOPS) {
            return;
        }

        for (Flight flight : repository.getFlightsFromCity(current)) {
            if (applyFilters(flight, filters) && !currentPath.contains(flight)) {
                currentPath.addLast(flight);
                findPaths(flight.getDestination(), destination, repository, filters, allPaths, currentPath, currentHops + 1);
                currentPath.removeLast();
            }
        }
    }

    private boolean applyFilters(Flight flight, List<FlightSearchFilter> filters) {
        if (filters == null || filters.isEmpty()) {
            return true;
        }
        for (FlightSearchFilter filter : filters) {
            if (!filter.apply(flight)) {
                return false;
            }
        }
        return true;
    }
}
