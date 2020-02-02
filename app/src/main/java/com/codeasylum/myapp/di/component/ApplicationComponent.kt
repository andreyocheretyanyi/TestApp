package com.codeasylum.myapp.di.component

import com.codeasylum.myapp.MyApplication
import com.codeasylum.myapp.di.module.RestApiModule
import com.codeasylum.myapp.di.module.ViewModelModule
import com.codeasylum.myapp.model.repository.weather.WeatherApiRepo
import com.codeasylum.myapp.model.repository.weather.WeatherCacheRepo
import com.codeasylum.myapp.model.repository.weather.WeatherDBRepo
import com.codeasylum.myapp.view.BaseFragment
import dagger.Component

@ApplicationScope
@Component(
    modules = [
        ViewModelModule::class,
        RestApiModule::class]
)
interface ApplicationComponent {

    fun weatherApiRepo(): WeatherApiRepo
    fun weatherDBRepo() : WeatherDBRepo
    fun weatherCache() : WeatherCacheRepo

    fun inject(myApplication: MyApplication)
    fun inject(baseFragment: BaseFragment)
}