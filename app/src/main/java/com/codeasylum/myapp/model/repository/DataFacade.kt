package com.codeasylum.myapp.model.repository

import com.codeasylum.myapp.model.localDto.City
import io.reactivex.Single

interface DataFacade {

    fun getCity(isConnected: Boolean): Single<MutableList<City>>

    fun saveCity(city: City)
}