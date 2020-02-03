package com.codeasylum.myapp.model.repository.city

import com.codeasylum.myapp.di.component.ApplicationScope
import com.codeasylum.myapp.model.localDto.City
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.realm.Realm
import io.realm.RealmConfiguration
import javax.inject.Inject

@ApplicationScope
class CityDbRepo @Inject constructor(private val realmConfiguration: RealmConfiguration) :
    CityRepo {
    override fun getAllCities(): Flowable<List<City>> = Flowable.create(
        {
            val realm = Realm.getInstance(realmConfiguration)
            try {
                val realmResult = realm.where(City::class.java).findAll()
                if (realmResult != null && !realm.isEmpty) {
                    it.onNext(realmResult)
                    it.onComplete()
                }
            } catch (e: Exception) {
                it.onError(e)
            } finally {
                realm.close()
            }
        }, BackpressureStrategy.LATEST
    )


    override fun saveCity(city: City) =
        with(Realm.getInstance(realmConfiguration)) {
            beginTransaction()
            copyToRealmOrUpdate(city)
            commitTransaction()
            close()
        }


}