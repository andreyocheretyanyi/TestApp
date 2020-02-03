package com.codeasylum.myapp.model.repository.city

import com.codeasylum.myapp.di.component.ApplicationScope
import com.codeasylum.myapp.model.localDto.City
import io.reactivex.Single
import io.realm.Realm
import io.realm.RealmConfiguration
import javax.inject.Inject

@ApplicationScope
class CityDbRepo @Inject constructor(private val realmConfiguration: RealmConfiguration) :
    CityRepo {
    override fun getAllCities(): Single<MutableList<City>> = Single.create {
        val realm = Realm.getInstance(realmConfiguration)
        try {
            val realmResult = realm.where(City::class.java).findAll()
            if (realmResult != null) {
                it.onSuccess(realm.copyFromRealm(realmResult))
            }
        } catch (e: Exception) {
            it.onError(e)
        } finally {
            realm.close()
        }
    }


    override fun saveCity(city: City) =
        with(Realm.getInstance(realmConfiguration)) {
            beginTransaction()
            copyToRealmOrUpdate(city)
            commitTransaction()
            close()
        }


}