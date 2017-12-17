package com.chipcerio.newsly.di

import com.chipcerio.newsly.BuildConfig
import com.chipcerio.newsly.api.ApiService
import com.chipcerio.newsly.common.Constants.Api.API_VERSION
import com.chipcerio.newsly.common.Constants.Api.BASE_URL
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides @Singleton
    fun providesOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.NONE
        return OkHttpClient.Builder()
                .addInterceptor(logging)
                .addInterceptor {
                    val original = it.request()
                    val requestBuilder = original.newBuilder()
                            .addHeader("X-Api-Key", BuildConfig.NEWS_API_KEY)
                    val request = requestBuilder.build()
                    it.proceed(request)
                }
                .build()
    }

    @Provides @Singleton
    fun providesMoshi(): Moshi = Moshi.Builder().build()

    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit {
        return Retrofit.Builder()
                .baseUrl("$BASE_URL$API_VERSION")
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build()
    }

    @Provides @Singleton
    fun providesApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

}