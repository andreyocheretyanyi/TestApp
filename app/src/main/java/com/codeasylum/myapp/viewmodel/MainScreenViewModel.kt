package com.codeasylum.myapp.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.codeasylum.myapp.MyApplication
import com.codeasylum.myapp.baseStuff.isConnectedToNetwork
import com.codeasylum.myapp.baseStuff.notifyObserver
import com.codeasylum.myapp.model.localDto.City
import com.codeasylum.myapp.model.repository.DataFacadeImpl
import javax.inject.Inject

class MainScreenViewModel @Inject constructor(
    private val dataFacadeImpl: DataFacadeImpl,
    private val myApplication: MyApplication
) : AndroidViewModel(myApplication) {


    val cities = MutableLiveData<ArrayList<City>>(arrayListOf())

    @SuppressLint("CheckResult")
    fun fetchDataForFirsTwoCity() {
        if (cities.value!!.isEmpty()) {
            dataFacadeImpl.getCity(myApplication.isConnectedToNetwork())
                .subscribe { it ->
                    cities.value!!.addAll(it)
                    cities.notifyObserver()
                }
        }
    }
}