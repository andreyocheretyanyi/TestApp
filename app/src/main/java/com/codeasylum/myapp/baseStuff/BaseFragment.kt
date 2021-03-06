package com.codeasylum.myapp.baseStuff

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.codeasylum.myapp.MyApplication
import javax.inject.Inject


abstract class BaseFragment : Fragment() {

    lateinit var viewModelFactory: ViewModelFactory
        @Inject set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity!!.application as MyApplication).appComponent.inject(this)
    }

}

