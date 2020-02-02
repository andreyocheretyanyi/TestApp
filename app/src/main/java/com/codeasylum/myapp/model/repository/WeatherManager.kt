package com.codeasylum.myapp.model.repository

import com.codeasylum.myapp.di.component.ApplicationScope
import com.codeasylum.myapp.model.DTO.WeatherDTO
import com.codeasylum.myapp.model.repository.weather.WeatherApiRepo
import com.codeasylum.myapp.model.repository.weather.WeatherCacheRepo
import com.codeasylum.myapp.model.repository.weather.WeatherDBRepo
import com.codeasylum.myapp.model.repository.weather.WeatherRepo
import io.reactivex.Flowable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@ApplicationScope
class WeatherManager @Inject constructor(
    private val weatherApiRepo: WeatherApiRepo,
    val weatherDBRepo: WeatherDBRepo,
    private val weatherCacheRepo: WeatherCacheRepo
) : WeatherRepo {

    override fun getWeatherByCityAndCountryCode(
        city: String,
        countryCode: String,
        networkAvailable: Boolean
    ): Flowable<WeatherDTO> = (if (networkAvailable)
        weatherApiRepo.getWeatherByCityAndCountryCode(city, countryCode, networkAvailable)
    else
        weatherCacheRepo.getWeatherByCityAndCountryCode(city, countryCode, networkAvailable))
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())


}