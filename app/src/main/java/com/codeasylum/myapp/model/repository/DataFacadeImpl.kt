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
    override fun getCities(
        isConnected: Boolean,
        cities: MutableList<City>,
        updateOld: Boolean,
        addDefault: Boolean
    ): Single<MutableList<City>> {

        if (addDefault) {
            cities.add(0, City("London", "uk"))
            cities.add(1, City("Texas, us"))
        }

        return if (isConnected)
            (if (updateOld) Flowable.zip(Flowable.just(cities),
                cityManager.getAllCities().toFlowable(),
                BiFunction<MutableList<City>, MutableList<City>, MutableList<City>> { live, old ->
                    return@BiFunction filterCities(live, old)
                }).flatMap { ci -> Flowable.fromIterable(ci) }
            else Flowable.fromIterable(cities))
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

    private fun filterCities(
        liveCity: MutableList<City>,
        oldCities: MutableList<City>
    ): MutableList<City> = ArrayList<City>()
        .apply {
            addAll(liveCity)
            for (i in 0 until oldCities.size) {
                if (contains(oldCities[i]))
                    continue
                add(oldCities[i])
            }

        }


    override fun saveCity(city: City) {
        cityManager.saveCity(city)
    }
}