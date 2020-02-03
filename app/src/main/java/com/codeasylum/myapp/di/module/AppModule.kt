package com.codeasylum.myapp.di.module

import android.content.Context
import com.codeasylum.myapp.MyApplication
import com.codeasylum.myapp.di.component.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class AppModule(private val app: MyApplication) {

    @ApplicationScope
    @Provides
    fun provideApplication(): MyApplication = app

    @ApplicationScope
    @Provides
    fun provideContext(): Context = app.applicationContext

}