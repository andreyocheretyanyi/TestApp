package com.codeasylum.myapp.di.module

import com.codeasylum.myapp.di.component.ApplicationScope
import dagger.Module
import dagger.Provides
import io.realm.Realm
import io.realm.RealmConfiguration

@Module
class RealmModule {
    @ApplicationScope
    @Provides
    fun provideRealmConfig() : RealmConfiguration = RealmConfiguration.Builder().build()

    @ApplicationScope
    @Provides
    fun provideRealm(realmConfiguration: RealmConfiguration) : Realm = Realm.getInstance(realmConfiguration)
}