package com.codeasylum.myapp.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.codeasylum.myapp.MyApplication
import com.codeasylum.myapp.baseStuff.isConnectedToNetwork
import com.codeasylum.myapp.baseStuff.notifyObserver
import com.codeasylum.myapp.model.DTO.WeatherDTO
import com.codeasylum.myapp.model.localDto.City
import com.codeasylum.myapp.model.repository.CityManager
import com.codeasylum.myapp.model.repository.WeatherManager
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainScreenViewModel @Inject constructor(
    private val weatherManager: WeatherManager,
    private val cityManager: CityManager,
    private val myApplication: MyApplication
) : AndroidViewModel(myApplication) {


    val cities = MutableLiveData<ArrayList<City>>(arrayListOf())

    @SuppressLint("CheckResult")
    fun fetchDataForFirsTwoCity() {
        if (cities.value!!.isEmpty()) {
            val cities = arrayListOf(City("London", "uk"), City("Texas", "us"))
            Flowable.fromIterable(cities)
                .concatMap { t: City ->
                    Flowable.zip(Flowable.just(t), weatherManager.getWeatherByCityAndCountryCode(
                        t.name,
                        t.code,
                        myApplication.isConnectedToNetwork()
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
                .subscribe { it ->
                    this.cities.value!!.addAll(it)
                    this.cities.notifyObserver()
                }

        }
    }
}