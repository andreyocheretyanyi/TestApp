package com.codeasylum.myapp.model.repository.weather

import com.codeasylum.myapp.model.DTO.WeatherDTO

interface CanSaveData {

    fun saveWeatherDto(city: String, code: String, weatherDTO: WeatherDTO)
}