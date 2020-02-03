package com.codeasylum.myapp.model.repository.city

import com.codeasylum.myapp.model.localDto.City
import io.reactivex.Flowable

interface CityRepo {

    fun getAllCities(): Flowable<List<City>>

    fun saveCity(city: City)
}