package com.codeasylum.myapp.model.repository.weather

import com.codeasylum.myapp.model.DTO.WeatherDTO
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherRepo {
    fun getWeatherByCityAndCountryCode(city: String, countryCode: String, networkAvailable : Boolean): Flowable<WeatherDTO>
}