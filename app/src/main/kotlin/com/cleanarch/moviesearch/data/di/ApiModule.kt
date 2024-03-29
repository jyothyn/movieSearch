package com.cleanarch.moviesearch.data.di

import com.cleanarch.moviesearch.data.network.ApiService
import com.cleanarch.moviesearch.data.repository.SearchRepository
import com.cleanarch.moviesearch.data.repository.SearchRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import retrofit2.Retrofit

@Module
@InstallIn(ActivityRetainedComponent::class)
object ApiModule {

    @ActivityRetainedScoped
    @Provides
    fun provideSearchService(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)

    @ActivityRetainedScoped
    @Provides
    fun provideRepositoryImpl(apiService: ApiService): SearchRepository =
        SearchRepositoryImpl(apiService)
}