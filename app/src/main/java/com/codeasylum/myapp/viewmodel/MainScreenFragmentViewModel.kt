package com.codeasylum.myapp.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.codeasylum.myapp.MyApplication
import com.codeasylum.myapp.model.repository.WeatherManager
import com.codeasylum.myapp.view.isConnectedToNetwork
import javax.inject.Inject

class MainScreenFragmentViewModel @Inject constructor(
     private val weatherManager: WeatherManager,
     private val myApplication: MyApplication
) : AndroidViewModel(myApplication) {
     val requestResult = MutableLiveData<String>("ffassf")

     @SuppressLint("CheckResult")
     fun fetchData() {
          weatherManager
               .getWeatherByCityAndCountryCode("London", "uk", myApplication.isConnectedToNetwork())
               .subscribe {
                    requestResult.postValue(it.toString())
               }
     }
}