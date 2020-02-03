package com.codeasylum.myapp.model.repository.city

import com.codeasylum.myapp.model.localDto.City
import io.reactivex.Single

interface CityRepo {

    fun getAllCities(): Single<MutableList<City>>

    fun saveCity(city: City)
}