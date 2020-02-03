package com.codeasylum.myapp.model.DTO

import com.google.gson.annotations.SerializedName
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.Index
import io.realm.annotations.PrimaryKey
import java.util.*


open class WeatherDTO(
    @SerializedName("cod")
    var cod: Int = 200,
    @PrimaryKey
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("main")
    var main: Main? = null,
    @SerializedName("name")
    var name: String = "",
    @SerializedName("visibility")
    var visibility: Int = 0,
    @SerializedName("weather")
    var weather: RealmList<Weather> = RealmList(),
    @SerializedName("wind")
    var wind: Wind? = null
) : RealmObject() {
    override fun toString(): String {
        return "WeatherDTO(cod=$cod, id=$id, main=$main, name='$name', visibility=$visibility, weather=${weather.get(
            0
        ).toString()}, wind=$wind)"
    }

}

open class Main(
    @PrimaryKey
    @Index
    var id: Int = 0,
    @SerializedName("humidity")
    var humidity: Int = 0,
    @SerializedName("pressure")
    var pressure: Int = 0,
    @SerializedName("temp")
    var temp: Double = 0.0,
    @SerializedName("temp_max")
    var tempMax: Double = 0.0,
    @SerializedName("temp_min")
    var tempMin: Double = 0.0
) : RealmObject() {
    override fun toString(): String {
        return "Main(id=$id, humidity=$humidity, pressure=$pressure, temp=$temp, tempMax=$tempMax, tempMin=$tempMin)"
    }
}

open class Weather(
    @PrimaryKey
    var id: String = UUID.randomUUID().toString(),
    @SerializedName("description")
    var description: String = "",
    @SerializedName("main")
    var main: String = ""
) : RealmObject() {
    override fun toString(): String {
        return "Weather(id=$id, description='$description', main='$main')"
    }
}

open class Wind(
    @PrimaryKey
    var id: String = UUID.randomUUID().toString(),
    @SerializedName("deg")
    var deg: Int = 0,
    @SerializedName("speed")
    var speed: Double = 0.0
) : RealmObject() {

    override fun toString(): String {
        return "Wind(id=$id, deg=$deg, speed=$speed)"
    }
}