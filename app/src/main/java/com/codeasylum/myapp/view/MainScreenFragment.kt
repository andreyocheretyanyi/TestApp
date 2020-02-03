package com.codeasylum.myapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.codeasylum.myapp.R
import com.codeasylum.myapp.baseStuff.BaseFragment
import com.codeasylum.myapp.databinding.FragmentMainScreenBinding
import com.codeasylum.myapp.viewmodel.MainScreenViewModel


class MainScreenFragment : BaseFragment() {

    private lateinit var mainScreenViewModel: MainScreenViewModel

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
        mainScreenViewModel =
            ViewModelProvider(this, viewModelFactory).get(MainScreenViewModel::class.java)
        binding.viewModel = mainScreenViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainScreenViewModel.fetchDataForFirsTwoCity()
    }


}