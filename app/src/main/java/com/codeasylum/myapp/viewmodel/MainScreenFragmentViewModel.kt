package com.codeasylum.myapp.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.codeasylum.myapp.model.repository.WeatherManager
import javax.inject.Inject

class MainScreenFragmentViewModel @Inject constructor(private val weatherManager: WeatherManager) : ViewModel() {
     val requestResult = MutableLiveData<String>("ffassf")

     @SuppressLint("CheckResult")
     fun fetchData(){
          weatherManager
               .getWeatherByCityAndCountryCode("London","uk",true)
               .subscribe {
                    requestResult.postValue(it.toString())
               }
     }
}