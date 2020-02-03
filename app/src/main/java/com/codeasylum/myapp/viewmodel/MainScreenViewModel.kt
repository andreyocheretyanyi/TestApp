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
    val cityName = MutableLiveData<String>("")
    val cityCode = MutableLiveData<String>("")
    val error = MutableLiveData<String>()

    @SuppressLint("CheckResult")
    fun fetchFirstData() {
        if (cities.value!!.isEmpty()) {
            dataFacadeImpl.getCities(
                myApplication.isConnectedToNetwork(), arrayListOf(),
                updateOld = true,
                addDefault = true
            )
                .doOnError { error.postValue(it.toString()) }
                .subscribe { it ->
                    cities.value!!.addAll(it)
                    cities.notifyObserver()
                }
        }
    }


    fun addCityToList() {
        cityName.value?.apply {
            if (isNotEmpty()) {
                dataFacadeImpl.getCities(
                    myApplication.isConnectedToNetwork(),
                    arrayListOf(City(this, cityCode.value ?: "")),
                    updateOld = true,
                    addDefault = false
                ).doOnError { error.postValue(it.toString()) }
                    .subscribe { it ->
                        cities.value!!.clear()
                        cities.value!!.addAll(it)
                        cities.notifyObserver()
                    }
            }
        }

    }
}