package com.codeasylum.myapp.model.repository

import com.codeasylum.myapp.di.component.ApplicationScope
import com.codeasylum.myapp.model.DTO.WeatherDTO
import com.codeasylum.myapp.model.repository.weather.*
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@ApplicationScope
class WeatherManager @Inject constructor(
    private val weatherApiRepo: WeatherApiRepo,
    private val weatherDBRepo: WeatherDBRepo,
    private val weatherCacheRepo: WeatherCacheRepo
) : WeatherRepo, CanSaveData {

    override fun getWeatherByCityAndCountryCode(
        city: String,
        countryCode: String,
        networkAvailable: Boolean
    ): Flowable<WeatherDTO> = (
            if (networkAvailable) weatherApiRepo.getWeatherByCityAndCountryCode(
                city,
                countryCode,
                networkAvailable
            ).doOnNext {
                saveWeatherDto(city, countryCode, it)
            }
            else
                weatherDBRepo.getWeatherByCityAndCountryCode(
                    city,
                    countryCode,
                    networkAvailable
                ))
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())


    override fun saveWeatherDto(city: String, code: String, weatherDTO: WeatherDTO) {
        weatherDBRepo.saveWeatherDto(city, code, weatherDTO)
        weatherCacheRepo.saveWeatherDto(city, code, weatherDTO)
    }


}