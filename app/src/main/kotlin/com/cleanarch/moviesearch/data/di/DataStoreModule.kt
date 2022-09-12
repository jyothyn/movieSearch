package com.cleanarch.moviesearch.data.di

import android.content.Context
import com.cleanarch.moviesearch.data.ds.LaunchTimeDS
import com.cleanarch.moviesearch.data.ds.LaunchTimeDsImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
object DataStoreModule {

    @ActivityRetainedScoped
    @Provides
    fun provideLaunchTimeDSImpl(@ApplicationContext context: Context): LaunchTimeDS =
        LaunchTimeDsImpl(context)
}