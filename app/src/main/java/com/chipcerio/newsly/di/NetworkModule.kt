package com.chipcerio.newsly.di

import com.chipcerio.newsly.BuildConfig
import com.chipcerio.newsly.api.ApiService
import com.chipcerio.newsly.api.ApiService.Api.BASE_URL
import com.chipcerio.newsly.api.ApiService.Api.VERSION
import com.chipcerio.newsly.api.ArticleDeserializer
import com.chipcerio.newsly.api.SourceDeserializer
import com.chipcerio.newsly.data.raw_types.Article
import com.chipcerio.newsly.data.raw_types.Source
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
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
    fun providesGson(): Gson {
        return GsonBuilder()
            .registerTypeAdapter(Source::class.java, SourceDeserializer())
            .registerTypeAdapter(Article::class.java, ArticleDeserializer())
            .setLenient()
            .create()
    }

    @Provides @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl("$BASE_URL$VERSION")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides @Singleton
    fun providesApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)
}