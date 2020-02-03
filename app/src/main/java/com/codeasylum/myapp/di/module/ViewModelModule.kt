package com.codeasylum.myapp.di.module

import androidx.lifecycle.ViewModel
import com.codeasylum.myapp.di.component.ViewModelKey
import com.codeasylum.myapp.viewmodel.MainScreenViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainScreenViewModel::class)
    abstract fun mainScreenFragmentViewModel(mainScreenViewModel: MainScreenViewModel): ViewModel
}