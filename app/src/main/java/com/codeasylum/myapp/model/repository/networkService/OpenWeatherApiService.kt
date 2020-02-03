package com.codeasylum.myapp.model.repository.networkService

import com.codeasylum.myapp.model.DTO.WeatherDTO
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherApiService {
    @GET("data/2.5/weather")
    //q={city name},{country code}
    fun getWeatherDataByCityName(
        @Query("q") city_countryCode: String, @Query("units") units: String, @Query("APPID") apiKey: String
    ): Flowable<WeatherDTO>
}