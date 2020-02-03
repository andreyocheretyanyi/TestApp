package com.codeasylum.myapp.model.DTO

import com.google.gson.annotations.SerializedName


data class WeatherDTO(
    @SerializedName("cod")
    val cod: Int,
    @SerializedName("main")
    val main: Main,
    @SerializedName("name")
    val name: String,
    @SerializedName("weather")
    val weather: List<Weather>
)

data class Main(
    @SerializedName("temp")
    val temp: Double,
    @SerializedName("temp_max")
    val tempMax: Double,
    @SerializedName("temp_min")
    val tempMin: Double
)

class Weather(
    @SerializedName("description")
    val description: String,
    @SerializedName("main")
    val main: String
)