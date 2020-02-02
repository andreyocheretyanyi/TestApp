package com.codeasylum.myapp.model.repository.weather

import com.codeasylum.myapp.di.component.ApplicationScope
import com.codeasylum.myapp.model.DTO.WeatherDTO
import io.reactivex.Flowable
import javax.inject.Inject

@ApplicationScope
class WeatherCacheRepo @Inject constructor() :
    WeatherRepo {
    private val weatherMapCache: HashMap<String, WeatherDTO> = HashMap()

    override fun getWeatherByCityAndCountryCode(
        city: String,
        countryCode: String,
        networkAvailable : Boolean
    ): Flowable<WeatherDTO> = weatherMapCache["$city,$countryCode"].let {
        return if (it == null)
            Flowable.empty()
        else Flowable.just(it)
    }

    fun saveResultToCache(city: String, code: String, dto: WeatherDTO) {
        weatherMapCache["$city,$code"] = dto
    }

}

