package com.codeasylum.myapp.view

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.codeasylum.myapp.MyApplication
import com.codeasylum.myapp.viewmodel.ViewModelFactory
import javax.inject.Inject


abstract class BaseFragment : Fragment() {

    lateinit var viewModelFactory: ViewModelFactory
        @Inject set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity!!.application as MyApplication).appComponent.inject(this)
    }

}

fun MyApplication.isConnectedToNetwork(): Boolean {
    val connectivityManager =
        this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
    return connectivityManager?.activeNetworkInfo?.isConnectedOrConnecting ?: false
}