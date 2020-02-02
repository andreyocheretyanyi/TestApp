package com.codeasylum.myapp

import android.app.Application
import com.codeasylum.myapp.di.component.ApplicationComponent
import com.codeasylum.myapp.di.component.DaggerApplicationComponent

class MyApplication : Application() {

    lateinit var appComponent: ApplicationComponent
    private set

    override fun onCreate() {
        super.onCreate()
        injectApp()
    }


    private fun injectApp() {
        this.appComponent = DaggerApplicationComponent.builder()
            .build()
        this.appComponent.inject(this)
    }


}