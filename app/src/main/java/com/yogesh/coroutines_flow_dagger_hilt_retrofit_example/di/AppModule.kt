package com.yogesh.coroutines_flow_dagger_hilt_retrofit_example.di

import com.yogesh.coroutines_flow_dagger_hilt_retrofit_example.connect.ApiService
import com.yogesh.coroutines_flow_dagger_hilt_retrofit_example.utils.MyConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun getInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    @Singleton
    fun getHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(25, TimeUnit.SECONDS)
            .readTimeout(25, TimeUnit.SECONDS)
            .writeTimeout(25, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    @Named("BASE_URL_TMDB")
    fun provideBaseUrlTmdb() = MyConstants.BASE_URL_TMDB

    @Provides
    @Singleton
    @Named("TMDB")
    fun provideRetrofitTmdb(
        @Named("BASE_URL_TMDB") baseUrl: String,
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create()).client(okHttpClient).build()
    }

    @Provides
    @Singleton
    @Named("TMDB")
    fun provideApiServiceTmdb(@Named("TMDB") retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    @Named("BASE_URL_TESTING")
    fun provideBaseUrlTesting() = MyConstants.BASE_URL_JSON_PLACEHOLDER_TYPICODE


    @Provides
    @Singleton
    @Named("TESTING")
    fun provideRetrofitTesting(
        @Named("BASE_URL_TESTING") baseUrl: String,
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create()).client(okHttpClient).build()
    }

    @Provides
    @Singleton
    @Named("TESTING")
    fun provideApiServiceTesting(@Named("TESTING") retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}