package com.codeasylum.myapp.model.repository.weather


import com.codeasylum.myapp.model.DTO.WeatherDTO
import io.reactivex.Flowable

interface WeatherRepo {
    fun getWeatherByCityAndCountryCode(city: String, countryCode: String, networkAvailable : Boolean): Flowable<WeatherDTO>
}