package com.codeasylum.myapp

import android.app.Application
import com.codeasylum.myapp.di.component.ApplicationComponent
import com.codeasylum.myapp.di.component.DaggerApplicationComponent
import com.codeasylum.myapp.di.module.AppModule
import io.realm.Realm

class MyApplication : Application() {

    lateinit var appComponent: ApplicationComponent
        private set

    override fun onCreate() {
        super.onCreate()
        initRealm()
        injectApp()
    }

    private fun initRealm() {
        Realm.init(applicationContext)
    }


    private fun injectApp() {
        this.appComponent = DaggerApplicationComponent.builder()
            .appModule(AppModule(this))
            .build()
        this.appComponent.inject(this)
    }


}