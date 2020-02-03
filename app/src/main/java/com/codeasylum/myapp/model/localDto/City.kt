package com.codeasylum.myapp.model.localDto

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class City(
    @PrimaryKey
    var name: String = "",
    var code: String = "",
    var lat: Double? = 0.0,
    var lng: Double? = 0.0,
    var tmp: String = "",
    var maxTmp: String = "",
    var minTmp: String = "",
    var desc: String = ""
) : RealmObject() {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as City

        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }
}