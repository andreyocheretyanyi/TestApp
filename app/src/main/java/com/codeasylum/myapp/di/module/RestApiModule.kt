package com.codeasylum.myapp.di.module

import com.codeasylum.myapp.BuildConfig
import com.codeasylum.myapp.di.component.ApplicationScope
import com.codeasylum.myapp.model.repository.networkService.OpenWeatherApiService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class RestApiModule {

    @ApplicationScope
    @Provides
    fun provideMainRetrofitClient(): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(BuildConfig.BASE_URL)
            .build()


    @ApplicationScope
    @Provides
    fun provideWeatherApiService(retrofit: Retrofit): OpenWeatherApiService =
        retrofit.create(OpenWeatherApiService::class.java)
}