package com.morpion.valorant.di

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun mySharedPreferences(@ApplicationContext app: Context): SharedPreferences {
        return app.getSharedPreferences(
            "local_sessions",
            Context.MODE_PRIVATE
        )
    }

}