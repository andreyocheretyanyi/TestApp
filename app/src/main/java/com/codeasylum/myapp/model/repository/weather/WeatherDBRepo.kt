package com.codeasylum.myapp.model.repository.weather

import com.codeasylum.myapp.di.component.ApplicationScope
import com.codeasylum.myapp.model.DTO.WeatherDTO
import io.reactivex.Flowable
import javax.inject.Inject

@ApplicationScope
class WeatherDBRepo @Inject constructor() :
    WeatherRepo {
    override fun getWeatherByCityAndCountryCode(
        city: String,
        countryCode: String,
        networkAvailable: Boolean
    ): Flowable<WeatherDTO> {

        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}