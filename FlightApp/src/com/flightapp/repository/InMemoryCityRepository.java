package com.flightapp.repository;

import com.flightapp.model.City;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryCityRepository implements CityRepository {

    private final Map<String, City> cityMap = new ConcurrentHashMap<>();


    @Override
    public City getCity(String code) {
        return cityMap.get(code);
    }

    @Override
    public void addCity(City city) {
        cityMap.put(city.getCityCode(), city);
    }
}
