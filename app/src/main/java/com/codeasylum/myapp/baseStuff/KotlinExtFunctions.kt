package com.codeasylum.myapp.baseStuff

import android.content.Context
import android.net.ConnectivityManager
import androidx.lifecycle.MutableLiveData
import com.codeasylum.myapp.MyApplication

fun MyApplication.isConnectedToNetwork(): Boolean {
    val connectivityManager =
        this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
    return connectivityManager?.activeNetworkInfo?.isConnectedOrConnecting ?: false
}

fun <T> MutableLiveData<T>.notifyObserver() {
    this.value = this.value
}