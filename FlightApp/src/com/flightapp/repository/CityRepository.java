package com.flightapp.repository;

import com.flightapp.model.City;

public interface CityRepository {
    City getCity(String code);
    void addCity(City city);
}
