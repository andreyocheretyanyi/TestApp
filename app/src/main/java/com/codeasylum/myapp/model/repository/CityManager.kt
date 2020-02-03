package com.codeasylum.myapp.model.repository

import android.annotation.SuppressLint
import com.codeasylum.myapp.di.component.ApplicationScope
import com.codeasylum.myapp.model.localDto.City
import com.codeasylum.myapp.model.repository.city.CityDbRepo
import com.codeasylum.myapp.model.repository.city.CityRepo
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@ApplicationScope
class CityManager @Inject constructor(private val cityDbRepo: CityDbRepo) : CityRepo {

    @SuppressLint("CheckResult")
    override fun getAllCities(): Flowable<List<City>> = cityDbRepo.getAllCities()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())


    override fun saveCity(city: City) {
        cityDbRepo.saveCity(city)
    }
}