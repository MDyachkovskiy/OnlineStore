package com.test.application.remote_data.di

import com.google.gson.GsonBuilder
import com.test.application.remote_data.api.CatalogueService
import com.test.application.remote_data.utils.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .build()
    }

    @Singleton
    @Provides
    fun provideCatalogueService(retrofit: Retrofit): CatalogueService {
        return retrofit.create(CatalogueService::class.java)
    }
}