package com.l3azh.l3azhapp.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.internal.GsonBuildConfig
import com.l3azh.l3azhapp.api.L3azhApi
import com.l3azh.l3azhapp.utils.Constant
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class RetrofitModule {

    @Singleton
    @Provides
    fun providesLoger(): HttpLoggingInterceptor {
        val logger = HttpLoggingInterceptor()
        logger.setLevel(HttpLoggingInterceptor.Level.BODY)
        return logger
    }

    @Singleton
    @Provides
    fun providesClient(logger: HttpLoggingInterceptor): OkHttpClient {
        val client = OkHttpClient.Builder().addInterceptor(logger).build()
        return client
    }

    @Singleton
    @Provides
    fun providesGson(): Gson {
        return GsonBuilder().setDateFormat("yyyy-MM-dd").create()
    }

    @Singleton
    @Provides
    fun providesRetrofitInstance(client: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Singleton
    @Provides
    fun providesApiService(retrofit: Retrofit):L3azhApi{
        return retrofit.create(L3azhApi::class.java)
    }


}