package com.codeasylum.myapp.model.repository

import android.annotation.SuppressLint
import com.codeasylum.myapp.di.component.ApplicationScope
import com.codeasylum.myapp.model.DTO.WeatherDTO
import com.codeasylum.myapp.model.localDto.City
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@ApplicationScope
class DataFacadeImpl @Inject constructor(
    private val weatherManager: WeatherManager,
    private val cityManager: CityManager
) : DataFacade {
    @SuppressLint("CheckResult")
    override fun getCity(isConnected: Boolean): Single<MutableList<City>> {
        val cities = arrayListOf(City("London", "uk"), City("Texas", "us"))
        return if (isConnected)
            Flowable.fromIterable(cities)
                .concatMap { t: City ->
                    Flowable.zip(
                        Flowable.just(t), weatherManager.getWeatherByCityAndCountryCode(
                            t.name,
                            t.code,
                            true
                        )
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread()),
                        BiFunction<City, WeatherDTO, City> { city, weatherDto ->
                            return@BiFunction City(
                                city.name,
                                city.code,
                                0.0,
                                0.0,
                                weatherDto.main.temp.toString(),
                                weatherDto.main.tempMax.toString(),
                                weatherDto.main.tempMin.toString(),
                                "${weatherDto.weather[0].main},${weatherDto.weather[0].description}"
                            )

                        })
                }
                .doOnNext {
                    cityManager.saveCity(it)
                }
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        else cityManager.getAllCities()
    }

    override fun saveCity(city: City) {
        cityManager.saveCity(city)
    }
}