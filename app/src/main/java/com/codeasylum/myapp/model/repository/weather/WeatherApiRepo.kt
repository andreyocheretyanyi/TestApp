package com.codeasylum.myapp.model.repository.weather

import com.codeasylum.myapp.di.component.ApplicationScope
import com.codeasylum.myapp.model.DTO.WeatherDTO
import com.codeasylum.myapp.model.repository.networkService.OpenWeatherApiService
import io.reactivex.Flowable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

private const val API_KEY = "2731dd9738886becd90a3e490c0af4aa"

@ApplicationScope
class WeatherApiRepo @Inject constructor(
    val openWeatherApiService: OpenWeatherApiService,
    val weatherCacheRepo: WeatherCacheRepo
) :
    WeatherRepo {

    override fun getWeatherByCityAndCountryCode(
        city: String,
        countryCode: String,
        networkAvailable: Boolean
    ): Flowable<WeatherDTO> = if (networkAvailable) {
        openWeatherApiService
            .getWeatherDataByCityName("$city,$countryCode", API_KEY)
            .doOnNext {
                weatherCacheRepo.saveResultToCache(city, countryCode, it)
            }
    } else {
        Flowable.empty()
    }


}



