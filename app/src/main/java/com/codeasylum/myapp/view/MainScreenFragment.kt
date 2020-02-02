package com.codeasylum.myapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.codeasylum.myapp.R
import com.codeasylum.myapp.databinding.FragmentMainScreenBinding
import com.codeasylum.myapp.viewmodel.MainScreenFragmentViewModel


class MainScreenFragment : BaseFragment() {

    private lateinit var mainScreenFragmentViewModel: MainScreenFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentMainScreenBinding>(
            inflater,
            R.layout.fragment_main_screen,
            container,
            false
        )
        binding.lifecycleOwner = viewLifecycleOwner
        mainScreenFragmentViewModel =
            ViewModelProvider(this, viewModelFactory).get(MainScreenFragmentViewModel::class.java)
        binding.viewModel = mainScreenFragmentViewModel
        return binding.root
    }


}