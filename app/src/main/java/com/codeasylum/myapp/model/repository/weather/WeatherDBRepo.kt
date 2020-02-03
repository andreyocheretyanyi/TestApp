package com.codeasylum.myapp.model.repository.weather

import com.codeasylum.myapp.di.component.ApplicationScope
import com.codeasylum.myapp.model.DTO.WeatherDTO
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.realm.Realm
import io.realm.RealmConfiguration
import javax.inject.Inject

@ApplicationScope
class WeatherDBRepo @Inject constructor(private val realmConfiguration: RealmConfiguration) :
    WeatherRepo, CanSaveData {

    override fun getWeatherByCityAndCountryCode(
        city: String,
        countryCode: String,
        networkAvailable: Boolean
    ): Flowable<WeatherDTO> {
        return Flowable.create({
            val realm = Realm.getInstance(realmConfiguration)
            try {
                val realmResult =
                    realm.where(WeatherDTO::class.java).equalTo("name", city).findAll()
                val weatherDbDTO = realmResult.last()
                if (weatherDbDTO != null) {
                    it.onNext(realm.copyFromRealm(weatherDbDTO))
                }
                it.onComplete()
            } catch (e: Exception) {
                it.onError(e)
            } finally {
                realm.close()
            }

        }, BackpressureStrategy.LATEST)
    }


    override fun saveWeatherDto(city: String, code: String, weatherDTO: WeatherDTO) {
        with(Realm.getInstance(realmConfiguration)) {
            beginTransaction()
            val main = copyToRealmOrUpdate(weatherDTO.main!!)
            val wind = copyToRealmOrUpdate(weatherDTO.wind!!)
            copyToRealmOrUpdate(weatherDTO.weather)
            val realmWeatherDTO = WeatherDTO(
                weatherDTO.cod, weatherDTO.id, main, weatherDTO.name, weatherDTO.visibility,
                weatherDTO.weather, wind
            )
            copyToRealmOrUpdate(realmWeatherDTO)
            commitTransaction()
            close()
        }


    }
}