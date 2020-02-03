package com.codeasylum.myapp.di.component

import com.codeasylum.myapp.MyApplication
import com.codeasylum.myapp.baseStuff.BaseFragment
import com.codeasylum.myapp.di.module.AppModule
import com.codeasylum.myapp.di.module.RealmModule
import com.codeasylum.myapp.di.module.RestApiModule
import com.codeasylum.myapp.di.module.ViewModelModule
import com.codeasylum.myapp.model.repository.city.CityDbRepo
import com.codeasylum.myapp.model.repository.weather.WeatherApiRepo
import com.codeasylum.myapp.model.repository.weather.WeatherCacheRepo
import dagger.Component

@ApplicationScope
@Component(
    modules = [
        AppModule::class,
        ViewModelModule::class,
        RestApiModule::class,
        RealmModule::class]
)
interface ApplicationComponent {

    fun weatherApiRepo(): WeatherApiRepo
    fun weatherCache(): WeatherCacheRepo
    fun cityDbRepo(): CityDbRepo

    fun inject(myApplication: MyApplication)
    fun inject(baseFragment: BaseFragment)
}