package com.codeasylum.myapp.di.module

import com.codeasylum.myapp.di.component.ApplicationScope
import dagger.Module
import dagger.Provides
import io.realm.RealmConfiguration

@Module
class RealmModule {
    @ApplicationScope
    @Provides
    fun provideRealmConfig() : RealmConfiguration = RealmConfiguration.Builder().build()

}